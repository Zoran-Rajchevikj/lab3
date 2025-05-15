package ukim.finki.mk.lab1.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukim.finki.mk.lab1.dto.CreateCountryDto;
import ukim.finki.mk.lab1.dto.UpdateCountryDto;
import ukim.finki.mk.lab1.model.domain.Country;
import ukim.finki.mk.lab1.service.application.CountryApplicationService;
import ukim.finki.mk.lab1.service.domain.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryApplicationService countryApplicationService;

    public CountryController( CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;

    }

    @Operation(summary = "Get all countries", description = "Returns a list of all countries.")
    @GetMapping
    public List<UpdateCountryDto> listAll() {
        return countryApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdateCountryDto> findById(@PathVariable Long id) {
        return countryApplicationService.findById(id).map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Operation(summary = "Add a new country", description = "Creates a new country in the system using the provided data.")
    @PostMapping("/add")
    public ResponseEntity<UpdateCountryDto> addCountry(@RequestBody CreateCountryDto createCountryDto) {
        return countryApplicationService.save(createCountryDto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Edit an existing country", description = "Updates the details of an existing country by its ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<UpdateCountryDto> editCountry(
            @PathVariable Long id,
            @RequestBody CreateCountryDto countryDto) {
        return countryApplicationService.update(id,countryDto).map(country -> ResponseEntity.ok().body(country) ).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a country", description = "Deletes a country by its ID from the system.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
