package swahili.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import swahili.cafe.model.AcademicProfile;

import java.util.List;

@Repository
public interface AcademicProfileRepository extends JpaRepository<AcademicProfile, Long> {
    AcademicProfile findAcademicProfileByProfileId(long showId);

    @Query("SELECT ap FROM AcademicProfile ap JOIN ap.expert e WHERE e.username = ?1")
    List<AcademicProfile> getAllAcademicProfilesByExpertUsername(String username);

    @Query("SELECT ap FROM AcademicProfile ap JOIN ap.expert e WHERE e.userId = ?1")
    List<AcademicProfile> getAllAcademicProfilesByExpertUserId(long userId);
}
