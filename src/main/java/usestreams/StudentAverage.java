package usestreams;

import students2.Student;

import java.util.List;
import java.util.Optional;

class Average {
  private double sum;
  private long count;

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public Average merge(Average other) {
    return new Average(this.sum + other.sum, this.count + other.count);
  }

  public double getTheAverage() {
    return sum / count;
  }

  public Optional<Double> get() {
    if (count != 0) {
      return Optional.of(sum / count);
    } else {
      return Optional.empty();
    }
  }

  @Override
  public String toString() {
    return "Average{" +
        "sum=" + sum +
        ", count=" + count +
        '}';
  }
}

public class StudentAverage {
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

    Optional<Average> oa = school.stream()
        .filter(s -> s.getCourses().contains("Math"))
        .filter(s -> s.getCourses().contains("Algebra"))
        .map(s -> new Average(s.getGrade(), 1))
        .reduce((a1, a2) -> a1.merge(a2));
    oa.ifPresent(a -> System.out.println("average is " +
        a.getTheAverage()));
    System.out.println("All done");
  }
}
