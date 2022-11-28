package telran.java2022.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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

// Стратегия наследования для моделей(entity)
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {
    private static final long serialVersionUID = -6589897511691176184L;
    // Id - устанавливается первичный ключ
    @Id
    Integer id;
    @Setter
    String name;
    LocalDate birthDate;
    @Setter
    Address address;
}
