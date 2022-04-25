package mk.ukim.finki.emtlab.web;

import mk.ukim.finki.emtlab.Model.Book;
import mk.ukim.finki.emtlab.Model.BookCategory;
import mk.ukim.finki.emtlab.Model.dto.BookDto;
import mk.ukim.finki.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emtlab.repository.BookRepositroy;
import mk.ukim.finki.emtlab.repository.CountryRepositroy;
import mk.ukim.finki.emtlab.service.BookService;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/book")
public class BookController {

    private final BookRepositroy bookRepositroy;
    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final CountryRepositroy countryRepositroy;

    @Autowired
    public BookController(BookRepositroy bookRepositroy, BookService bookService, AuthorRepository authorRepository, CountryRepositroy countryRepositroy){
        this.bookService = bookService;
        this.bookRepositroy = bookRepositroy;
        this.authorRepository = authorRepository;
        this.countryRepositroy = countryRepositroy;
    }

    @GetMapping
    public List<Book> findAll(){
        return this.bookService.listAll();
    }

    @GetMapping("/categories")
    public List<BookCategory> categories(){
        return Arrays.asList(BookCategory.values());
    }

    @GetMapping("/rent/{id}")
    public int rent(@PathVariable Long id){
        this.bookService.markAsTaken(id);
        return this.bookService.findById(id).getAvailableCopeis();
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.delete(id);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Book addBook(@RequestBody BookDto bookDto){
        return this.bookService.create(bookDto.getName(), BookCategory.valueOf(bookDto.getCategory().toString()), bookDto.getAuthor(), bookDto.getAvailableCopeis());
    }

    @PostMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody BookDto bookDto){
        return this.bookService.update(id, bookDto.getName(), BookCategory.valueOf(bookDto.getCategory().toString()), bookDto.getAuthor(), bookDto.getAvailableCopeis());
    }
}
