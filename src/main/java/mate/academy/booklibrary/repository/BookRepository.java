package mate.academy.booklibrary.repository;

import java.util.List;
import mate.academy.booklibrary.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
