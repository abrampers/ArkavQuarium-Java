package itb.arkavquarium;

import static org.junit.Assert.*;

import java.util.Iterator;
import org.junit.Test;

public class LinkedListTest {

  /*
  Test for LinkedList's methods
   */

  @Test
  public void isEmpty() {
    LinkedList<Integer> l = new LinkedList<>();
    assertTrue("Initialization returns non-empty list", l.isEmpty());
    l.add(5);
    assertFalse("Element addition doesn't make list non-empty", l.isEmpty());
  }

  @Test
  public void add() {
    LinkedList<Integer> l = new LinkedList<>();
    Iterator<Integer> intIterator = l.iterator();
    boolean found = false;
    l.add(10);
    while (intIterator.hasNext()) {
      if (intIterator.next() == 10) {
        found = true;
      }
    }
    assertTrue("Element addition failed", found);
  }

  @Test
  public void remove() {
    LinkedList<Integer> l = new LinkedList<>();
    Iterator<Integer> intIterator = l.iterator();
    l.add(13);
    l.remove(13);
    while (intIterator.hasNext()) {
      if (intIterator.next() == 13) {
        fail("Element removal failed");
      }
    }
  }

  @Test
  public void iterator() {
    LinkedList<Integer> l = new LinkedList<>();
    Iterator<Integer> intIterator = l.iterator();
    int i;
    int[] elementExample = new int[5];
    for (i = 0; i < elementExample.length; i++) {
      l.add(i);
      elementExample[i] = i;
    }
    i = 0;
    while (intIterator.hasNext()) {
      if (intIterator.next() != elementExample[i]) {
        fail("Element called by iterator is not equal to the data inserted");
      }
      i++;
    }
  }
}