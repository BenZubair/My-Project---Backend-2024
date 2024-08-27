package swahili.cafe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "academic_profiles")
public class AcademicProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    private String collegeName;
    private String qualification;
    private String programName;
    private String startYear;
    private String endYear;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;
}
