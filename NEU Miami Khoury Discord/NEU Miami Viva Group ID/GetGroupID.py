import base64
import json

encoded_str = "eyJfdHlwZSI6Ikdyb3VwIiwiaWQiOiIxOTc2Njg2MDE4NTYifQ"

missing_padding = len(encoded_str) % 4
if missing_padding != 0:
    encoded_str += '=' * (4 - missing_padding)

decoded_bytes = base64.b64decode(encoded_str)
decoded_str = decoded_bytes.decode('utf-8')

group_info = json.loads(decoded_str)
print(group_info)