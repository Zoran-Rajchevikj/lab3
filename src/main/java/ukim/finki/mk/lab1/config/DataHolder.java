package ukim.finki.mk.lab1.config;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ukim.finki.mk.lab1.model.domain.*;
import ukim.finki.mk.lab1.model.enums.Category;
import ukim.finki.mk.lab1.model.enums.Role;
import ukim.finki.mk.lab1.repository.*;

import java.util.List;

@Component
public class DataHolder {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WishListRepository wishListRepository;

    public DataHolder(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, WishListRepository wishListRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.wishListRepository = wishListRepository;
    }


    @PostConstruct
    public void initData() {

//
//        if (countryRepository.count() == 0) {
//
//            Country germany = new Country("Germany", "Europe");
//            Country macedonia = new Country("Macedonia", "Europe");
//            Country brazil = new Country("Brazil", "South America");
//            countryRepository.saveAll(List.of( germany, macedonia, brazil));
//
//        }
//
//        if (authorRepository.count() == 0) {
//            Country germany = countryRepository.findByName("Germany").orElseThrow();
//            Country macedonia = countryRepository.findByName("Macedonia").orElseThrow();
//            Country brazil = countryRepository.findByName("Brazil").orElseThrow();
//
//            Author a1 = new Author("Friedrich", "Nietzsche", germany);
//            Author a2 = new Author("Blaze", "Koneski", macedonia);
//            Author a3 = new Author("Paulo", "Coelho", brazil);
//
//            authorRepository.saveAll(List.of(a1,a2,a3));
//        }
//
//        if (bookRepository.count() == 0) {
//            Author a1 = authorRepository.findByNameAndSurname("Friedrich", "Nietzsche").orElseThrow();
//            Author a2 = authorRepository.findByNameAndSurname("Blaze", "Koneski").orElseThrow();
//            Author a3 = authorRepository.findByNameAndSurname("Paulo", "Coelho").orElseThrow();
//
//            Book b1 = new Book("Thus Spoke Zarathustra", Category.CLASSICS, a1, 10);
//            Book b2 = new Book("Collected Poems", Category.DRAMA, a2, 5);
//            Book b3 = new Book("The Alchemist", Category.NOVEL, a3, 8);
//
//            bookRepository.saveAll(List.of(b1, b2, b3));
//        }

        if (userRepository.count() == 0) {
            // Creating users and wishlists
            User admin = new User("lb", passwordEncoder.encode("lb"), "Lib", "Lib", Role.ROLE_LIBRARIAN);
            User user = new User("user", passwordEncoder.encode("user"), "User", "Userovski", Role.ROLE_USER);


            // Automatically creates the wishlists associated with users
            userRepository.saveAll(List.of(admin, user));
            WishList wishList = new WishList(admin);
            WishList wishList1 = new WishList(user);
            wishListRepository.save(wishList);
            wishListRepository.save(wishList1);
            admin.setWishList(wishList);

        }
    }

}
