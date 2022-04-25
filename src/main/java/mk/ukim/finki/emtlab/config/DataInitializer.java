package mk.ukim.finki.emtlab.config;

import mk.ukim.finki.emtlab.Model.Author;
import mk.ukim.finki.emtlab.Model.Book;
import mk.ukim.finki.emtlab.Model.BookCategory;
import mk.ukim.finki.emtlab.Model.Country;
import mk.ukim.finki.emtlab.repository.AuthorRepository;
import mk.ukim.finki.emtlab.repository.BookRepositroy;
import mk.ukim.finki.emtlab.repository.CountryRepositroy;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final CountryRepositroy countryRepositroy;
    private final AuthorRepository authorRepository;
    private final BookRepositroy bookRepositroy;

    public DataInitializer( CountryRepositroy countryRepositroy, AuthorRepository authorRepository, BookRepositroy bookRepositroy){
        this.countryRepositroy = countryRepositroy;
        this.authorRepository = authorRepository;
        this. bookRepositroy = bookRepositroy;
        this.fillData();
    }

    private void fillData() {
        Country germany = new Country("Germany", "Europe");
        Country macedonia = new Country("Macedonia", "Europe");
        Country us = new Country("USA", "North America");

        countryRepositroy.save(germany);
        countryRepositroy.save(us);
        countryRepositroy.save(macedonia);

        Author german = new Author("Johann", "Wolfgang von Goethe", germany);
        Author macedonian = new Author("Kole", "Nedelkovski", macedonia);
        Author Us = new Author("Mark", "Twain", us);

        authorRepository.save(german);
        authorRepository.save(macedonian);
        authorRepository.save(Us);

        Book TSW = new Book("The Sorrow of young Werther", BookCategory.NOVEL, german, 50);
        Book FAW = new Book("On Foot Around the World", BookCategory.HISTORY, macedonian, 100);
        Book AHF = new Book("Adventures of Huckelberry Finn", BookCategory.NOVEL, Us, 200);

        bookRepositroy.save(TSW);
        bookRepositroy.save(FAW);
        bookRepositroy.save(AHF);
    }
}
