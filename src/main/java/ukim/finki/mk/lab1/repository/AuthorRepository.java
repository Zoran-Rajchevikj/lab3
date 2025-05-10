package ukim.finki.mk.lab1.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ukim.finki.mk.lab1.model.domain.Author;
import ukim.finki.mk.lab1.model.domain.Country;
import ukim.finki.mk.lab1.projections.AuthorNamesProjection;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    //
    Optional<Author> findByNameAndSurname(String name,String surname);
    List<AuthorNamesProjection> findAllProjectedBy( Sort sort);
}
