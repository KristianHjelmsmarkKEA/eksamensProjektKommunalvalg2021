package eksamensprojekt.kommunalvalg.controllers;


import eksamensprojekt.kommunalvalg.models.Candidate;
import eksamensprojekt.kommunalvalg.models.Party;
import eksamensprojekt.kommunalvalg.repositories.CandidatesRepo;
import eksamensprojekt.kommunalvalg.repositories.PartiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Parties {

    @Autowired
    PartiesRepo parties;

    @Autowired
    CandidatesRepo candidates;



    @GetMapping("/parties")
    public Iterable<Party> getParty() {
        return parties.findAll();
    }

    @GetMapping("/parties/{id}")
    public Party getParty(@PathVariable Long id) {
        return parties.findById(id).get();
    }



    /*
    @PutMapping("/parties/{id}")
    public String updateParty(@PathVariable Long id, @RequestBody Party partyToUpdate) {
        if (parties.existsById(id)) {
            partyToUpdate.setId(id);
            parties.save(partyToUpdate);
            return "Party updated";
        } else {
            return "Party not found/updated";
        }
    }
    */

    @PatchMapping("/parties/{id}")
    public String patchParty(@PathVariable Long id, @RequestBody Party partyToUpdate) {
        return parties.findById(id).map( foundParty -> {
            if(partyToUpdate.getPartyName() != null) foundParty.setPartyName(partyToUpdate.getPartyName());
            if(partyToUpdate.getVotes() != 0) foundParty.setVotes(partyToUpdate.getVotes());
            parties.save(foundParty);
            return "Party information updated";
        }).orElse("Party not found/updated");
    }

    @DeleteMapping("/parties/{id}")
    public void deleteParty(@PathVariable Long id) {
        parties.deleteById(id);
    }

}
