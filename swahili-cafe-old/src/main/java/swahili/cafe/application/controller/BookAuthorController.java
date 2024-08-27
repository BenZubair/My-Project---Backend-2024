package swahili.cafe.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swahili.cafe.application.constant.FileConstant;
import swahili.cafe.application.model.BookAuthor;
import swahili.cafe.application.service.BookAuthorService;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/book-authors")
@RequiredArgsConstructor
public class BookAuthorController {
    private final BookAuthorService bookAuthorService;

    @PostMapping("/register")
    public ResponseEntity<BookAuthor> createBookAuthor(
            @RequestParam(value = "fullName", required = false) String fullName,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "numberOfBooksWrote", required = false) int numberOfBooksWrote,
            @RequestParam(value = "bookNames", required = false) String bookNames,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        BookAuthor bookAuthor = bookAuthorService.createBookAuthor(fullName, address, numberOfBooksWrote, bookNames, image);
        return new ResponseEntity<>(bookAuthor, HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<BookAuthor> updateBookAuthor(
            @RequestParam(value = "fullName", required = false) String fullName,
            @RequestParam(value = "address", required = false) String address,
            @RequestParam(value = "numberOfBooksWrote", required = false) int numberOfBooksWrote,
            @RequestParam(value = "bookNames", required = false) String bookNames,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @PathVariable long id) throws IOException {

        BookAuthor bookAuthor = bookAuthorService.updateBookAuthor(fullName, address, numberOfBooksWrote, bookNames, image, id);
        return new ResponseEntity<>(bookAuthor, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<BookAuthor>> getAllBookAuthors(){
        List<BookAuthor> bookAuthors = bookAuthorService.getAllBookAuthors();
        return new ResponseEntity<>(bookAuthors, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookAuthor> findBookAuthor(@PathVariable long id){
        BookAuthor bookAuthors = bookAuthorService.findBookAuthor(id);
        return new ResponseEntity<>(bookAuthors, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookAuthor(@PathVariable long id){
        bookAuthorService.deleteBookAuthor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //    display room image
    @GetMapping(path = "/image/{bookAuthorId}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable String bookAuthorId, @PathVariable String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(FileConstant.BOOK_AUTHOR_FOLDER + bookAuthorId + "/" + fileName));
    }

}
