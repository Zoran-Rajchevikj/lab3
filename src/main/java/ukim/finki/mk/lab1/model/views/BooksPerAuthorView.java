//package ukim.finki.mk.lab1.model.views;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import lombok.Data;
//import org.hibernate.annotations.Immutable;
//import org.hibernate.annotations.Subselect;
//
//@Data
//@Entity
//@Subselect("SELECT * FROM books_per_author")
//@Immutable
//public class BooksPerAuthorView {
//    @Id
//    private Long author_id;
//    private Integer numberOfBooks;
//}
package ukim.finki.mk.lab1.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM books_per_author")
@Immutable
public class BooksPerAuthorView {

    @Id
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "num_books")
    private Long numBooks;
}
