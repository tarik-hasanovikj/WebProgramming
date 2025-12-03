package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.enums.Gender;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(Long id);

    Author save(String name, String surname, String country, String biography, Gender gender);

    Author edit(Long id, String name, String surname, String country, String biography, Gender gender);

    void delete(Long id);

}
