import {getBackendUrl} from '../config.js';

window.onload = (event) => {
    getClubsAndDisplay();
};

function getClubsAndDisplay() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText)
            displayClubs(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/clubs', true);
    xhttp.send();

}

function displayClubs(clubs) {
    let tableBody = document.getElementById('clubList');
    clearTable(tableBody)
    clubs.clubs.forEach(club => {
        tableBody.appendChild(createRow(club))
    });
}

function clearTable(tableBody) {
    while (tableBody.firstChild) {
        tableBody.removeChild(tableBody.firstChild);
    }
}

function createRow(club) {
    let tr = document.createElement('tr');

    // Dodanie kolumny nazwy
    let tdName = document.createElement('td');
    tdName.appendChild(document.createTextNode(club))
    tr.appendChild(tdName)

    //Dodanie linku widoku
    let tdView = document.createElement('td');
    let a = document.createElement('a');
    a.appendChild(document.createTextNode('view'));
    a.href = 'club_view.html?club=' + club;
    tdView.appendChild(a);
    tr.append(tdView)

    //Dodanie linku edycji
    let tdEdit = document.createElement('td');
    let aEdit = document.createElement('a');
    aEdit.appendChild(document.createTextNode('edit'));
    aEdit.href = 'club_edit.html?club=' + club;
    tdEdit.appendChild(aEdit);
    tr.append(tdEdit)

    //Dodanie linku usuwania
    let tdDelete= document.createElement('td');
    let aDelete = document.createElement('a');
    aDelete.appendChild(document.createTextNode('delete'));
    aDelete.onclick = function() { deleteClub(club); };
    tdDelete.appendChild(aDelete)
    tr.appendChild(tdDelete)
    return tr;
}

function deleteClub(club) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            getClubsAndDisplay();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/clubs/' + club, true);
    xhttp.send();
}