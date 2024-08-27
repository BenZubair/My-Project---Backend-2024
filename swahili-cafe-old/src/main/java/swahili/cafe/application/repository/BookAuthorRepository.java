package swahili.cafe.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swahili.cafe.application.model.BookAuthor;

@Repository
public interface BookAuthorRepository extends JpaRepository<BookAuthor, Long> {
    BookAuthor findBookAuthorById(long id);
}
