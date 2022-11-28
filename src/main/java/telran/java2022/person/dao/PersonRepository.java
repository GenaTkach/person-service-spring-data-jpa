package telran.java2022.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import telran.java2022.person.dto.CityPopulationDto;
import telran.java2022.person.model.Child;
import telran.java2022.person.model.Employee;
import telran.java2022.person.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query("SELECT p FROM Person p WHERE p.address.city=:city")
    Stream<Person> findAllByAddressCity(@Param("city") String city);

    @Query("SELECT p FROM Person p WHERE p.name=?1")
    Stream<Person> findPersonsByName(String name);

    Stream<Person> findByBirthDateBetween(LocalDate minAge, LocalDate maxAge);

    @Query("SELECT new telran.java2022.person.dto.CityPopulationDto(p.address.city, count(p)) FROM Person p GROUP BY p.address.city ORDER BY count(p) DESC")
    List<CityPopulationDto> getCitiesPopulation();
    
    Stream<Employee> findEmployeesBySalaryBetween(int min, int max);
    
    @Query("SELECT child FROM Child child")
    Stream<Child> findAllChildren();
    
}
