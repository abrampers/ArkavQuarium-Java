package itb.arkavquarium;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SnailTest {
  private Aquarium a;

  @Before
  public void setUp() {
    a = new Aquarium(0, 0, 100, 100);
    // Purge the aquarium
    Guppy g1 = a.getContentGuppy().iterator().next();
    a.getContentGuppy().remove(g1);
    Snail s1 = a.getContentSnail().iterator().next();
    a.getContentSnail().remove(s1);
  }

  @Test
  public void chaseCoinTest() {
    Coin c = new Coin(45, 45, 50, a);
    Snail s = new Snail(a);

    s.setX(15);
    a.getContentCoin().add(c);
    a.getContentSnail().add(s);

    double distBefore = Math.sqrt((c.getX() - s.getX()) * (c.getX() - s.getX()) + (c.getY() - s.getY()) * (c.getY() - s.getY()));
    a.updateState(1);
    double distAfter = Math.sqrt((c.getX() - s.getX()) * (c.getX() - s.getX()) + (c.getY() - s.getY()) * (c.getY() - s.getY()));
    assertTrue("Snail didn't correctly chase coin", distAfter < distBefore || s.getState() == State.turningLeft || s.getState() == State.turningRight);
  }

  @Test
  public void yTest() {
    Coin c = new Coin(45, a.getYMax(), 50, a);
    Snail s = new Snail(a);
    s.setX(15);
    a.getContentCoin().add(c);
    a.getContentSnail().add(s);
    a.updateState(0.2);
    assertEquals("Somehow Snail can swim",  a.getYMax(), s.getY(), 0.01);
  }

  @Test
  public void eatCoinTest() {
    Coin c = new Coin(45, a.getYMax(), 50, a);
    Snail s = new Snail(a);
    s.setX(50);
    a.getContentCoin().add(c);
    a.getContentSnail().add(s);
    a.updateState(1);
    assertTrue("Coin is not being eaten", a.getContentCoin().isEmpty());
  }
}