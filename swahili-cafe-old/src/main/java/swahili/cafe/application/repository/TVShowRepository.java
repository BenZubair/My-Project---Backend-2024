package swahili.cafe.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swahili.cafe.application.model.TVShow;

@Repository
public interface TVShowRepository extends JpaRepository<TVShow, Long> {
    TVShow findTVShowByShowId(long showId);
}
