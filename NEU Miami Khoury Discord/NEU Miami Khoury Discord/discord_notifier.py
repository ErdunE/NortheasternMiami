# discord_notifier.py

import requests
import logging

def send_to_discord(job_info, webhook_url):
    """Send job information to Discord."""
    data = {"content": job_info}
    headers = {"Content-Type": "application/json"}

    response = requests.post(webhook_url, json=data, headers=headers)
    if response.status_code == 204:
        logging.info("Successfully sent to Discord.")
    else:
        logging.error(f"Failed to send to Discord: {response.status_code} - {response.text}")
