package superiterable;

import java.util.List;

public class UseSuperIterable {
  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(
        List.of("Fred", "Jim", "Sheila")
    );

    for (String s : sis) {
      System.out.println("> " + s);
    }

    SuperIterable<String> longStrings = sis.filter(s -> s.length() > 3);
    for (String s : longStrings) {
      System.out.println(">>> " + s);
    }
  }
}
