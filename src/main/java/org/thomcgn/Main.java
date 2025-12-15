package org.thomcgn;

import org.thomcgn.data.Person;
import org.thomcgn.data.Student;
import org.thomcgn.repositories.PersonRepository;
import org.thomcgn.utils.DaysOfWeek;
import org.thomcgn.utils.Gender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    static void main() throws IOException {

        PersonRepository repo = new PersonRepository();

        repo.addPerson(new Person(1, "Alice", DaysOfWeek.FRIDAY, Gender.FEMALE));
        repo.addPerson(new Person(2, "Bob", DaysOfWeek.MONDAY, Gender.MALE));
        repo.addPerson(new Person(3, "Charlie", DaysOfWeek.SUNDAY, Gender.DIVERSE));
        repo.addPerson(new Person(4, "Dana", DaysOfWeek.WEDNESDAY, Gender.FEMALE));

        Optional<Person> presentPersonID = repo.getById(20);
        Optional<Person> presentPersonName = repo.getByName("Charlie");

        presentPersonID.ifPresentOrElse(
                person -> System.out.println("Found by ID: " + person),
                () -> System.out.println("Person not found by ID")
        );

        presentPersonName.ifPresentOrElse(
                person -> System.out.println("Found by Name: " + person),
                () -> System.out.println("Person not found by Name"));


        long maleCounts = repo.countMales();
        System.out.println(maleCounts);

        Map<Gender, Long> genderCounts = repo.countGenders();
        System.out.println(genderCounts);

        Map<Gender, Long> weekendCounts = repo.countWeekendFavoritesByGender();
        System.out.println(weekendCounts);

        Map<Gender, Map<String, Long>> report = repo.fullGenderFavoriteDayReport();
        System.out.println(report);

        // Ausgangsliste
        List<Integer> numbers = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 8, 86, 9, 111, 234, 763));

        // Schritt 1: Filter nur gerade Zahlen
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("Schritt 1 - Gerade Zahlen: " + evenNumbers);

        // Schritt 2: map - Verdopple jede Zahl
        List<Integer> doubledNumbers = evenNumbers.stream()
                .map(n -> n * 2)
                .toList();
        System.out.println("Schritt 2 - Verdoppelte Zahlen: " + doubledNumbers);

        // Schritt 3: Sortiere die Liste aufsteigend
        List<Integer> sortedNumbers = doubledNumbers.stream()
                .sorted()
                .toList();
        System.out.println("Schritt 3 - Sortierte Zahlen: " + sortedNumbers);

        // Schritt 4: Reduce - Summe aller Zahlen berechnen
        int sum = sortedNumbers.stream()
                .reduce(0, Integer::sum);
        System.out.println("Schritt 4 - Summe aller Zahlen: " + sum);

        // Schritt 5: forEach - Ausgabe jeder verarbeiteten Zahl
        System.out.println("Schritt 5 - Ausgabe jeder Zahl:");
        sortedNumbers.forEach(System.out::println);

        // Schritt 6: Sammle die verarbeiteten Zahlen in einer neuen Liste
        List<Integer> finalList = sortedNumbers.stream()
                .toList();
        System.out.println("Schritt 6 - Neue Liste: " + finalList);

        try (Stream<String> lines = Files.lines(Path.of("students.csv"))) {

            List<Student> students = lines
                    .skip(1) // Header entfernen
                    .map(line -> {
                        String[] parts = line.split(",");
                        try {
                            if (parts.length != 4) return null;
                            String id = parts[0].trim();
                            String name = parts[1].trim();
                            String postalCode = parts[2].trim();
                            int age = Integer.parseInt(parts[3].trim());
                            return new Student(Integer.parseInt(id), name, Integer.parseInt(postalCode), age);
                        } catch (Exception e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .distinct()
                    .toList();


            students.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
