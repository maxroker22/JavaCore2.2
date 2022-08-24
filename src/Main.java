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
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }
        //1
        Stream<Person> kids = persons.stream();
        System.out.println("1 " + persons.stream()
                .filter(x -> x.getAge() < 18)
                .count());
        //2
        List<Person> warriors = Arrays.asList();
        System.out.println("2 " + persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 27)
                .filter(x -> x.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList()));
        //3
        List<Person> workPeople = Arrays.asList();
        System.out.println("3 " + persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getSex() == Sex.MAN & x.getAge() < 65 || x.getSex() == Sex.WOMAN & x.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList()));
    }
}
