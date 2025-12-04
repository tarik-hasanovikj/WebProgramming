package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaSpecificationRepository<Book, Long> {

    List<Book> findAllByAuthor_Id(Long authorId);

    @Query("SELECT b FROM Book b " +
            "WHERE (:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:minRating IS NULL OR b.averageRating >= :minRating)")
    List<Book> searchBooks(@Param("title") String title,
                           @Param("minRating") Double minRating);

}
