package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.enums.Gender;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        Optional<Author> book = authorRepository.findById(id);
        return book.orElse(null);
    }

    @Override
    public Author save(String name, String surname, String country, String biography, Gender gender) {
        Author author = new Author(name, surname, country, biography, gender);
        return authorRepository.save(author);
    }

    @Override
    public Author edit(Long id, String name, String surname, String country, String biography, Gender gender) {
        Optional<Author> author = authorRepository.findById(id);

        if (author.isEmpty()) {
            return null;
        }

        author.get().setName(name);
        author.get().setSurname(surname);
        author.get().setCountry(country);
        author.get().setBiography(biography);
        author.get().setGender(gender);

        return authorRepository.save(author.get());
    }

    @Override
    public void delete(Long id) {
        authorRepository.delete(id);
    }

}