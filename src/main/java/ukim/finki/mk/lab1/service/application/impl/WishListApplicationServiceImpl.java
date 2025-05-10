package ukim.finki.mk.lab1.service.application.impl;

import org.springframework.stereotype.Service;
import ukim.finki.mk.lab1.dto.UpdateBookDto;
import ukim.finki.mk.lab1.dto.WishListDto;
import ukim.finki.mk.lab1.model.domain.Book;
import ukim.finki.mk.lab1.model.domain.WishList;
import ukim.finki.mk.lab1.repository.BookRepository;
import ukim.finki.mk.lab1.repository.WishListRepository;
import ukim.finki.mk.lab1.service.application.WishListApplicationService;
import ukim.finki.mk.lab1.service.domain.BookService;
import ukim.finki.mk.lab1.service.domain.WishListService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class WishListApplicationServiceImpl implements WishListApplicationService {
    private final WishListService wishListService;
    private final BookService bookService;

    public WishListApplicationServiceImpl(WishListService wishListService, BookService bookService) {
        this.wishListService = wishListService;
        this.bookService = bookService;
    }


    @Override
    public WishListDto addBookToWishlist(String userId, Long bookId) {
        WishList wishList = wishListService.addBookToWishlist(userId, bookId);

        return WishListDto.from(wishList);
    }

    @Override
    public List<UpdateBookDto> rentAllFromWishlist(String userId) {
        WishList wishList = wishListService.findByUserName(userId);
        List<UpdateBookDto> rentedBooks = new ArrayList<>();
        for (Book book : wishList.getBooks()) {
            if (book.getAvailableCopies()>0){
                bookService.markRented(book.getId());
                rentedBooks.add(UpdateBookDto.fromBook(book));
            }else {
                throw new RuntimeException( "Not enough available copies for book " + book.getName());
            }
        }

        return rentedBooks;
    }

    @Override
    public WishListDto findByUserName(String username) {
        WishList wishList = wishListService.findByUserName(username);

        return WishListDto.from(wishList);
    }

    @Override
    public WishListDto removeFromWishlist(String userId, Long bookId) {
        Book book = bookService.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        WishList wishList = wishListService.removeFromWishlist(userId, bookId);

        return WishListDto.from(wishList);
    }


}
