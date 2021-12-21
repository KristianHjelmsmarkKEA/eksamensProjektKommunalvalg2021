const voteResultTableBody = document.getElementById("vote-result-table-tbody");
const totalVotes = 22903;

fetch(localURL + "/parties")
.then(response => response.json())
.then(parties => {
    console.log(parties);
    parties.map(createVoteResultTableRow)
})

function createVoteResultTableRow(parties) {
    const voteResultTableRow = document.createElement("tr");
    voteResultTableRow.id = parties.id;

    voteResultTableBody.appendChild(voteResultTableRow);
    constructVoteResultTableRow(voteResultTableRow, parties);
}

function constructVoteResultTableRow(voteResultTableRow, parties) {
    voteResultTableRow.innerHTML = `
    <td>
    <a class="a-party-name">${escapeHTML(parties.partyName)}</a>
    </td>
    <td>
    <a class="a-party-votes">${escapeHTML(parties.votes.toString())}</a>
    </td>
    <td>
       <p id="percentage">${escapeHTML(((100* parties.votes) / totalVotes).toFixed(2))}</p>
    </td>
    `;
}
