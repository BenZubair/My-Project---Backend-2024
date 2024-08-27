package swahili.cafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swahili.cafe.model.Expert;
import swahili.cafe.model.TVShow;
import swahili.cafe.repository.TVShowRepository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@Service
@RequiredArgsConstructor
public class TVShowService {
    private final TVShowRepository showRepository;
    private final UserService userService;
    private final EmailService emailService;

    public TVShow createTVShow(TVShow tvShow, long expertId) {
        Expert expert = (Expert) userService.findUserByUserId(expertId);
        tvShow.setExpert(expert);
        tvShow.setCreatedDate(new Date());
        emailService.sendEMailMessage(expert.getFirstName(), expert.getEmail());
        return showRepository.save(tvShow);
    }



    public List<TVShow> getAllTVShows() {
        return showRepository.findAll();
    }


    public TVShow updateTVShow(String title, String description, String time, long showId) {
        TVShow show = findTVShow(showId);
        show.setTitle(title);
        show.setDescription(description);
        show.setTime(time);
        return showRepository.save(show);
    }


    public TVShow findTVShow(long showId) {
        TVShow show = showRepository.findTVShowByShowId(showId);
        if (show == null) {
            throw new RuntimeException("TVShow not found");
        }

        return show;
    }


    public void deleteTVShow(long bookId) {
        TVShow show = findTVShow(bookId);
        showRepository.deleteById(show.getShowId());
    }
}
