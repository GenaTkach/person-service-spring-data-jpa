package telran.java2022.person.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.person.dto.AddressDto;
import telran.java2022.person.dto.CityPopulationDto;
import telran.java2022.person.dto.PersonDto;
import telran.java2022.person.service.PersonService;

@RestController
@RequestMapping(path = "/person")
@RequiredArgsConstructor
public class PersonController {
    final PersonService personService;

    @PostMapping
    public Boolean addPerson(@RequestBody PersonDto personDto) {
	return personService.addPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto findPersonById(@PathVariable Integer id) {
	return personService.findPersonById(id);
    }

    @DeleteMapping("/{id}")
    public PersonDto removePerson(@PathVariable Integer id) {
	return personService.removePerson(id);
    }

    @PutMapping("/{id}/name/{name}")
    public PersonDto updatePersonName(@PathVariable Integer id, @PathVariable String name) {
	return personService.updatePersonName(id, name);
    }

    @PutMapping("/{id}/address")
    public PersonDto updatePersonAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
	return personService.updatePersonAddress(id, addressDto);
    }

    @GetMapping("/city/{city}")
    public Iterable<PersonDto> findPersonsByCity(@PathVariable String city) {
	return personService.findPersonsByCity(city);
    }

    @GetMapping("/name/{name}")
    public Iterable<PersonDto> findPersonsByName(@PathVariable String name) {
	return personService.findPersonsByName(name);
    }

    @GetMapping("/ages/{minAge}/{maxAge}")
    public Iterable<PersonDto> findPersonsBetweenAges(@PathVariable Integer minAge, @PathVariable Integer maxAge) {
	return personService.findPersonsBetweenAges(minAge, maxAge);
    }

    @GetMapping("/population/city")
    public Iterable<CityPopulationDto> getCitiesPopulation() {
	return personService.getCitiesPopulation();
    }

    @GetMapping("/salary/{min}/{max}")
    public Iterable<PersonDto> findEmployeesBySalary(@PathVariable int min, @PathVariable int max) {
	return personService.findEmployeesBySalary(min, max);
    }
    
    @GetMapping("/children")
    public Iterable<PersonDto> getAllChildren(){
	return personService.getChildren();
    }

}
