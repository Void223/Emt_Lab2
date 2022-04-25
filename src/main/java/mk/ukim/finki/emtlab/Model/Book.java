package mk.ukim.finki.emtlab.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {

    public Book(){}

    public Book(String name, BookCategory category, Author author, int availableCopeis){
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopeis = availableCopeis;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private BookCategory category;

    @ManyToOne
    private Author author;

    private int availableCopeis;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getAvailableCopeis() {
        return availableCopeis;
    }

    public void setAvailableCopeis(int availableCopeis) {
        this.availableCopeis = availableCopeis;
    }
}
