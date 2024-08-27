package swahili.cafe.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Expert extends User {
//    private String collegeName;
//    private String qualification;
//    private String startYear;
//    private String endYear;
//    private String programName;
    private String otherProgram;
    private String computerKnowledge;
    private boolean isEmployed;
    private String companyName;
    private String position;
    private String employedYear;
}
