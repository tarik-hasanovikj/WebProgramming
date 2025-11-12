package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public Author(String name, String surname, String country, String biography) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.biography = biography;
    }
}
