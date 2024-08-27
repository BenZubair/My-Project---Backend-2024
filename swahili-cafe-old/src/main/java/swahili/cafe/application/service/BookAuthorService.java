package swahili.cafe.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import swahili.cafe.application.constant.FileConstant;
import swahili.cafe.application.model.BookAuthor;
import swahili.cafe.application.repository.BookAuthorRepository;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookAuthorService {
    private final BookAuthorRepository bookAuthorRepository;
    private final BookNameService bookNameService;

//    public BookAuthor createBookAuthor(String fullName, String address, int numberOfBooksWrote, MultipartFile image, String name, int publicationYear) throws IOException {
//        BookName bookName = new BookName();
//        bookName.setName(name);
//        bookName.setPublicationYear(publicationYear);
//        bookNameService.createBookName(bookName);
//        BookAuthor bookAuthor = new BookAuthor();
//        bookAuthor.setFullName(fullName);
//        bookAuthor.setAddress(address);
//        bookAuthor.setNumberOfBooksWrote(numberOfBooksWrote);
//        bookAuthor.setBookNamesToBookAuthor(bookName);
//        bookAuthorRepository.save(bookAuthor);
//        saveBookAuthorImage(bookAuthor, image);
//        return bookAuthor;
//    }

    public BookAuthor createBookAuthor(String fullName, String address, int numberOfBooksWrote, String bookNames, MultipartFile image) throws IOException {
        BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setFullName(fullName);
        bookAuthor.setAddress(address);
        bookAuthor.setNumberOfBooksWrote(numberOfBooksWrote);
        bookAuthor.setBookNames(bookNames);
        bookAuthorRepository.save(bookAuthor);
        saveBookAuthorImage(bookAuthor, image);
        return bookAuthor;
    }


    private void saveBookAuthorImage(BookAuthor bookAuthor, MultipartFile image) throws IOException {
        if (image != null) {
            Path roomFolder = Paths.get(FileConstant.BOOK_AUTHOR_FOLDER + bookAuthor.getId()).toAbsolutePath().normalize();
            if (!Files.exists(roomFolder)) {
                Files.createDirectories(roomFolder);
                log.info("Directory created for " + roomFolder);
            }

            Files.deleteIfExists(Paths.get(FileConstant.BOOK_AUTHOR_FOLDER + bookAuthor.getId() + "." + "jpg"));
            Files.copy(image.getInputStream(), roomFolder.resolve(bookAuthor.getId() + "." + "jpg"), REPLACE_EXISTING);
            bookAuthor.setImage(setBookImage(bookAuthor.getId()));
            bookAuthorRepository.save(bookAuthor);
            log.info(image.getOriginalFilename());
        }
    }

    private String setBookImage(long bookAuthorId) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(FileConstant.BOOK_AUTHOR_IMAGE_PATH + bookAuthorId + "/" + bookAuthorId + "." + "jpg").toUriString();
    }


    public List<BookAuthor> getAllBookAuthors() {
        return bookAuthorRepository.findAll();
    }


    public BookAuthor updateBookAuthor(String fullName, String address, int numberOfBooksWrote, String bookNames, MultipartFile image, long bookId) throws IOException {
        BookAuthor bookAuthor = findBookAuthor(bookId);
        bookAuthor.setFullName(fullName);
        bookAuthor.setAddress(address);
        bookAuthor.setNumberOfBooksWrote(numberOfBooksWrote);
        bookAuthor.setBookNames(bookNames);
        bookAuthorRepository.save(bookAuthor);
        saveBookAuthorImage(bookAuthor, image);
        return bookAuthor;
    }

    public BookAuthor findBookAuthor(long id) {
        BookAuthor bookAuthor = bookAuthorRepository.findBookAuthorById(id);
        if (bookAuthor == null) {
            throw new RuntimeException("Book author not found");
        }

        return bookAuthor;
    }


    public void deleteBookAuthor(long id) {
        BookAuthor bookAuthor = findBookAuthor(id);
        bookAuthorRepository.deleteById(bookAuthor.getId());
    }
}
