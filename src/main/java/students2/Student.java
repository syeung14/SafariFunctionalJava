package students2;

import java.util.List;
import java.util.function.Predicate;

public class Student {
  private String name;
  private int grade;
  private List<String> courses;

  public Student(String name, int grade, List<String> courses) {
    this.name = name;
    this.grade = grade;
    this.courses = courses;
  }

  public Student(String name, int grade, String ... courses) {
    this(name, grade, List.of(courses));
  }

  public String getName() {
    return name;
  }

  public int getGrade() {
    return grade;
  }

  public Student withGrade(int grade) {
    // need not waste lots of memory
    return new Student(this.name, grade, this.courses);
  }

  public List<String> getCourses() {
    return courses;
  }

  // "capturing" a would-have-been-short-lived method local
  // variable (including actual parameters) is call a "closure"
  public static Predicate<Student> getSmartPredicate(int threshold) {
    return s -> s.grade > threshold ;
  }

  public static Predicate<Student> getEnthusiasticPredicate(int threshold) {
    return s -> s.getCourses().size() > threshold;
  }

  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", grade=" + grade +
        ", courses=" + courses +
        '}';
  }
}
