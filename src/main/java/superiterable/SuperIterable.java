package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }


  // this is built into Iterable as "forEach"
//  public void forEvery(SuperIterable<E> this, Consumer<E> op) {
//    for (E e : this.self) {
//      op.accept(e);
//    }
//  }

  // if you have a "bucket of stuff" which has a "map" function
  // which produces a new bucket of stuff containing one item
  // for every one of the original items, then we (probably,
  // with some math-rules constraints) call this a Functor
  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> res = new ArrayList<>();
    for (E e : this.self) {
      F f = op.apply(e);
      res.add(f);
    }
    return new SuperIterable<>(res);
  }

  // if your bucket of stuff has a flatMap method "like this"
  // then it's probably called a "Monad"
  public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>> op) {
    List<F> res = new ArrayList<>();
    for (E e : this.self) {
      SuperIterable<F> manyF = op.apply(e);
      for (F oneF : manyF) {
        res.add(oneF);
      }
    }
    return new SuperIterable<>(res);
  }

  // dispose of duplicates?
  // sort items?
  // *** produce a single result
  // doing all of this in parallel

  //  public SuperIterable<E> filter(SuperIterable<E> this, Predicate<E> crit) {
  public SuperIterable<E> filter(Predicate<E> crit) {
    // divide the initial data into 8 sub-chunks
    // send each chunk to a different CPU (Thread)
    // have each CPU (Thread) filter the subset of data
    // bring the results back together
    // THIS ONLY works safely if the Predicate "has no side effects"

    List<E> res = new ArrayList<>();
    for (E e : this.self) {
      if (crit.test(e)) {
        res.add(e);
      }
    }
    return new SuperIterable<>(res);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }
}
