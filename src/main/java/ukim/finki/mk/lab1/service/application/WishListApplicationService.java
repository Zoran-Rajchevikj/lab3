package ukim.finki.mk.lab1.service.application;

import ukim.finki.mk.lab1.dto.UpdateBookDto;
import ukim.finki.mk.lab1.dto.WishListDto;
import ukim.finki.mk.lab1.model.domain.Book;
import ukim.finki.mk.lab1.model.domain.WishList;

import java.util.List;
import java.util.Optional;

public interface WishListApplicationService {
    WishListDto addBookToWishlist(String userId, Long bookId);

    List<UpdateBookDto> rentAllFromWishlist(String userId);

    WishListDto findByUserName(String username);

    WishListDto removeFromWishlist(String userId, Long bookId);


}
