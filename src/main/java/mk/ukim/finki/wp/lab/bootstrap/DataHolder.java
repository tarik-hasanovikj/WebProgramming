package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Book> books;
    public static List<BookReservation> reservations;
    public static List<Author> authors;

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        reservations = new ArrayList<>();
        authors = new ArrayList<>();

        Author JaneAusten = new Author("Jane", "Austen", "UK",
                "Jane Austen was an English novelist.");
        Author JohnTolkien = new Author("John", "Tolkien", "UK",
                "John Ronald Reuel Tolkien was an English writer and philologist.");
        Author DanBrown = new Author("Dan", "Brown", "USA",
                "Daniel Gerhard Brown (born June 22, 1964) is an American writer.");

        authors.add(JaneAusten);
        authors.add(JohnTolkien);
        authors.add(DanBrown);

        books.add(new Book("To Kill a Mockingbird", "Classic", 4.8, DanBrown));
        books.add(new Book("1984", "Dystopian", 4.7, DanBrown));
        books.add(new Book("The Great Gatsby", "Classic", 4.4, JohnTolkien));
        books.add(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 4.9, JohnTolkien));
        books.add(new Book("The Lord of the Rings", "Fantasy", 4.9, JaneAusten));
        books.add(new Book("Pride and Prejudice", "Romance", 4.6, JaneAusten));
        books.add(new Book("The Catcher in the Rye", "Classic", 4.0, JaneAusten));
        books.add(new Book("The Hobbit", "Fantasy", 4.8, JohnTolkien));
        books.add(new Book("The Da Vinci Code", "Thriller", 4.3, DanBrown));
        books.add(new Book("The Alchemist", "Philosophical Fiction", 4.5, DanBrown));
    }
}
