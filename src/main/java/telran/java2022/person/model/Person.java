package telran.java2022.person.model;

import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
// Entity - создает таблицу для этого объекта
@Entity
// Table - устанавливает название таблицы
@Table(name = "persons")
public class Person {
    // Id - устанавливается первичный ключ
    @Id
    Integer id;
    @Setter
    String name;
    LocalDate birthDate;
    @Setter
    Address address;
}
