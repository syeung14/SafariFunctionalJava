package superiterable;

import students2.Student;

import java.util.List;

public class UseSuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(
        List.of("Fred", "Jim", "Sheila")
    );

    sis.forEach(s -> System.out.println("> " + s));
//    for (String s : sis) {
//      System.out.println("> " + s);
//    }

//    SuperIterable<String> longStrings = sis.filter(s -> s.length() > 3);
//    longStrings.forEvery(s -> System.out.println(">>> " + s));

//    for (String s : longStrings) {
//      System.out.println(">>> " + s);
//    }

    sis
        .filter(s -> s.length() > 3)
        .map(s -> s.length())
        .forEach(s -> System.out.println(">>> " + s));

    SuperIterable<Student> school = new SuperIterable<>(List.of(
        new Student("Fred", 72, "Math", "Physics"),
        new Student("Jim", 60, "Journalism"),
        new Student("Jimmy", 68, "Journalism", "Art History"),
        new Student("James", 79, "Journalism", "English"),
        new Student("Sheila", 92, "Math", "Physics",
            "Astrophysics", "Quantum mechanics")
    ));

    school
        .filter(s -> s.getGrade() > 70)
        .map(s -> s.getName() + " scored " + s.getGrade())
        .forEach(m -> System.out.println(m));

    school
        .filter(s -> s.getGrade() > 70)
        .flatMap(s -> new SuperIterable<>(s.getCourses()))
//        .map(s -> s.length())
        .forEach(s -> System.out.println(s));
  }
}
