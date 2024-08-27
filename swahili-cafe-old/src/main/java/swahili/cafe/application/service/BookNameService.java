package swahili.cafe.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swahili.cafe.application.model.BookName;
import swahili.cafe.application.repository.BookNameRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BookNameService {
    private final BookNameRepository bookNameRepository;


    public void createBookName(BookName bookName) {
        bookNameRepository.save(bookName);
    }


    public List<BookName> getAllBookNames() {
        return bookNameRepository.findAll();
    }



    public BookName updateBookName(String name, int publicationYear, long id) {
        BookName bookName = findBookName(id);
        bookName.setName(name);
        bookName.setPublicationYear(publicationYear);
        return bookNameRepository.save(bookName);
    }

    public BookName findBookName(long id) {
        BookName book = bookNameRepository.findBookNameById(id);
        if (book == null) {
            throw new RuntimeException("Book name not found");
        }

        return book;
    }


    public void deleteBookName(long id) {
        BookName bookName = findBookName(id);
        bookNameRepository.deleteById(bookName.getId());
    }
}
