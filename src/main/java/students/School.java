package students;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// CriterionStudent defines EXACTLY ONE abstract method
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

class NotSmartStudent implements CriterionStudent {
  @Override
  public boolean test(Student s) {
    return s.getGrade() < 70;
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
    showAll(getByCriterion(school, new NotSmartStudent()));
    showAll(getByCriterion(school, new EnthusiasticStudent()));

    // Compiler uses this "skeleton" of a single method.
    // it KNOWS that it needs an OBJECT instance, and
    // that instance MUST implement CriterionStudent
    // and because that CriterionStudent declares EXACTLY ONE
    // abstract method, it (compiler) can build a class using
    // this single method-like "piece" and then instantiate
    // that class
    showAll(getByCriterion(school,
        (Student s) -> {
          return s.getCourses().size() < 3;
        }
    ));
//    showAll(getSmart(school, 50));
//    showAll(getEnthusiastic(school, 2));

    Runnable r =
        () -> { System.out.println("Hello"); };
        // NOPE Java does NOT permit "headless" lambdas
//         -> { System.out.println("Hello"); };

    CriterionStudent obj;
//    obj = (Student s) -> { return s.getCourses().size() < 3; };
    // leaving out the type requires:
    // a) all argument types must be unambigously known
    // b) all argument types must be not-specified
//    obj = (s) -> { return s.getCourses().size() < 3; };
    // var only works if used for ALL the arguments
    // and they must all be unambigously known
    // this is useful for annotation
//    obj = (@Deprecated var s) -> { return s.getCourses().size() < 3; };
//    obj = s -> { return s.getCourses().size() < 3; }; // block lambda
    obj = s -> s.getCourses().size() < 3  ;  // Expression lambda


// NOPE, obj is an object that implements CriterionStudent
// it is NOT a "function"
//    obj(myStudent)

    System.out.println("class of obj is: " + obj.getClass().getName());
    Method[] methods = obj.getClass().getMethods();
    for (Method m : methods) {
      System.out.println(m);
    }

    Object obj2 = (CriterionStudent & Serializable)(Student s) -> {
      return s.getCourses().size() < 3;
    };
  }
}
