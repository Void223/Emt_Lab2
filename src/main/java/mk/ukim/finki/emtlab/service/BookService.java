package mk.ukim.finki.emtlab.service;

import mk.ukim.finki.emtlab.Model.Author;
import mk.ukim.finki.emtlab.Model.Book;
import mk.ukim.finki.emtlab.Model.BookCategory;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    Book findById(Long id);
    Book create(String name, BookCategory category, Long authorId, int availableCopeis);
    Book update(Long id, String name, BookCategory category, Long authorId, int availableCopeis);
    Book delete(Long id);
    Book markAsTaken(Long id);

}
