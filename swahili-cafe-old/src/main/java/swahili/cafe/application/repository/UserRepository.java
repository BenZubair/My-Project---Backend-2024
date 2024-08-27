package swahili.cafe.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import swahili.cafe.application.model.Expert;
import swahili.cafe.application.model.User;


import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User findUserByUserId(long userId);

    @Query("SELECT COUNT(*) FROM User u")
    Integer getTotalUsers();

    @Query("SELECT e FROM Expert e WHERE e.role = 'ROLE_EXPERT'")
    List<Expert> getAllExperts();

    @Query("SELECT COUNT(*) FROM User u WHERE u.role = 'ROLE_COORDINATOR'")
    Integer getTotalStaffs();

    @Query("SELECT COUNT(*) FROM User u WHERE u.role = 'ROLE_AUTHOR'")
    Integer getTotalAuthors();

    @Query("SELECT COUNT(*) FROM User u WHERE u.role = 'ROLE_EXPERT'")
    Integer getTotalExperts();
}
