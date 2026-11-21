
package mate.academy.booklibrary.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.booklibrary.dto.BookDto;
import mate.academy.booklibrary.dto.CreateBookRequestDto;
import mate.academy.booklibrary.dto.UpdateBookRequestDto;
import mate.academy.booklibrary.exception.EntityNotFoundException;
import mate.academy.booklibrary.mapper.BookMapper;
import mate.academy.booklibrary.model.Book;
import mate.academy.booklibrary.repository.BookRepository;
import mate.academy.booklibrary.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public Page<BookDto> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book not found with id: " + id
                ));

        return bookMapper.toDto(book);
    }

    @Override
    public BookDto createBook(CreateBookRequestDto bookDto) {
        Book book = bookMapper.toModel(bookDto);

        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book not found with id: " + id
                ));

        bookRepository.delete(book);
    }

    @Override
    public BookDto updateBook(Long id, UpdateBookRequestDto requestDto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book not found with id: " + id
                ));

        bookMapper.updateBook(requestDto, book);

        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDto(updatedBook);
    }
}
