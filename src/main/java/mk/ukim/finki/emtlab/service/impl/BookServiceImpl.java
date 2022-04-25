package mk.ukim.finki.emtlab.service.impl;

import mk.ukim.finki.emtlab.Model.Author;
import mk.ukim.finki.emtlab.Model.Book;
import mk.ukim.finki.emtlab.Model.BookCategory;
import mk.ukim.finki.emtlab.Model.Exceptions.InvalidAuthorIdException;
import mk.ukim.finki.emtlab.Model.Exceptions.InvalidBookIdException;
import mk.ukim.finki.emtlab.Model.Exceptions.NoMoreCopiesException;
import mk.ukim.finki.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emtlab.repository.BookRepositroy;
import mk.ukim.finki.emtlab.repository.CountryRepositroy;
import mk.ukim.finki.emtlab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepositroy bookRepositroy;
    private final AuthorRepository authorRepository;
    private final CountryRepositroy countryRepositroy;

    public BookServiceImpl(BookRepositroy bookRepositroy, AuthorRepository authorRepository, CountryRepositroy countryRepositroy){
        this.bookRepositroy = bookRepositroy;
        this.authorRepository = authorRepository;
        this.countryRepositroy = countryRepositroy;
    }

    @Override
    public List<Book> listAll() {
        return bookRepositroy.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepositroy.findById(id).orElseThrow(InvalidBookIdException::new);
    }

    @Override
    public Book create(String name, BookCategory category, Long authorId, int availableCopeis) {
        Author author = authorRepository.findById(authorId).orElseThrow(InvalidAuthorIdException::new);
        Book book = new Book(name, category,author, availableCopeis);
        bookRepositroy.save(book);
        return book;
    }

    @Override
    public Book update(Long id, String name, BookCategory category, Long authorId, int availableCopeis) {
        Book book = bookRepositroy.findById(id).orElseThrow(InvalidBookIdException::new);
        Author author = authorRepository.findById(authorId).orElseThrow(InvalidAuthorIdException::new);
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopeis(availableCopeis);

        bookRepositroy.save(book);
        return book;
    }

    @Override
    public Book delete(Long id) {
        Book book = bookRepositroy.findById(id).orElseThrow(InvalidBookIdException::new);
        bookRepositroy.delete(book);
        return book;
    }

    @Override
    public Book markAsTaken(Long id) {
        Book book = bookRepositroy.findById(id).orElseThrow(InvalidBookIdException::new);
        if(book.getAvailableCopeis() == 0){
            throw new NoMoreCopiesException();
        }
        else {
            book.setAvailableCopeis(book.getAvailableCopeis() - 1);
        }
        bookRepositroy.save(book);
        return book;
    }
}
