package eksamensprojekt.kommunalvalg.repositories;

import eksamensprojekt.kommunalvalg.models.Candidate;
import eksamensprojekt.kommunalvalg.models.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartiesRepo extends JpaRepository<Party, Long> {


}
