package swahili.cafe.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swahili.cafe.application.model.BookName;

@Repository
public interface BookNameRepository extends JpaRepository<BookName, Long> {
    BookName findBookNameById(long id);
}
