package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Book> books;
    public static List<BookReservation> reservations;

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        reservations = new ArrayList<>();

        books.add(new Book("To Kill a Mockingbird", "Classic", 4.8));
        books.add(new Book("1984", "Dystopian", 4.7));
        books.add(new Book("The Great Gatsby", "Classic", 4.4));
        books.add(new Book("Harry Potter and the Sorcerer's Stone", "Fantasy", 4.9));
        books.add(new Book("The Lord of the Rings", "Fantasy", 4.9));
        books.add(new Book("Pride and Prejudice", "Romance", 4.6));
        books.add(new Book("The Catcher in the Rye", "Classic", 4.0));
        books.add(new Book("The Hobbit", "Fantasy", 4.8));
        books.add(new Book("The Da Vinci Code", "Thriller", 4.3));
        books.add(new Book("The Alchemist", "Philosophical Fiction", 4.5));
    }
}
