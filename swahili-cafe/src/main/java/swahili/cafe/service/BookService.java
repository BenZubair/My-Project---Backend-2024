package swahili.cafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swahili.cafe.model.Book;
import swahili.cafe.model.User;
import swahili.cafe.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final UserService userService;

    public Book createBook(Book book, long authorId) {
        User author = userService.findUserByUserId(authorId);
        book.setAuthor(author);
        return bookRepository.save(book);
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAllBooksByAuthorUsername(String username) {
        return bookRepository.getAllBooksByAuthorUsername(username);
    }


    public Book updateBook(String title, String ISBNumber, String publisher, double price, long bookId) {
        Book updateBook = findBook(bookId);
        updateBook.setTitle(title);
        updateBook.setISBNumber(ISBNumber);
        updateBook.setPublisher(publisher);
        updateBook.setPrice(price);
        return bookRepository.save(updateBook);
    }

    public Book findBook(long bookId) {
        Book book = bookRepository.findBookByBookId(bookId);
        if (book == null) {
            throw new RuntimeException("Book not found");
        }

        return book;
    }


    public void deleteBook(long bookId) {
        Book book = findBook(bookId);
        bookRepository.deleteById(book.getBookId());
    }
}
