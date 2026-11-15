package mate.academy.booklibrary.dto;

import java.math.BigDecimal;
import lombok.Data;
//For clients

@Data
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private String description;
    private String coverImage;
}
