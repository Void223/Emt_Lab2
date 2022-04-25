package mk.ukim.finki.emtlab.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Author {

    public Author(){
    }

    public Author(String name, String surname, Country country){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @ManyToOne
    private Country country;
}
