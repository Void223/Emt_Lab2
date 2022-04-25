package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositroy extends JpaRepository<Book, Long> {
}
