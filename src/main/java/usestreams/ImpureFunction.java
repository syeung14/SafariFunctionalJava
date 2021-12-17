package usestreams;

import java.util.stream.Stream;

public class ImpureFunction {
  public static void main(String[] args) {
    long [] counter = { 0 };
    Stream.generate(() -> "")
        .parallel()
        .limit(100_000)
        .map(s -> {
          counter[0]++; // THIS IS NOT PURE!!! IT FAILS
          return s;
        })
        .reduce((s1, s2) -> "");
    System.out.println("counter has value " + counter[0]);

  }
}
