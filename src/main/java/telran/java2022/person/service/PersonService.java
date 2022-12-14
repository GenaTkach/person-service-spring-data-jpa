package telran.java2022.person.service;

import telran.java2022.person.dto.AddressDto;
import telran.java2022.person.dto.CityPopulationDto;
import telran.java2022.person.dto.PersonDto;

public interface PersonService {
    Boolean addPerson(PersonDto personDto);

    PersonDto findPersonById(Integer id);

    PersonDto removePerson(Integer id);

    PersonDto updatePersonName(Integer id, String name);

    // Поменял в аргументах со String addressDto -> AddressDto addressDto;
    PersonDto updatePersonAddress(Integer id, AddressDto addressDto);

    Iterable<PersonDto> findPersonsByCity(String city);

    Iterable<PersonDto> findPersonsByName(String name);

    Iterable<PersonDto> findPersonsBetweenAges(Integer minAge, Integer maxAge);

    Iterable<CityPopulationDto> getCitiesPopulation();
    
    Iterable<PersonDto> findEmployeesBySalary(int min, int max);
    
    Iterable<PersonDto> getChildren();
}
