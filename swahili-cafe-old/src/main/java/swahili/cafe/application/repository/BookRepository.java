package swahili.cafe.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swahili.cafe.application.model.Book;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByBookId(long bookId);

    @Query("SELECT b FROM Book b JOIN b.author a WHERE a.username = ?1")
    List<Book> getAllBooksByAuthorUsername(String username);
}
