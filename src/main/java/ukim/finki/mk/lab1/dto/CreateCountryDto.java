package ukim.finki.mk.lab1.dto;

import lombok.Data;
import ukim.finki.mk.lab1.model.domain.Country;


public record CreateCountryDto(String name,String continent) {
    public Country toCountry() {
        return  new Country(name,continent);
    }
}
