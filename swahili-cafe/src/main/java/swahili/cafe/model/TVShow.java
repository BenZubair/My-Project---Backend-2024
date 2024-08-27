package swahili.cafe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tv_shows")
public class TVShow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;
    private String title;
    private String description;
    private String time;
    private Date createdDate;

    @OneToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;
}
