package collections;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Optional;

class Average {
  private double sum;
  private long count;

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public void include(double d) {
    this.sum += d;
    this.count++;
  }

  public void merge(Average other) {
    this.sum += other.sum;
    this.count += other.count;
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

public class LargeAverages {
  public static void main(String[] args) {
    long start = System.nanoTime();
    ThreadLocalRandom.current().doubles(8_000_000_000L, -Math.PI, +Math.PI)
        .parallel()
        // collect and the three-argument version of reduce
        // sometimes show up as "fold/foldRight" or perhaps "aggregate"
        // "groupingByKey" keep an eye out
        .collect(() -> new Average(0, 0),
            (a, d) -> a.include(d),
            (a1, a2) -> a1.merge(a2))
        .get()
        .map(d -> "Average is " + d)
        .ifPresent(m -> System.out.println(m));
    long time = System.nanoTime() - start;
    System.out.println("time taken: " + (time / 1_000_000_000.0));

  }
}
