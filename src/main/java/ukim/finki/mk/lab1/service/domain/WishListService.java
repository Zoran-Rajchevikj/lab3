package ukim.finki.mk.lab1.service.domain;

import ukim.finki.mk.lab1.model.domain.Book;
import ukim.finki.mk.lab1.model.domain.WishList;

import java.util.List;
import java.util.Optional;

public interface WishListService {
    WishList addBookToWishlist(String username, Long bookId);

    List<Book> rentAllFromWishlist(String username);

    WishList findByUserName(String username);

    WishList removeFromWishlist(String userId, Long bookId);



}
