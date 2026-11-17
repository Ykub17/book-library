package mate.academy.booklibrary.service;

import java.util.List;
import mate.academy.booklibrary.dto.BookDto;
import mate.academy.booklibrary.dto.CreateBookRequestDto;
import mate.academy.booklibrary.dto.UpdateBookRequestDto;

public interface BookService {
    List<BookDto> getAll();

    BookDto getBookById(Long id);

    BookDto createBook(CreateBookRequestDto bookDto);

    void deleteBook(Long id);

    BookDto updateBook(Long id, UpdateBookRequestDto requestDto);

}
