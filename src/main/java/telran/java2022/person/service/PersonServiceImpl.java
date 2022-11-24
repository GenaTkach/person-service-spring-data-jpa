package telran.java2022.person.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.person.dao.PersonRepository;
import telran.java2022.person.dto.AddressDto;
import telran.java2022.person.dto.CityPopulationDto;
import telran.java2022.person.dto.PersonDto;
import telran.java2022.person.dto.PersonNotFoundException;
import telran.java2022.person.model.Address;
import telran.java2022.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    final PersonRepository repository;
    final ModelMapper mapper;

    @Override
    public Boolean addPerson(PersonDto personDto) {
	repository.save(mapper.map(personDto, Person.class));
	return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
	Person person = repository.findById(id)
		.orElseThrow(PersonNotFoundException::new);
	return mapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto removePerson(Integer id) {
	Person person = repository.findById(id)
		.orElseThrow(PersonNotFoundException::new);
	repository.delete(person);
	return mapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto updatePersonName(Integer id, String name) {
	Person person = repository.findById(id)
		.orElseThrow(PersonNotFoundException::new);
	person.setName(name);
	repository.save(person);
	return mapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
	Person person = repository.findById(id)
		.orElseThrow(PersonNotFoundException::new);
	Address address = mapper.map(addressDto, Address.class);
	person.setAddress(address);
	repository.save(person);
	return mapper.map(person, PersonDto.class);
    }

    @Override
    public Iterable<PersonDto> findPersonsByCity(String city) {
	ArrayList<PersonDto> list = repository.findAllByAddressCity(city);
	return list;
    }

    @Override
    public Iterable<PersonDto> findPersonsByName(String name) {
	ArrayList<PersonDto> list = repository.findPersonsByName(name);
	return list;
    }

//    @Override
//    public Iterable<PersonDto> findPersonsBetweenAges(Integer minAge, Integer maxAge) {
//	ArrayList<PersonDto> list = repository.findByBirthDateBetween(LocalDate.now()
//		.minusYears(maxAge),
//		LocalDate.now()
//			.minusYears(minAge));
//	return list;
//    }

//    @Override
//    public Iterable<CityPopulationDto> getCitiesPopulation() {
//	ArrayList<CityPopulationDto> list = repository.findAllCitiesByPopulation();
//	return list;
//    }

}
