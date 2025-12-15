package org.thomcgn.repositories;

import org.thomcgn.data.Person;
import org.thomcgn.utils.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonRepository {
    List<Person> persons = new ArrayList<>();

    public void addPerson(Person person) {
        persons.add(person);
    }

    public long countMales() {
        return persons.stream()
                .filter(person -> person.gender().equals(Gender.MALE))
                .count();

    }

    public Optional<Person> getById(int id) {
        return persons.stream()
                .filter(person -> person.id() == id).findFirst();
    }

    public Optional<Person> getByName(String name) {
        return persons.stream()
                .filter(person -> person.name().equals(name))
                .findFirst();
    }

    public Map<Gender, Long> countGenders() {
        return persons.stream()
                .collect(Collectors.groupingBy(Person::gender, Collectors.counting()));
    }

    public Map<Gender, Long> countWeekendFavoritesByGender() {
        return persons.stream()
                .filter(person -> person.favoriteDay().getValue().equals("Wochenende"))
                .collect(Collectors.groupingBy(Person::gender, Collectors.counting()));
    }

    public Map<Gender, Map<String, Long>> fullGenderFavoriteDayReport() {
        return persons.stream()
                .collect(Collectors.groupingBy(
                        Person::gender,
                        Collectors.groupingBy(
                                person -> person.favoriteDay().getValue().equals("Wochenende") ? "Weekend" : "Weekday",
                                Collectors.counting()
                        )
                ));
    }
}