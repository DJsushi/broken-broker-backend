import json
from dataclasses import dataclass
from pprint import pprint
from typing import Optional

import requests
from bs4 import BeautifulSoup

url = "https://www.fit.vut.cz/study/program/7886/.en"
req = requests.get(url)
soup = BeautifulSoup(req.text, "html.parser")

@dataclass
class Termin:
    predmet: str
    day: str
    type: str
    weeks: str
    room: str
    start: str
    end: str
    capacity: Optional[int]
    info: str


terminy: list[Termin] = []

if __name__ == '__main__':
    tables = soup.select("div.table-responsive:nth-child(2) table")
    for table in tables:
        predmety_semestru = table.select("tr")
        for predmet in list(predmety_semestru)[1:]:
            a = predmet.select("td")[0].select("a")[0]["href"]
            predmet_skratka = predmet.select("th")[0].text
            predmet_req = requests.get(a)
            predmet_soup = BeautifulSoup(predmet_req.text, "html.parser")
            predmet_terminy = predmet_soup.select("#schedule > tbody:nth-child(2) tr")
            for predmet_termin in predmet_terminy:
                typ = predmet_termin.select("span")[0].text
                if typ == "exam":
                    continue
                table_datas = predmet_termin.select("td")
                terminy.append(Termin(
                    predmet=predmet_skratka,
                    day=predmet_termin.select("th")[0].text,
                    type=typ,
                    weeks=table_datas[1].text,
                    room=", ".join([x.text for x in table_datas[2].select("a")]),
                    start=table_datas[3].text,
                    end=table_datas[4].text,
                    capacity=(int(table_datas[5].text) if table_datas[5].text != "" else None),
                    info=table_datas[-1].text
                ))
                pprint(terminy[-1])

    json_string = json.dumps([obj.__dict__ for obj in terminy])
    with open("terminy.json", "w") as file:
        file.write(json_string)
