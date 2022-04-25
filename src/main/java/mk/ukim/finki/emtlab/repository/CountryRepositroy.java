package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.Model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepositroy extends JpaRepository <Country, Long> {

}
