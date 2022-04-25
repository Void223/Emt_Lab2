package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.Model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository <Author, Long> {

}
