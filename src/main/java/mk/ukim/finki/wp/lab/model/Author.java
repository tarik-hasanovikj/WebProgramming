package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.model.enums.Gender;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String country;

    private String biography;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author(String name, String surname, String country, String biography, Gender gender) {
//        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.biography = biography;
        this.gender = gender;
        this.books = new ArrayList<>();
    }
}
