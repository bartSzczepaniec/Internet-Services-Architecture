import {getBackendUrl} from '../config.js';

window.onload = (event) => {
    getClubAndDisplay();

    let editionForm = document.getElementById('edition_form')
    editionForm.addEventListener('submit', event => updateClub(event));
};

function getClubAndDisplay() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            initForm(JSON.parse(this.responseText));
        }
    };
    let param = new URLSearchParams(window.location.search).get('club');
    xhttp.open("GET", getBackendUrl() + '/api/clubs/' + param, true);
    xhttp.send();
}

function initForm(club) {
    // Dopisanie nazwy
    let nameHeader = document.getElementById('nazwa_klubu');
    nameHeader.appendChild(document.createTextNode(club.name));

    //Wypełnienie pól

    let inputYear = document.getElementById('year_of_foundation')
    inputYear.value = club.year_of_foundation

    let inputTeamValue = document.getElementById('teams_market_value')
    inputTeamValue.value = club.teams_market_value

}

function updateClub(event) {
    event.preventDefault();

    if (document.getElementById('year_of_foundation').value == "" || document.getElementById('teams_market_value').value == "") {
        window.alert("Wypełnij formularz")
        return;
    }

    let param = new URLSearchParams(window.location.search).get('club');

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            window.alert("Informacje o klubie zostały zedytowane")
            window.location.href = "club_view.html?club=" + param;
        }
        if (this.readyState === 4 && (this.status === 400 || this.status === 405)) {
            window.alert("Wypełnij formularz poprawnie")
            return;
        }
    };
    
    xhttp.open("PUT", getBackendUrl() + '/api/clubs/' + param, true);

    const request = {
        'name': param,
        'year_of_foundation': document.getElementById('year_of_foundation').value,
        'teams_market_value': document.getElementById('teams_market_value').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

}