package mate.academy.booklibrary.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.booklibrary.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByIsDeletedFalse();

    Optional<Book> findByIdAndIsDeletedFalse(Long id);
}
