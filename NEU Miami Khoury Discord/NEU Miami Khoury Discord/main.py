# main.py

import logging
import random
from time import sleep
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from linkedin_scraper import login_to_linkedin, collect_jobs
from discord_notifier import send_to_discord

# Define credentials and webhook URL directly here
LINKEDIN_EMAIL = "email"
LINKEDIN_PASSWORD = "password"
DISCORD_WEBHOOK_URL = "https://discord.com/api/webhooks/1296315533058248795/yxrQReJWsCcwtE-DbF8CcnKNmMdw3l3CJ2kBE-FNjgoL0bge6PT-b4fFKoBF6sRngmMF"

# Logging configuration
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

def random_delay():
    """Introduce a random delay to mimic human behavior."""
    delay = random.uniform(3, 7)
    logging.info(f"Sleeping for {delay:.2f} seconds...")
    sleep(delay)

def create_driver():
    """Create and return a WebDriver instance."""
    options = webdriver.ChromeOptions()
    options.add_argument("--headless")
    options.add_argument("--no-sandbox")
    options.add_argument("--disable-dev-shm-usage")
    options.add_argument("--disable-blink-features=AutomationControlled")

    driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
    return driver

def main():
    """Main entry point for the script."""
    driver = create_driver()

    try:
        logging.info("Logging into LinkedIn...")
        login_to_linkedin(driver, LINKEDIN_EMAIL, LINKEDIN_PASSWORD)

        random_delay()

        logging.info("Collecting jobs...")
        jobs = collect_jobs(driver, limit=3)

        if jobs:
            for job in jobs:
                send_to_discord(job, DISCORD_WEBHOOK_URL)
        else:
            logging.warning("No jobs found or an error occurred.")
    except Exception as e:
        logging.error(f"An error occurred: {e}")
    finally:
        driver.quit()
        logging.info("Driver closed.")

if __name__ == "__main__":
    main()
