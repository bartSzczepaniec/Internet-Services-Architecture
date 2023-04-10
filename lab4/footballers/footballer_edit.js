import {getBackendUrl} from '../config.js';

window.onload = (event) => {
    getFootballerAndDisplay();

    let editionForm = document.getElementById('edition_form');
    editionForm.addEventListener('submit', event => updateFootballer(event));
};

function getFootballerAndDisplay() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            initForm(JSON.parse(this.responseText));
        }
    };
    let param = new URLSearchParams(window.location.search).get('footballer');
    xhttp.open("GET", getBackendUrl() + '/api/footballers/' + param, true);
    xhttp.send();
}

function initForm(footballer) {
    // Dopisanie nazwy
    let idHeader = document.getElementById('id_pilkarza');
    idHeader.appendChild(document.createTextNode(footballer.id));

    //Wypełnienie pól

    let full_name = document.getElementById('full_name')
    full_name.value = footballer.full_name;

    let age = document.getElementById('age')
    age.value = footballer.age;

    let average_rating = document.getElementById('average_rating')
    average_rating.value = footballer.average_rating;
    
    let club = document.getElementById('club')
    club.value = footballer.club;

}

function updateFootballer(event) {
    event.preventDefault();

    if (document.getElementById('age').value == "" || document.getElementById('average_rating').value == "") {
        window.alert("Wypełnij formularz");
        return;
    }
    if (document.getElementById('full_name').value == "") {
        window.alert("Wypełnij formularz");
        return;
    }
    let param = new URLSearchParams(window.location.search).get('footballer');

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            window.alert("Informacje o piłkarzu zostały zedytowane")
            window.location.href = "footballer_view.html?footballer=" + param;
        }
        if (this.readyState === 4 && (this.status === 400 || this.status === 405 || this.status === 404)) {
            window.alert("Wypełnij formularz poprawnie")
            return;
        }
    };
    
    xhttp.open("PUT", getBackendUrl() + '/api/footballers/' + param, true);

    const request = {   
        'full_name': document.getElementById('full_name').value,
        'age': document.getElementById('age').value,
        'average_rating': document.getElementById('average_rating').value,
        'clubname': document.getElementById('club').value
    };
    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));

}