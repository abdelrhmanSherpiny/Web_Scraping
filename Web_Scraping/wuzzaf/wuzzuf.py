from math import ceil

import requests
from bs4 import BeautifulSoup
import csv
from itertools import zip_longest

title = []
company = []
location = []
skills = []
links = []
page_num = 0

while True:

    page = requests.get(f"https://wuzzuf.net/search/jobs/?a=hpb&q=python&start={page_num}")

    src = page.content
    soup = BeautifulSoup(src, 'lxml')
    page_limit = int(soup.find("strong").text)
    cards = soup.find_all('div', {"class": "css-1gatmva"})

    for i in range(len(cards)):
        title.append(cards[i].find('h2', {"class": "css-m604qf"}).text)
        company.append(cards[i].find('a', {"class": "css-17s97q8"}).text)
        location.append(cards[i].find('span', {"class": "css-5wys0k"}).text)
        skills.append(cards[i].find('div', {"class": "css-y4udm8"}).text)
        links.append(cards[i].find('h2', {"class": "css-m604qf"}).find('a').attrs['href'])
    page_num += 1
    if page_num > page_limit//15:
        break

exported = zip_longest(title, company, location, skills, links)
print(exported)
with open('jobs.csv', 'w', newline='',encoding='utf-8') as csvfile:
    writer = csv.writer(csvfile)
    writer.writerow(["Title", "Company", "Location", "Skills", "Links"])
    writer.writerows(exported)

