package telran.java2022.person.dao;

import java.util.ArrayList;


import org.springframework.data.repository.CrudRepository;

import telran.java2022.person.dto.PersonDto;
import telran.java2022.person.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    ArrayList<PersonDto> findAllByAddressCity(String city);

    ArrayList<PersonDto> findPersonsByName(String name);

//    ArrayList<PersonDto> findByBirthDateBetween(LocalDate minAge, LocalDate maxAge);

//    ArrayList<CityPopulationDto> findAllCitiesByPopulation();
}
