import {getBackendUrl} from '../config.js';

window.onload = (event) => {
    let editionForm = document.getElementById('adding_form')
    editionForm.addEventListener('submit', event => addFootballer(event));
};

function addFootballer(event) {
    event.preventDefault();

    if (document.getElementById('full_name').value == "") {
        window.alert("Wpisz imię i nazwisko")
        return;
    }
    if (document.getElementById('average_rating').value == "" || document.getElementById('age').value == "") {
        window.alert("Wypełnij formularz")
        return;
    }
    let param = new URLSearchParams(window.location.search).get('club');
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            window.alert("Piłkarz został dodany")
            window.location.href = "../clubs/club_view.html?club=" + param;
        }
        if (this.readyState === 4 && this.status === 400) {
            window.alert("Wypełnij formularz poprawnie")
            return;
        }
    };

    xhttp.open("POST", getBackendUrl() + '/api/clubs/'+ param + '/footballers', true);

    const request = {
        'full_name': document.getElementById('full_name').value,
        'age': document.getElementById('age').value,
        'average_rating': document.getElementById('average_rating').value

    };

    console.log(request)
    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

}