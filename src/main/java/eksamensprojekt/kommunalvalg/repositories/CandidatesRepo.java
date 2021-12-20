package eksamensprojekt.kommunalvalg.repositories;

import eksamensprojekt.kommunalvalg.models.Candidate;
import eksamensprojekt.kommunalvalg.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CandidatesRepo extends JpaRepository<Candidate, Long> {

    @Query(value = "SELECT * FROM candidates WHERE party_id = ?", nativeQuery = true)
    List<Candidate> findCandidatesByParty(long partyId);




}
