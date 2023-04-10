import {getBackendUrl} from '../config.js';

window.onload = (event) => {
    getClubAndDisplay();
};

function getClubAndDisplay() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            displayInfo(JSON.parse(this.responseText));
        }
    };
    let param = new URLSearchParams(window.location.search).get('club');
    xhttp.open("GET", getBackendUrl() + '/api/clubs/' + param, true);
    xhttp.send();
}

function displayInfo(club) {
    // Dopisanie nazwy
    let divInfo = document.getElementById('club_info');
    while (divInfo.firstChild) {
        divInfo.removeChild(divInfo.firstChild);
    }
    let nameHeader = document.createElement('h4');
    nameHeader.appendChild(document.createTextNode(club.name));
    divInfo.appendChild(nameHeader);

    let yearHeader = document.createElement('h4');
    yearHeader.appendChild(document.createTextNode('Rok utworzenia - ' + club.year_of_foundation));
    divInfo.appendChild(yearHeader);

    let valueHeader = document.createElement('h4');
    valueHeader.appendChild(document.createTextNode('Wartość drużyny - ' + club.teams_market_value));
    divInfo.appendChild(valueHeader);

    let addButton = document.getElementById('addFootballer');
    addButton.href = '../footballers/footballer_add.html?club=' + club.name;

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
            showFootballers(JSON.parse(this.responseText), club);
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/clubs/' + club.name + '/footballers/', true);
    xhttp.send();
    
}

function showFootballers(footballers, club) {
    let tableBody = document.getElementById('footballerList');
    clearTable(tableBody)
    footballers.footballers.forEach(footballer => {
        tableBody.appendChild(createRow(footballer, footballers, club))
    });

}

function clearTable(tableBody) {
    while (tableBody.firstChild) {
        tableBody.removeChild(tableBody.firstChild);
    }
}

function createRow(footballer, footballers, club) {
    let tr = document.createElement('tr');

    // Dodanie kolumny nazwy
    let tdName = document.createElement('td');
    tdName.appendChild(document.createTextNode(footballer.full_name))
    tr.appendChild(tdName)

    //Dodanie linku widoku
    let tdView = document.createElement('td');
    let a = document.createElement('a');
    a.appendChild(document.createTextNode('view'));
    a.href = '../footballers/footballer_view.html?footballer=' + footballer.id;
    tdView.appendChild(a);
    tr.append(tdView)

    //Dodanie linku edycji
    let tdEdit = document.createElement('td');
    let aEdit = document.createElement('a');
    aEdit.appendChild(document.createTextNode('edit'));
    aEdit.href = '../footballers/footballer_edit.html?footballer=' + footballer.id;
    tdEdit.appendChild(aEdit);
    tr.append(tdEdit)

    //Dodanie linku usuwania
    let tdDelete= document.createElement('td');
    let aDelete = document.createElement('a');
    aDelete.appendChild(document.createTextNode('delete'));
    aDelete.onclick = function() { deleteFootballer(footballer, footballers, club); };
    tdDelete.appendChild(aDelete)
    tr.appendChild(tdDelete)
    return tr;
}

function deleteFootballer(footballer, footballers, club) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            getClubAndDisplay()
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/footballers/' + footballer.id, true);
    xhttp.send();
}