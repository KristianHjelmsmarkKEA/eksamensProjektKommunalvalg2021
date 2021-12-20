package eksamensprojekt.kommunalvalg.DTO.response;

import eksamensprojekt.kommunalvalg.models.Candidate;

public class CandidateCreateDTO {

    public Candidate candidate;
    public String errorMessage;
    public boolean failed;

    // success case
    public CandidateCreateDTO(Candidate candidate) { this.candidate = candidate; }

    // failure case
    public CandidateCreateDTO(String errorMessage) {
        this.errorMessage = errorMessage;
        this.failed = true;
    }
}
