package mate.academy.booklibrary.service;

import java.util.List;
import mate.academy.booklibrary.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
