package usestreams;

import students2.Student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleExample {
  public static void main(String[] args) {
    List<Student> school = List.of(
        new Student("Fred", 72, "Math", "Physics"),
        new Student("Freddy", 68, "Math"),
        new Student("Frederick", 78, "Math", "Physics"),
        new Student("Jim", 60, "Journalism"),
        new Student("Jimmy", 68, "Journalism", "Art History"),
        new Student("James", 79, "Journalism", "English"),
        new Student("Sheila", 92, "Math", "Physics",
            "Astrophysics", "Quantum mechanics")
    );

    Stream<String> ss = school.stream()
        .map(s -> {
          System.out.println(s);
          return s.getName();
        });
    List<String> intermediate = ss.collect(Collectors.toList());
    intermediate.forEach(s -> System.out.println(s));
    System.out.println("----------------");
//    school.stream()
//        .map(s -> {
//          System.out.println(s);
//          return s.getName();
//        }).forEach(s -> System.out.println(s));
    intermediate.stream()
        .map(s -> s.toUpperCase())
        .forEach(s -> System.out.println(s));
  }
}
