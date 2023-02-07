import json
from dataclasses import dataclass
from typing import Literal

import requests
from bs4 import BeautifulSoup

url = "https://www.fit.vut.cz/study/program/7886/.cs"
req = requests.get(url)
soup = BeautifulSoup(req.text, "html.parser")


@dataclass
class Predmet:
    nazov: str
    skratka: str
    kredity: int
    povinnost: str
    zakoncenie: str
    fakulta: str
    semester: str  # letny/zimny
    rocnik: int


predmety: list[Predmet] = []
semester: Literal["zimny", "letny"] = "zimny"
rocnik: int = 1

if __name__ == '__main__':
    tables = soup.select("div.table-responsive:nth-child(2) table")
    for table in tables:
        predmety_semestru = table.select("tr")
        for predmet in list(predmety_semestru)[1:]:
            table_data = predmet.select("td")
            predmety.append(Predmet(
                skratka=predmet.select("th")[0].text,
                nazov=table_data[0].text,
                kredity=int(table_data[1].text),
                povinnost=table_data[2].text,
                zakoncenie=table_data[3].text,
                fakulta=table_data[4].text,
                semester=semester,
                rocnik=rocnik
            ))
        if semester == "letny":
            rocnik += 1
            semester = "zimny"
        else:
            semester = "letny"

    json_string = json.dumps([obj.__dict__ for obj in predmety])
    with open("predmety.json", "w") as file:
        file.write(json_string)
