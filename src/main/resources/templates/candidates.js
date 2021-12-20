const candidateTableBody = document.getElementById("candidate-table-tbody")
let partyId;
let candidate;

fetch(localURL + "/candidates/")
    .then(response => response.json())
    .then(candidates => {
        console.log(candidates);
        candidates.map(createCandidateTableRow)

        fetch(localURL + "/parties")
            .then(respose => respose.json())
            .then(parties => {
                console.log(parties);
            });
});

function createCandidateTableRow(candidate) {
    const candidateTableRow = document.createElement("tr");
    candidateTableRow.id = "candidate" + candidate.id;

    candidateTableBody.appendChild(candidateTableRow);
    constructCandidateTableRow(candidateTableRow, candidate);
}

function constructCandidateTableRow(candidateTableRow, candidate) {
    candidateTableRow.innerHTML = `
    <td>
    <a class="a-candidate-name">${candidate.name}</a>
    </td>
    <td>
    <a class="a-candidate-party">${candidate.party.partyName}</a>
    </td>
    <td>
     <button id="update-button-${candidate.id}">Update</button>
     <a href="index.html"><button onclick="deleteCandidate(${candidate.id})">❌</button></a>
    </td>
    `;

    document.getElementById(`update-button-${candidate.id}`)
        .addEventListener("click", () => editCandidateInformation(candidate))

}

function deleteCandidate(candidateId) {
    fetch(localURL + "/candidates/" + candidateId, {
        method: "DELETE"
    }).then(response => {
        if (response.status === 200) {
            document.getElementById("candidate removed").remove();
        } else {
            console.log(response.status);
        }
    });
}




function addNewCandidate() {
    console.log(document.getElementById("party-dropdown").value)
    partyId = document.getElementById("party-dropdown").value;

    const newCandidate = {
        name: document.getElementById(`new-candidate-name`).value,
        party_id: document.getElementById("party-dropdown").value,
    };
    console.log(newCandidate);

    fetch(localURL + "/candidates/" + partyId, {
        method: "POST",
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(newCandidate)
    })
        .then(response => {
            if (response.status === 200) {
                console.log("New candidate added");
            } else {
                console.log("New candidate not created", response.status);
            }
        })
        .catch(error => console.log("Network related error: ", error));
}


function searchFunction() {
    var input, filter, table, tr, td, td1, i;
    input = document.getElementById("name-and-candidate-search");
    filter = input.value.toUpperCase();
    table = document.getElementById("candidate-table");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        td1 = tr[i].getElementsByTagName("td")[1];
        if (td) {
            if ((td.textContent.toUpperCase().indexOf(filter) > -1) ||
               (td1.textContent.toUpperCase().indexOf(filter) > -1)) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
