package mate.academy.booklibrary.dto;

import java.math.BigDecimal;
import lombok.Data;
//for our db

@Data
public class CreateBookRequestDto {
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
}
