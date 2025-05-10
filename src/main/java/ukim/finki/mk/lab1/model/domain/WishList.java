package ukim.finki.mk.lab1.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
@Table(name = "wishlists")
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")  // This defines the join column between User and WishList
//    @ToString.Exclude
@OneToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id")

    private User user;// Povrzanost so korisnikot

//    private String name; // Ime na wish listata
    @ManyToMany
    private List<Book> books; // Листа на книги во wish listата


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public List<Book> getBooks() {
        return  books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    // Konstruktor
    public WishList() {
        this.books = new ArrayList<>();
    }

    public WishList(User user) {
        this.user = user;
//        this.name = name;
        this.books = new ArrayList<>();
    }
}
