import os
import re
import statistics
from glob import glob
import matplotlib.pyplot as plt

LOG_DIR = './superstars-oom-logs'

TAGS = [
    "App Started",
    "Before UploadRecordScreen",
    "PreviewNewStoryScreen Loaded"
]

def parse_log(logfile):
    """Return dict {tag: {java, native, rss}}"""
    with open(logfile, encoding='utf-8') as f:
        content = f.read()
    pattern = re.compile(
        r'\[DEBUG OOM\] \[(.+?)\]\nJava Heap: ([\d.]+) MB / ([\d.]+) MB \([\d.]+%\)\nNative Heap: ([\d.]+) MB\nRss Total: ([\d.]+) MB'
    )
    results = {}
    for tag, java_heap, java_max, native, rss in pattern.findall(content):
        results[tag.strip('[]')] = {
            'java': float(java_heap),
            'native': float(native),
            'rss': float(rss)
        }
    return results

def diff(a, b):
    if a is None or b is None:
        return None
    return round(b - a, 1)

def summarize(arr):
    arr_clean = [x for x in arr if x is not None]
    if not arr_clean:
        return '-'
    return f"{round(statistics.mean(arr_clean),1)} (±{round(statistics.stdev(arr_clean),1) if len(arr_clean)>1 else 0})"

def main():
    files = sorted(glob(os.path.join(LOG_DIR, "*.txt")))
    if not files:
        print("No log files found.")
        return
    print(f"Number of log files: {len(files)}\n")

    # Col widths for pretty printing
    FILE_W = 32
    COL_W = 22
    DELTA_W = 28

    # Header
    print(
        f"{'File':<{FILE_W}}"
        f"| {'AppStart':^{COL_W}}"
        f"| {'BeforeUpload':^{COL_W}}"
        f"| {'PreviewLoaded':^{COL_W}}"
        f"| {'Δ1 (BeforeUpload-AppStart)':^{DELTA_W}}"
        f"| {'Δ2 (Preview-Upload)':^{DELTA_W}}"
    )
    print('-' * (FILE_W + COL_W*3 + DELTA_W*2 + 7))

    stage1_deltas = {'java':[], 'native':[], 'rss':[]}
    stage2_deltas = {'java':[], 'native':[], 'rss':[]}

    for f in files:
        name = os.path.basename(f)
        tags = parse_log(f)
        v0 = tags.get(TAGS[0])
        v1 = tags.get(TAGS[1])
        v2 = tags.get(TAGS[2])

        def fstr(v):
            return f"J:{v['java']:>5.0f} N:{v['native']:>5.0f} R:{v['rss']:>5.0f}" if v else f"{'-':^{COL_W}}"

        d1 = {k: diff(v0[k], v1[k]) if v0 and v1 else None for k in ['java','native','rss']}
        d2 = {k: diff(v1[k], v2[k]) if v1 and v2 else None for k in ['java','native','rss']}
        dstr1 = f"J:{d1['java'] if d1['java'] is not None else '-':>5} N:{d1['native'] if d1['native'] is not None else '-':>5} R:{d1['rss'] if d1['rss'] is not None else '-':>5}"
        dstr2 = f"J:{d2['java'] if d2['java'] is not None else '-':>5} N:{d2['native'] if d2['native'] is not None else '-':>5} R:{d2['rss'] if d2['rss'] is not None else '-':>5}"

        print(
            f"{name:<{FILE_W}}"
            f"| {fstr(v0):<{COL_W}}"
            f"| {fstr(v1):<{COL_W}}"
            f"| {fstr(v2):<{COL_W}}"
            f"| {dstr1:<{DELTA_W}}"
            f"| {dstr2:<{DELTA_W}}"
        )

        for k in ['java','native','rss']:
            stage1_deltas[k].append(d1[k])
            stage2_deltas[k].append(d2[k])

    # Print summary
    print("\n=== Mean increment across all logs (mean ± std) ===")
    print(f"{'Stage':22} | {'Java Heap':>14} | {'Native Heap':>14} | {'RSS':>14}")
    print('-'*70)
    print(f"{'Startup→Create Entry':22} | {summarize(stage1_deltas['java']):>14} | {summarize(stage1_deltas['native']):>14} | {summarize(stage1_deltas['rss']):>14}")
    print(f"{'Create Entry→Preview':22} | {summarize(stage2_deltas['java']):>14} | {summarize(stage2_deltas['native']):>14} | {summarize(stage2_deltas['rss']):>14}")

    # Visualization: Bar chart for mean+std of each stage and heap type
    fig, axes = plt.subplots(1, 3, figsize=(12,4))
    heap_types = ['java', 'native', 'rss']
    heap_names = ['Java Heap', 'Native Heap', 'RSS']
    colors = ['royalblue', 'orange']
    stage_labels = ['Startup→Create', 'Create→Preview']

    for idx, (heap, name) in enumerate(zip(heap_types, heap_names)):
        means = [statistics.mean([x for x in stage1_deltas[heap] if x is not None]),
                 statistics.mean([x for x in stage2_deltas[heap] if x is not None])]
        stds = [statistics.stdev([x for x in stage1_deltas[heap] if x is not None]) if len(stage1_deltas[heap]) > 1 else 0,
                statistics.stdev([x for x in stage2_deltas[heap] if x is not None]) if len(stage2_deltas[heap]) > 1 else 0]
        axes[idx].bar(stage_labels, means, yerr=stds, color=colors, alpha=0.75, capsize=6)
        axes[idx].set_title(name)
        axes[idx].set_ylabel('MB')
        axes[idx].grid(True, axis='y', linestyle='--', alpha=0.4)
        axes[idx].set_ylim(bottom=0)

    plt.tight_layout()
    plt.show()

if __name__ == '__main__':
    main()
