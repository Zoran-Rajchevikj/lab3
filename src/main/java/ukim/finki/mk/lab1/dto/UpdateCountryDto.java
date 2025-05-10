package ukim.finki.mk.lab1.dto;

import ukim.finki.mk.lab1.model.domain.Country;

public record UpdateCountryDto(Long id, String name, String continent) {
    public static  UpdateCountryDto fromCountry(Country country) {
        return new UpdateCountryDto(country.getId(),country.getName(),country.getContinent());
    }
}
