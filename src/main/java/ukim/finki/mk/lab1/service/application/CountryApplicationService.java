package ukim.finki.mk.lab1.service.application;

import org.hibernate.sql.Update;
import ukim.finki.mk.lab1.dto.CreateCountryDto;
import ukim.finki.mk.lab1.dto.UpdateCountryDto;
import ukim.finki.mk.lab1.model.domain.Country;
import ukim.finki.mk.lab1.model.views.AuthorsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<UpdateCountryDto> findAll();
    Optional<UpdateCountryDto> findById(Long id);
    Optional <UpdateCountryDto> save(CreateCountryDto countryDto);
    Optional <UpdateCountryDto> update(Long id, CreateCountryDto countryDto);
    void deleteById(Long id);

    List<AuthorsPerCountryView> findAllAuthorsPerCountry();
    AuthorsPerCountryView findAuthorsPerCountryById(Long id);
    void refreshMaterializedView();

}
