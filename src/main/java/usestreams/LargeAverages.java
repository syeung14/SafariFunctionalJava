package usestreams;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LargeAverages {
  public static void main(String[] args) {
//    Stream.generate(() -> Math.random())
//        .limit(10)
//        .forEach(d -> System.out.println(d));

//    IntStream.iterate(0, x -> x + 1)
//        .forEach(i -> System.out.println(i));

    long start = System.nanoTime();
    ThreadLocalRandom.current().doubles(4_000_000_000L, -Math.PI, +Math.PI)
        .parallel()
        .mapToObj(d -> new Average(d, 1))
        .reduce((a1, a2) -> a1.merge(a2))
        .get()
        .get()
        .ifPresent(av -> System.out.println("Average is " + av));
    long time = System.nanoTime() - start;
    System.out.println("time taken: " + (time / 1_000_000_000.0));

  }
}
