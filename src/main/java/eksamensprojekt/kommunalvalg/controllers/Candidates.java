package eksamensprojekt.kommunalvalg.controllers;


import eksamensprojekt.kommunalvalg.DTO.response.CandidateCreateDTO;
import eksamensprojekt.kommunalvalg.models.Candidate;
import eksamensprojekt.kommunalvalg.models.Party;
import eksamensprojekt.kommunalvalg.repositories.CandidatesRepo;
import eksamensprojekt.kommunalvalg.repositories.PartiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Candidates {

    @Autowired
    CandidatesRepo candidates;

    @Autowired
    PartiesRepo parties;

    @GetMapping("/candidates")
    public Iterable<Candidate> getCandidate() {
        return candidates.findAll();
    }

    @GetMapping("/candidates/{id}")
    public Candidate getCandidate(@PathVariable Long id) {
        return candidates.findById(id).get();
    }

    @GetMapping("/candidates/parties/{id}")
    public List<Candidate> getPartyCandidates(@PathVariable Long id) {
        return candidates.findCandidatesByParty(id);
    }

    @PostMapping("/candidates/{partyId}")
    public CandidateCreateDTO addNewCandidate(@PathVariable Long partyId, @RequestBody Candidate newCandidate) {
        return parties.findById(partyId).map(party -> {
                    newCandidate.setId(null);
                    newCandidate.setParty(party);
                    Candidate addCandidate = candidates.save(newCandidate);
                    return new CandidateCreateDTO(addCandidate);
                }
        ).orElse(new CandidateCreateDTO("Did not add new candidate with PartyId"));
    }



    @PutMapping("/candidates/{id}")
    public String updateCandidate(@PathVariable Long id, @RequestBody Candidate candidateToUpdate) {
        if (candidates.existsById(id)) {
            candidateToUpdate.setId(id);
            candidates.save(candidateToUpdate);
            return "Candidate updated";
        } else {
            return "Candidate not found/updated";
        }
    }

    @PatchMapping("/candidates/{id}")
    public String patchCandidate(@PathVariable Long id, @RequestBody Candidate candidateToUpdate) {
        return candidates.findById(id).map( foundCandidate -> {
            if(candidateToUpdate.getName() != null) foundCandidate.setName(candidateToUpdate.getName());
            if(candidateToUpdate.getParty() != null) foundCandidate.setParty(candidateToUpdate.getParty());
            candidates.save(foundCandidate);
            return "Candidate updated";
        }).orElse("Candidate not found/updated");
    }

    @DeleteMapping("/candidates/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        candidates.deleteById(id);
    }

}
