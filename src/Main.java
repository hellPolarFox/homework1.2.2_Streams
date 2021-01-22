import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count + " - количество несовершеннолетних жителей");


        List<String> recruits = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(recruits.size() + " - количество мужчин призывного возраста");

        List<Person> workable = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < 65)
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workable.size() + " - количество работоспособных жителей с высшим образованием");



        // для самопроверки
        /*int i = 0;
        for (Person p : persons) {
            if (p.getAge() < 18) i++;
        }
        System.out.println(i + " - подсчитано без стримов");*/

        // для самопроверки
        /*List<String> recr = new ArrayList<>();
        for (Person p : persons) {
            if (p.getAge() >= 18 && p.getAge() <= 27 && p.getSex().equals(Sex.MAN)) {
                recr.add(p.getFamily());
            }
        }
        System.out.println(recr.size() + " - подсчитано без стримов");*/

        // для самопроверки
        /*List<Person> work = new ArrayList<>();
        for (Person p : persons) {
            if (p.getAge() >= 18 && p.getAge() < 65 && p.getEducation().equals(Education.HIGHER)) {
                work.add(p);
            }
        }
        System.out.println(workers.size() + " - подсчитано без стримов");*/
    }
}
