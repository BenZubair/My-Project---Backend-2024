package swahili.cafe.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swahili.cafe.application.model.Book;
import swahili.cafe.application.service.BookService;

import java.util.List;


@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("/create/author/{authorId}")
    public ResponseEntity<Book> createBook(@RequestBody Book book, @PathVariable long authorId) {
        Book createdBook = bookService.createBook(book, authorId);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @GetMapping("/all/author/{username}")
    public ResponseEntity<List<Book>> getAllBooksByAuthorUsername(@PathVariable String username) {
        List<Book> books = bookService.getAllBooksByAuthorUsername(username);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @PutMapping("/update/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable long bookId) {
        Book createdBook = bookService.updateBook(book.getTitle(), book.getISBNumber(), book.getPublisher(), book.getPrice(), bookId);
        return new ResponseEntity<>(createdBook, HttpStatus.OK);
    }


    @GetMapping("/{bookId}")
    public ResponseEntity<Book> findBook(@PathVariable long  bookId) {
        Book book = bookService.findBook(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
