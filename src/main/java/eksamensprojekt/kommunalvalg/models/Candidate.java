package eksamensprojekt.kommunalvalg.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.Nullable;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Data
@Table(name="candidates")
@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "party_id")
    private Party party;

}
