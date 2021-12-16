package students2;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class School {
//  public static <E> Predicate<E> negate(Predicate<E> crit) {
//    return s -> !crit.test(s);
//  }
//
//  public static <E> Predicate<E> and(
//      Predicate<E> first, Predicate<E> second) {
//    return s -> first.test(s) && second.test(s);
//  }
//
//  public static <E> Predicate<E> or(
//      Predicate<E> first, Predicate<E> second) {
//    return s -> first.test(s) || second.test(s);
//  }

  public static void showAll(Iterable<?> ls) {
    for (Object s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("-----------------------");
  }

  // functional programming describes the "command pattern"
  // and also the "behavior factory" that is Student.getSmartPredicate
  // as "higher order functions"
  public static <E> List<E> getByPredicate(
      Iterable<E> ls, Predicate<E> cs) {
    List<E> res = new ArrayList<>();
    for (E s : ls) {
      if (cs.test(s)) {
        res.add(s);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    List<Student> school = List.of(
        new Student("Fred", 72, "Math", "Physics"),
        new Student("Jim", 60, "Journalism"),
        new Student("Sheila", 92, "Math", "Physics",
            "Astrophysics", "Quantum mechanics")
    );
    showAll(school);
    showAll(getByPredicate(school,
        (Student s) -> {
          return s.getCourses().size() < 3;
        }
    ));

    Predicate<Student> smart = Student.getSmartPredicate(70);
//    Predicate<Student> notSmart = Predicate.not(smart);
    Predicate<Student> notSmart = smart.negate();
    showAll(getByPredicate(school, smart));
    showAll(getByPredicate(school, notSmart));

    Predicate<Student> enthusiastic = Student.getEnthusiasticPredicate(3);
    showAll(getByPredicate(school, smart.and(enthusiastic.negate())));

    showAll(getByPredicate(List.of("Fred", "Jim", "Sheila"),
        s -> s.length() > 3));
  }
}
