package ukim.finki.mk.lab1.service.application.impl;

import org.springframework.stereotype.Service;
import ukim.finki.mk.lab1.dto.CreateCountryDto;
import ukim.finki.mk.lab1.dto.UpdateCountryDto;
import ukim.finki.mk.lab1.model.views.AuthorsPerCountryView;
import ukim.finki.mk.lab1.repository.AuthorsPerCountryViewRepository;
import ukim.finki.mk.lab1.service.application.CountryApplicationService;
import ukim.finki.mk.lab1.service.domain.CountryService;

import java.util.List;
import java.util.Optional;
@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;

    public CountryApplicationServiceImpl(CountryService countryService, AuthorsPerCountryViewRepository authorsPerCountryViewRepository) {
        this.countryService = countryService;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
    }

    @Override
    public List<UpdateCountryDto> findAll() {
        return countryService.findAll().stream().map(UpdateCountryDto::fromCountry).toList();
    }

    @Override
    public Optional<UpdateCountryDto> findById(Long id) {
        return countryService.findById(id).map(UpdateCountryDto::fromCountry);
    }

    @Override
    public Optional<UpdateCountryDto> save(CreateCountryDto countryDto) {

        return countryService.save(countryDto.toCountry()).map(UpdateCountryDto::fromCountry);
    }

    @Override
    public Optional<UpdateCountryDto> update(Long id, CreateCountryDto countryDto) {
        return countryService.update(id,countryDto.toCountry()).map(UpdateCountryDto::fromCountry);
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }

    @Override
    public List<AuthorsPerCountryView> findAllAuthorsPerCountry() {
        return authorsPerCountryViewRepository.findAll();
    }

    @Override
    public AuthorsPerCountryView findAuthorsPerCountryById(Long id) {
        return authorsPerCountryViewRepository.findById(id).orElseThrow(() -> new RuntimeException("Author per country not found"));
    }

    @Override
    public void refreshMaterializedView() {
        authorsPerCountryViewRepository.refreshMaterializedView();
    }
}
