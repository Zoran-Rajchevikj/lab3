package ukim.finki.mk.lab1.service.domain.impl;

import org.springframework.stereotype.Service;
import ukim.finki.mk.lab1.model.domain.Country;
import ukim.finki.mk.lab1.repository.CountryRepository;
import ukim.finki.mk.lab1.service.domain.CountryService;

import java.util.List;
import java.util.Optional;
@Service
public class CountryServiceImpl implements CountryService {
    private  final CountryRepository countryRepository;

    public CountryServiceImpl( CountryRepository countryRepository) {

        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryRepository.findById(id).map(existing -> {
            if(country.getName() != null){
                existing.setName(country.getName());
            }if(country.getContinent() != null){
                existing.setContinent(country.getContinent());
            }
            return countryRepository.save(existing);
        });
    }

//    @Override
//    public Country save(String name, String continent) {
//
//        return countryRepository.save(new Country(name,continent));
//    }
//
//    @Override
//    public Country update(Long id, String name, String continent) {
//        Country country = countryRepository.findById(id).orElse(null);
//        if (country == null) {
//            return null;
//        }
//        country.setName(name);
//        country.setContinent(continent);
//        return countryRepository.save(country);
//    }

    @Override
    public void deleteById(Long id) {

        countryRepository.deleteById(id);
    }
}
