package students;

import java.util.List;

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

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", grade=" + grade +
        ", courses=" + courses +
        '}';
  }
}
