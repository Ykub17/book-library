package mate.academy.booklibrary;

import java.math.BigDecimal;
import mate.academy.booklibrary.model.Book;
import mate.academy.booklibrary.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookLibraryApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(BookService bookService) {
        return args -> {
            Book book = new Book();
            book.setTitle("Spring Boot in Action");
            book.setDescription("Comprehensive guide to Spring Boot");
            book.setAuthor("Craig Walls");
            book.setIsbn("978-1617292545");
            book.setPrice(BigDecimal.valueOf(45.99));
            book.setCoverImage("spring-boot-action.jpg");

            bookService.save(book);

            System.out.println("All books in DB");
            bookService.findAll().forEach(System.out::println);
        };
    }
}
