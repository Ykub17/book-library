package mate.academy.booklibrary.mapper;

import mate.academy.booklibrary.config.MapStructConfig;
import mate.academy.booklibrary.dto.BookDto;
import mate.academy.booklibrary.dto.CreateBookRequestDto;
import mate.academy.booklibrary.dto.UpdateBookRequestDto;
import mate.academy.booklibrary.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    void updateBook(UpdateBookRequestDto requestDto, @MappingTarget Book book);
}
