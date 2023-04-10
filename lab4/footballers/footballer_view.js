import {getBackendUrl} from '../config.js';

window.onload = (event) => {
    getFootballerAndDisplay();
};

function getFootballerAndDisplay() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            displayInfo(JSON.parse(this.responseText));
        }
    };
    let param = new URLSearchParams(window.location.search).get('footballer');
    xhttp.open("GET", getBackendUrl() + '/api/footballers/' + param, true);
    xhttp.send();
}

function displayInfo(footballer) {
    // Dopisanie nazwy
    let divInfo = document.getElementById('player_info');
    let idHeader = document.createElement('h4');
    idHeader.appendChild(document.createTextNode('ID: ' + footballer.id));
    divInfo.appendChild(idHeader);

    let nameHeader = document.createElement('h4');
    nameHeader.appendChild(document.createTextNode('Pełne imię i nazwisko: ' + footballer.full_name));
    divInfo.appendChild(nameHeader);

    let ageHeader = document.createElement('h4');
    ageHeader.appendChild(document.createTextNode('Wiek zawodnika: ' + footballer.age));
    divInfo.appendChild(ageHeader);

    let ratingHeader = document.createElement('h4');
    ratingHeader.appendChild(document.createTextNode('Średnia ocena: ' + footballer.average_rating));
    divInfo.appendChild(ratingHeader);

    let clubHeader = document.createElement('h4');
    let aClub = document.createElement('a');
    if (footballer.club != "null" || footballer.club != "")
    {
        aClub.href = "../clubs/club_view.html?club=" + footballer.club;
    }
    aClub.appendChild(document.createTextNode(footballer.club));
    clubHeader.appendChild(document.createTextNode('Klub: '));
    clubHeader.appendChild(aClub);
    divInfo.appendChild(clubHeader);
}
