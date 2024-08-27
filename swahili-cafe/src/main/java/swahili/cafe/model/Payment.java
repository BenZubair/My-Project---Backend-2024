package swahili.cafe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private double paymentAmount;
    private String status;
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "expert_id")
    private Expert expert;



    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
