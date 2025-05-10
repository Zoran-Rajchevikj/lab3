package ukim.finki.mk.lab1.service.domain.impl;

import org.springframework.stereotype.Service;
import ukim.finki.mk.lab1.model.domain.Author;
import ukim.finki.mk.lab1.model.domain.Country;
import ukim.finki.mk.lab1.repository.AuthorRepository;
import ukim.finki.mk.lab1.repository.CountryRepository;
import ukim.finki.mk.lab1.service.domain.AuthorService;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;


    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;

    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(Author author) {
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
        return authorRepository.findById(id).map(existingAuthor -> {
            if(author.getName() != null ){
                existingAuthor.setName(author.getName());
            }
            if(author.getSurname() != null ){
                existingAuthor.setSurname(author.getSurname());
            }
            if(author.getCountry() != null ){
                existingAuthor.setCountry(author.getCountry());
            }
            return authorRepository.save(existingAuthor);
        });
    }



    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
