package mate.academy.booklibrary.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.booklibrary.dto.BookDto;
import mate.academy.booklibrary.dto.CreateBookRequestDto;
import mate.academy.booklibrary.dto.UpdateBookRequestDto;
import mate.academy.booklibrary.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public Page<BookDto> getAll(Pageable pageable) {
        return bookService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(
            @Valid @RequestBody CreateBookRequestDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBookRequestDto requestDto
    ) {
        return bookService.updateBook(id, requestDto);
    }
}
