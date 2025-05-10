package ukim.finki.mk.lab1.web;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukim.finki.mk.lab1.dto.UpdateBookDto;
import ukim.finki.mk.lab1.dto.WishListDto;
import ukim.finki.mk.lab1.model.domain.Book;
import ukim.finki.mk.lab1.service.application.WishListApplicationService;
import ukim.finki.mk.lab1.service.domain.WishListService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishListApplicationService wishListApplicationService;

    public WishlistController( WishListApplicationService wishListApplicationService) {
        this.wishListApplicationService = wishListApplicationService;

    }

    @GetMapping("/{userId}")
    public ResponseEntity<WishListDto> getWishlist(@PathVariable String userId) {
        WishListDto wishListDto = wishListApplicationService.findByUserName(userId);
        if (wishListDto != null ) {
            return ResponseEntity.ok(wishListDto);  // Return wishlist as response with status 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)  // Return 404 if wishlist is not found
                    .body(null);
        }
    }

    @Operation(summary = "Add a book to the wishlist", description = "Adds a book to the wishlist if there are available copies.")
    @PostMapping("/add/{userId}/{bookId}")
    public ResponseEntity<?> addToWishlist(@PathVariable String userId, @PathVariable Long bookId) {
        try {
            wishListApplicationService.addBookToWishlist(userId, bookId);
            return ResponseEntity.status(HttpStatus.CREATED)  // Return 201 Created after successfully adding the book
                    .body("Book added to wishlist successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)  // Handle cases like book already in wishlist or unavailable
                    .body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping("/{userId}/remove/{bookId}")
    public ResponseEntity<WishListDto> removeFromWishlist(@PathVariable String userId, @PathVariable Long bookId) {
        try {
            // Call the service method to remove the book from the wishlist
            WishListDto wishlistDto = wishListApplicationService.removeFromWishlist(userId, bookId);
            return ResponseEntity.ok(wishlistDto);  // Return the updated wishlist as the response
        } catch (RuntimeException e) {
            // Handle errors such as book not found or other exceptions
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);  // Return an empty body or an error message as needed
        }
    }

    @Operation(summary = "Rent all books from wishlist", description = "Rents all books from the wishlist by reducing available copies.")
    @PostMapping("/rent/{userId}")
    public ResponseEntity<List<UpdateBookDto>> rentAllFromWishlist(@PathVariable String userId) {
        try {
            // Call the service method to rent all books from the wishlist
            List<UpdateBookDto> rentedBooks = wishListApplicationService.rentAllFromWishlist(userId);
            return ResponseEntity.ok(rentedBooks);  // Return the rented books with a 200 OK status
        } catch (RuntimeException e) {
            // Handle errors such as unavailable books or other issues
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);  // You can return a specific error message or null as needed
        }
    }
}
