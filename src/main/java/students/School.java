package students;

import java.util.ArrayList;
import java.util.List;

interface CriterionStudent {
  boolean test(Student s);
}

class SmartStudent implements CriterionStudent {
  private int threshold;

  public SmartStudent(int threshold) {
    this.threshold = threshold;
  }

  @Override
  public boolean test(Student s) {
    return s.getGrade() > threshold;
  }
}

class EnthusiasticStudent implements CriterionStudent {
  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 2;
  }
}

public class School {
  public static void showAll(Iterable<?> ls) {
//  public static <E> void showAll(Iterable<E> ls) {
//    for (E s : ls) {
    for (Object s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("-----------------------");
  }

  public static List<Student> getLegalSmart(Iterable<Student> ls) {
    return getByCriterion(ls, new SmartStudent(75));
  }

  // Command pattern!!! pass an object because of the
  // BEHAVIOR that it contains, that behavior "tailors"
  // the main behavior to get just the perfect result
  public static List<Student> getByCriterion(
      Iterable<Student> ls, CriterionStudent cs) {
    List<Student> res = new ArrayList<>();
    for (Student s : ls) {
      if (cs.test(s)) {
        res.add(s);
      }
    }
    return res;
  }

//  public static List<Student> getSmart(Iterable<Student> ls, int threshold) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getGrade() > threshold) {
//        res.add(s);
//      }
//    }
//    return res;
//  }
//
//  public static List<Student> getEnthusiastic(Iterable<Student> ls, int threshold) {
//    List<Student> res = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getCourses().size() > threshold) {
//        res.add(s);
//      }
//    }
//    return res;
//  }
//
  public static void main(String[] args) {
    List<Student> school = List.of(
        new Student("Fred", 72, "Math", "Physics"),
        new Student("Jim", 60, "Journalism"),
        new Student("Sheila", 92, "Math", "Physics",
            "Astrophysics", "Quantum mechanics")
    );
    showAll(school);
    showAll(getByCriterion(school, new SmartStudent(70)));
    showAll(getByCriterion(school, new EnthusiasticStudent()));
//    showAll(getSmart(school, 50));
//    showAll(getEnthusiastic(school, 2));
  }
}
