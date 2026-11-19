package mate.academy.booklibrary.repository;

import mate.academy.booklibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
