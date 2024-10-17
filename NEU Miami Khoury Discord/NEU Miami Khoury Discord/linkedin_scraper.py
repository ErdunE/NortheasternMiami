# linkedin_scraper.py

from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException, NoSuchElementException
from time import sleep
import logging

def login_to_linkedin(driver, email, password):
    """Login to LinkedIn."""
    driver.get("https://www.linkedin.com/login")
    try:
        WebDriverWait(driver, 10).until(
            EC.presence_of_element_located((By.ID, "username"))
        ).send_keys(email)
        driver.find_element(By.ID, "password").send_keys(password)
        driver.find_element(By.ID, "password").submit()
        sleep(5)
        logging.info("Successfully logged into LinkedIn.")
    except TimeoutException:
        logging.error("Login failed. Timeout occurred.")
        raise

def infinite_scroll(driver, max_attempts=10):
    """Scroll the page multiple times to ensure all content loads."""
    last_height = driver.execute_script("return document.body.scrollHeight")

    for attempt in range(max_attempts):
        logging.info(f"Scrolling attempt: {attempt + 1}")
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
        sleep(5)  # Wait for content to load

        new_height = driver.execute_script("return document.body.scrollHeight")
        if new_height == last_height:
            logging.info("Reached end of the page.")
            break  # Exit if no new content is loaded
        last_height = new_height

def collect_jobs(driver, limit=5):
    """Collect job postings from LinkedIn."""
    url = (
        "https://www.linkedin.com/jobs/search/?f_E=1&f_TPR=r86400&geoId=103644278"
        "&keywords=2025%20Computer%20Science%20Summer%20Intern&origin=JOB_SEARCH_PAGE_JOB_FILTER"
    )
    driver.get(url)
    logging.info("Loading job search page...")

    infinite_scroll(driver)

    # 打印页面 HTML 进行调试
    html_content = driver.page_source
    logging.info("Printing HTML content for debugging...")
    print(html_content[:2000])  # 打印前 2000 个字符

    try:
        job_cards = WebDriverWait(driver, 10).until(
            EC.presence_of_all_elements_located((By.CSS_SELECTOR, "div.job-card-container"))
        )
        logging.info(f"Found {len(job_cards)} job cards.")
    except TimeoutException:
        logging.warning("No job cards found.")
        return []

    jobs = []
    for card in job_cards[:limit]:
        try:
            title = card.find_element(By.CLASS_NAME, "job-card-list__title").text
            company = card.find_element(By.CLASS_NAME, "job-card-container__company-name").text
            location = card.find_element(By.CLASS_NAME, "job-card-container__metadata-item").text
            link = card.find_element(By.TAG_NAME, "a").get_attribute("href")

            job_info = (
                f"**Job Title:** {title}\n"
                f"**Company:** {company}\n"
                f"**Location:** {location}\n"
                f"**Link:** {link}"
            )
            jobs.append(job_info)
        except NoSuchElementException as e:
            logging.error(f"Error extracting job details: {e}")

    return jobs
