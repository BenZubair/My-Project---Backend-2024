package swahili.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swahili.cafe.model.Expert;
import swahili.cafe.model.User;

import java.util.List;

@Repository
public interface ExpertRepository extends UserRepository {

}
