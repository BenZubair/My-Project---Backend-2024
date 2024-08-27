package swahili.cafe.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swahili.cafe.application.model.TVShow;
import swahili.cafe.application.service.TVShowService;

import java.util.List;


@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
public class TVShowController {
    private final TVShowService showService;


    @PostMapping("/create/expert/{expertId}")
    public ResponseEntity<TVShow> createTVShow(@RequestBody TVShow tvShow, @PathVariable  long expertId) {
        TVShow createdShow = showService.createTVShow(tvShow, expertId);
        return new ResponseEntity<>(createdShow, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<TVShow>> getAllTVShows() {
        List<TVShow> allTVShows = showService.getAllTVShows();
        return new ResponseEntity<>(allTVShows, HttpStatus.OK);
    }


    @PutMapping("/update/{showId}")
    public ResponseEntity<TVShow> updateTVShow(@RequestBody TVShow tvShow, @PathVariable  long showId) {
        TVShow updateTVShow = showService.updateTVShow(tvShow.getTitle(), tvShow.getDescription(), tvShow.getTime(), showId);
        return new ResponseEntity<>(updateTVShow, HttpStatus.OK);
    }


    @GetMapping("/{showId}")
    public ResponseEntity<TVShow> findTVShow(@PathVariable long showId) {
        TVShow show = showService.findTVShow(showId);
        return new ResponseEntity<>(show, HttpStatus.OK);
    }


    @DeleteMapping("/{showId}")
    public ResponseEntity<?> deleteTVShow(@PathVariable long showId) {
       showService.deleteTVShow(showId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
