function editCandidateInformation(candidate) {
    const candidateTableRowToUpdate = document.getElementById("candidate"+candidate.id);
    console.log(candidate);

    candidateTableRowToUpdate.innerHTML = `
    <td>
        <input id="update-candidate-name-${candidate.id}" value="${candidate.name}" placeholder="${candidate.name}">
    </td>
    <td>
        <select id="update-candidate-party-dropdown" value="${candidate.id}">
        <option value="1">Socialdemokratiet</option>
        <option value="2">Radikale Venstre</option>
        <option value="3">Den Konservative Folkeparty</option>
        <option value="4">Nye Borgerlige</option>
        <option value="5">SF - Socialistisk Folkeparty</option>
        <option value="6">Liberal Alliance</option>
        <option value="7">Dansk Folkeparti</option>
        <option value="8">Venstre, Danmarks Liberale Parti</option>
        <option value="9">Frihedslisten</option>
        <option value="10">Enhedslisten - De Rød-Grønne</option>
        <option value="11">Alternativet</option>
    </select>
    </td>
    <td>
        <a href="index.html"><button onclick="updateCandidateInformation(${candidate.id})">Save new candidate information</button></a>
        <button id="cancel-button-${candidate.id}">Cancel</button>
    </td>
    `
    document.getElementById(`cancel-button-${candidate.id}`)
        .addEventListener("click", () => cancelEditCandidate(candidate))
}
function cancelEditCandidate(candidate) {
    const candidateTableRow = document.getElementById("candidate"+candidate.id)
    constructCandidateTableRow(candidateTableRow, candidate);
}



function updateCandidateInformation(candidateId) {
    console.log("Hej")
    partyId = document.getElementById("party-dropdown").value;
    const candidateInformationToUpdate = {
        id: candidateId,
        name: document.getElementById(`update-candidate-name-${candidateId}`).value,
        party_id: document.getElementById("update-candidate-party-dropdown").value,
    }
    console.log(candidateInformationToUpdate);

    fetch(localURL + "/candidates/" + candidateId, {
        method: "PATCH",
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify(candidateInformationToUpdate)
    })
        .then(response => {
            console.log(response);
            if (response.status === 200) {
                console.log("New candidate information changed")
            } else {
                console.log("Candidate information was not updated", response.status);
            }
        })
}