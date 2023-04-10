import {getBackendUrl} from '../config.js';

window.onload = (event) => {
    let editionForm = document.getElementById('adding_form')
    editionForm.addEventListener('submit', event => addClub(event));
};

function addClub(event) {
    event.preventDefault();

    if (document.getElementById('name').value == "") {
        window.alert("Wpisz nazwę")
        return;
    }
    if (document.getElementById('year_of_foundation').value == "" || document.getElementById('teams_market_value').value == "") {
        window.alert("Wypełnij formularz")
        return;
    }

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            window.alert("Klub został dodany")
            window.location.href = "clubs_list.html";
        }
        if (this.readyState === 4 && this.status === 400) {
            window.alert("Wypełnij formularz poprawnie")
            return;
        }
    };
    let param = new URLSearchParams(window.location.search).get('club');
    xhttp.open("POST", getBackendUrl() + '/api/clubs', true);

    const request = {
        'name': document.getElementById('name').value,
        'year_of_foundation': document.getElementById('year_of_foundation').value,
        'teams_market_value': document.getElementById('teams_market_value').value
    };

    console.log(request)
    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

}