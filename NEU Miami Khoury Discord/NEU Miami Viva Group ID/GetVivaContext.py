import requests

# API URL
url = "https://api.yammer.com/v1/messages/in_group/197668601856.json"

# Headers with access token
headers = {
    "Authorization": "Bearer 1581644-lgdtd2uPu9AJUE0YxFID0g"
}

# Send GET request
response = requests.get(url, headers=headers)

# Print response
print(response.status_code)
print(response.json())