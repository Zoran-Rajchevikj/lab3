package ukim.finki.mk.lab1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukim.finki.mk.lab1.model.domain.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

    WishList findByUser_Username(String username); // Prebaruvanje na wishlist-ovite na korisnik po korisnicko ime


}

