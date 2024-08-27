//package swahili.cafe.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "book_authors")
//public class BookAuthor {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String fullName;
//    private String address;
//    private int numberOfBooksWrote;
//    private String bookNames;
//    private String image;
//
//    @ManyToOne
//    @JoinColumn(name = "coordinator_id")
//    private User coordinator;
//
//
//}
