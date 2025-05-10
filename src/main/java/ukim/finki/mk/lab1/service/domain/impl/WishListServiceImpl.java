package ukim.finki.mk.lab1.service.domain.impl;

import org.springframework.stereotype.Service;
import ukim.finki.mk.lab1.model.domain.Book;
import ukim.finki.mk.lab1.model.domain.User;
import ukim.finki.mk.lab1.model.domain.WishList;
import ukim.finki.mk.lab1.repository.UserRepository;
import ukim.finki.mk.lab1.repository.WishListRepository;
import ukim.finki.mk.lab1.service.domain.BookService;
import ukim.finki.mk.lab1.service.domain.WishListService;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {
    private final WishListRepository wishlistRepository;
    private final BookService bookService;
    private final UserRepository userRepository;


    public WishListServiceImpl(WishListRepository wishlistRepository, BookService bookService, UserRepository userRepository) {
        this.wishlistRepository = wishlistRepository;
        this.bookService = bookService;
        this.userRepository = userRepository;
    }



//    @Override
//    public WishList addBookToWishlist(String username, Long bookId) {
//        WishList wishList = wishlistRepository.findByUser_Username(username);
//        if (wishList == null) {
//            throw  new RuntimeException("Wishlist not found");
//        }
//        Book book = bookService.findById(bookId).orElseThrow(() ->new RuntimeException ("Book not found"));
//        if (book.getAvailableCopies()==0){
//            throw new RuntimeException ("Copies not available");
//        }
//        if (wishList.getBooks().contains(book)){
//            throw new RuntimeException ("Book already in wishlist");
//        }
//        wishList.getBooks().add(book);
//        wishlistRepository.save(wishList);
//        return wishList;
//    }
@Override
public WishList addBookToWishlist(String username, Long bookId) {
    WishList wishList = wishlistRepository.findByUser_Username(username);

    if (wishList == null) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        wishList = new WishList(user);
        wishList = wishlistRepository.save(wishList);
        user.setWishList(wishList);
        userRepository.save(user);
    }

    Book book = bookService.findById(bookId)
            .orElseThrow(() -> new RuntimeException("Book not found"));

    if (book.getAvailableCopies() == 0) {
        throw new RuntimeException("Copies not available");
    }

    if (wishList.getBooks().contains(book)) {
        throw new RuntimeException("Book already in wishlist");
    }

    wishList.getBooks().add(book);
    return wishlistRepository.save(wishList);
}


    public List<Book> rentAllFromWishlist(String  userId) {
        WishList wishlist = wishlistRepository.findByUser_Username(userId);
        if (wishlist == null || wishlist.getBooks().isEmpty() ){
            throw new RuntimeException ("Wishlist is empty");
        }
        List<Book> rentedBooks = new ArrayList<>();

        for (Book book : wishlist.getBooks()) {
            if (book.getAvailableCopies()>0){
                bookService.markRented(book.getId());
                rentedBooks.add(book);
            }
            else {
                throw new RuntimeException ("Copies not available");
            }
        }
        return rentedBooks;
    }

    @Override
    public WishList findByUserName(String username) {
        WishList wishlist = wishlistRepository.findByUser_Username(username);
        if (wishlist==null){
            User user = userRepository.findByUsername(username).orElseThrow(() ->new RuntimeException ("User not found"));
            wishlist = new WishList();
            wishlist.setUser(user);
            return wishlistRepository.save(wishlist);
        }

        return wishlist;
    }

    @Override
    public WishList removeFromWishlist(String userId, Long bookId) {
        WishList wishlist = wishlistRepository.findByUser_Username(userId);
        if (wishlist!=null){
            Book book = bookService.findById(bookId).orElseThrow(() ->new RuntimeException ("Book not found"));
            if (wishlist.getBooks().contains(book)){
                wishlist.getBooks().remove(book);
                wishlistRepository.save(wishlist);
            }else {
                throw new RuntimeException ("Book not found in wishlist");
            }

        }else {
            throw new RuntimeException("Wishlist not found for user with username: " + userId);
        }
        return wishlist;
    }


}
