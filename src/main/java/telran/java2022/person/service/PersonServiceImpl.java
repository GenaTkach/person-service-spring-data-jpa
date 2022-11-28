package telran.java2022.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java2022.person.dao.PersonRepository;
import telran.java2022.person.dto.AddressDto;
import telran.java2022.person.dto.ChildDto;
import telran.java2022.person.dto.CityPopulationDto;
import telran.java2022.person.dto.EmployeeDto;
import telran.java2022.person.dto.PersonDto;
import telran.java2022.person.dto.PersonNotFoundException;
import telran.java2022.person.model.Address;
import telran.java2022.person.model.Child;
import telran.java2022.person.model.Employee;
import telran.java2022.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {
    final PersonRepository repository;
    final ModelMapper mapper;

    @Override
    @Transactional
    public Boolean addPerson(PersonDto personDto) {
	// Проверка если уже есть такой пользователь по id
	if (repository.existsById(personDto.getId())) {
	    return false;
	}
	repository.save(mapper.map(personDto, getModelClass(personDto)));
	return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
	Person person = repository.findById(id)
		.orElseThrow(PersonNotFoundException::new);
	return mapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public PersonDto removePerson(Integer id) {
	Person person = repository.findById(id)
		.orElseThrow(PersonNotFoundException::new);
	repository.delete(person);
	return mapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public PersonDto updatePersonName(Integer id, String name) {
	Person person = repository.findById(id)
		.orElseThrow(PersonNotFoundException::new);
	person.setName(name);
	return mapper.map(person, PersonDto.class);
    }

    @Override
    @Transactional
    public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
	Person person = repository.findById(id)
		.orElseThrow(PersonNotFoundException::new);
	Address address = mapper.map(addressDto, Address.class);
	person.setAddress(address);
	return mapper.map(person, PersonDto.class);
    }

    @Override
    // Транзакция может проходить несколькими пользователями, потому что READ ONLY.
    @Transactional(readOnly = true)
    public Iterable<PersonDto> findPersonsByCity(String city) {
	return repository.findAllByAddressCity(city)
		.map(p -> mapper.map(p, PersonDto.class))
		.collect(Collectors.toList());
    }

    @Override
    // Транзакция может проходить несколькими пользователями, потому что READ ONLY.
    @Transactional(readOnly = true)
    public Iterable<PersonDto> findPersonsByName(String name) {
	return repository.findPersonsByName(name)
		.map(p -> mapper.map(p, PersonDto.class))
		.collect(Collectors.toList());
    }

    @Override
    // Транзакция может проходить несколькими пользователями, потому что READ ONLY.
    @Transactional(readOnly = true)
    public Iterable<PersonDto> findPersonsBetweenAges(Integer minAge, Integer maxAge) {
	LocalDate from = LocalDate.now()
		.minusYears(maxAge);
	LocalDate to = LocalDate.now()
		.minusYears(minAge);

	return repository.findByBirthDateBetween(from, to)
		.map(p -> mapper.map(p, PersonDto.class))
		.collect(Collectors.toList());
    }

    @Override
    public Iterable<CityPopulationDto> getCitiesPopulation() {
	return repository.getCitiesPopulation();
    }

    // Дефолтное добавление при старте апликации
    @Override
    public void run(String... args) throws Exception {
	Person person = new Person(1, "John", LocalDate.of(1985, 04, 11), new Address("Tel Aviv", "Shenkin", 100));
	Child child = new Child(2, "Moshe", LocalDate.of(2018, 07, 11), new Address("Ashkelon", "Bar Kohva", 82),
		"Shalom");
	Employee employee = new Employee(3, "Sarah", LocalDate.of(1995, 10, 23), new Address("Rehovot", "Herzl", 7),
		"Motorola", 20000);
	repository.save(person);
	repository.save(child);
	repository.save(employee);
    }

    @Override
    public Iterable<PersonDto> findEmployeesBySalary(int min, int max) {
	return null;
    }

    @Override
    public Iterable<PersonDto> getChildren() {
	return null;
    }

    // Метод для определения DTO
    private Class<? extends Person> getModelClass(PersonDto personDto) {
	if (personDto instanceof EmployeeDto) {
	    return Employee.class;
	}
	if (personDto instanceof ChildDto) {
	    return Child.class;
	}
	return Person.class;
    }
}
