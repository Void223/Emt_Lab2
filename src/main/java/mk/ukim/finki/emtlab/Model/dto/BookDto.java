package mk.ukim.finki.emtlab.Model.dto;

import lombok.Data;
import mk.ukim.finki.emtlab.Model.Author;
import mk.ukim.finki.emtlab.Model.Book;
import mk.ukim.finki.emtlab.Model.BookCategory;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
public class BookDto {
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private BookCategory category;

    private Long author;

    private int availableCopeis;

    public BookDto(){}

    public BookDto(String name, BookCategory bookCategory, Long authorId, int availableCopeis){
        this.name = name;
        this.category = bookCategory;
        this.author = authorId;
        this.availableCopeis = availableCopeis;
    }

}
