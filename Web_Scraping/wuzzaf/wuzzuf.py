import requests
from bs4 import BeautifulSoup
import csv
from itertools import zip_longest

page = requests.get("https://wuzzuf.net/search/jobs/?a=hpb%7Cspbg&q=Python")

src = page.content
soup = BeautifulSoup(src, 'lxml')

cards = soup.find_all('div', {"class" : "css-1gatmva"})

title = []
company = []
location = []
skills = []
links = []

for i in range(len(cards)):
    title.append(cards[i].find('h2', {"class": "css-m604qf"}).text)
    company.append(cards[i].find('a', {"class": "css-17s97q8"}).text)
    location.append(cards[i].find('span', {"class": "css-5wys0k"}).text)
    skills.append(cards[i].find('div', {"class": "css-y4udm8"}).text)
    links.append(cards[i].find('h2', {"class": "css-m604qf"}).find('a').attrs['href'])

exported = zip_longest(title, company, location, skills, links)
with open('jobs.csv', 'w', newline='') as csvfile:
    writer = csv.writer(csvfile)
    writer.writerow(["Title", "Company", "Location", "Skills", "Links"])
    writer.writerows(exported)

