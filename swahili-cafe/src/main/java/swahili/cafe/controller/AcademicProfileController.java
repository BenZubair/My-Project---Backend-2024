package swahili.cafe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swahili.cafe.model.AcademicProfile;
import swahili.cafe.model.Book;
import swahili.cafe.service.AcademicProfileService;

import java.util.List;

@RestController
@RequestMapping("/academic-profiles")
@RequiredArgsConstructor
public class AcademicProfileController {
    private final AcademicProfileService profileService;

    @PostMapping("/create/expert/{expertId}")
    public ResponseEntity<AcademicProfile> createAcademicProfile(@RequestBody AcademicProfile academicProfile, @PathVariable long expertId) {
        AcademicProfile profile = profileService.createAcademicProfile(academicProfile, expertId);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }


//    @GetMapping("/all")
//    public ResponseEntity<List<Book>> getAllBooks() {
//        List<Book> allBooks = profileService.getAllBooks();
//        return new ResponseEntity<>(allBooks, HttpStatus.OK);
//    }

    @GetMapping("/all/expert/{username}")
    public ResponseEntity<List<AcademicProfile>> getAllAcademicProfilesByExpertUsername(@PathVariable String username) {
        List<AcademicProfile> books = profileService.getAllAcademicProfilesByExpertUsername(username);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @GetMapping("/all/expert-id/{expertId}")
    public ResponseEntity<List<AcademicProfile>> getAllAcademicProfilesByExpertUserId(@PathVariable long expertId) {
        List<AcademicProfile> books = profileService.getAllAcademicProfilesByExpertUserId(expertId);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

}
