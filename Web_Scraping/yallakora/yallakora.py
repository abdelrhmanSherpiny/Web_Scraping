import requests
from bs4 import BeautifulSoup
import csv

date = input("Enter date in MM/DD/YYYY format: ")
page = requests.get(f"https://www.yallakora.com/match-center?date={date}#days")

def parser(page):
    soup = BeautifulSoup(page.content, 'lxml')
    match_details = []
    champs = soup.find_all('div', {'class': 'matchCard'})
    for champ in champs:

        title  = champ.find('h2').text.strip()
        matches = champ.find_all('div', {'class':'teamsData'})
        for i in range(len(matches)):

            teamA = matches[i].find('div', {'class': 'teamA'}).text.strip()
            teamB = matches[i].find('div', {'class': 'teamB'}).text.strip()
            result = matches[i].find_all('span', {'class': 'score'})
            score = f"{result[0].text.strip()} - {result[1].text.strip()}"
            time = matches[i].find('span', {'class': 'time'}).text.strip()
            match_details.append({"بطولة" : title, "الفريق الاول" : teamA, "الفريق الثانى" : teamB, "الوقت" : time, "النتيجة" : score})
    return match_details

def csv_writer(match_details):
    keys = match_details[0].keys()

    with open('match_details.csv','w', newline='',  encoding='utf-8') as csvMatch:
        writer = csv.DictWriter(csvMatch, keys)
        writer.writeheader()
        writer.writerows(match_details)
        print("file created")


csv_writer(parser(page))