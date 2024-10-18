import requests

url = "https://api.yammer.com/v1/groups.json"
headers = {
    "Authorization": "Bearer 1581644-lgdtd2uPu9AJUE0YxFID0g"
}

response = requests.get(url, headers=headers)

print(response.status_code)
print(response.text)