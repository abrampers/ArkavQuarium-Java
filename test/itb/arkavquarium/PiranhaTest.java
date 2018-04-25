package itb.arkavquarium;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PiranhaTest {
  // Stub Aquarium
  private Aquarium a;

  @Before
  public void setUp() {
    a = new Aquarium(0, 0, 100, 100);
    // Purge the aquarium
    Guppy g1 = a.getContentGuppy().iterator().next();
    a.getContentGuppy().remove(g1);
  }

  @Test
  public void chaseGuppyTest() {
    Piranha p = new Piranha(a);
    p.setX(25);
    p.setY(50);
    Guppy g = new Guppy(a);
    g.setX(50);
    g.setY(50);
    p.setHungry(true);
    a.getContentGuppy().add(g);
    a.getContentPiranha().add(p);

    double distBefore = Math.sqrt((p.getX() - g.getX()) * (p.getX() - g.getX()) + (p.getY() - g.getY()) * (p.getY() - g.getY()));
    a.setCurrTime(2);
    p.updateState();
    double distAfter = Math.sqrt((p.getX() - g.getX()) * (p.getX() - g.getX()) + (p.getY() - g.getY()) * (p.getY() - g.getY()));
    // if the test is negative, it's definitely wrong
    // if the test is positive, it's probably right. Test it repeatedly
    assertTrue("Piranha turns left, while the Guppy is in the right", distAfter <= distBefore || (p.getXDir() >= 0 && g.getXDir() >= 0) || (p.getXDir() < 0 && g.getXDir() < 0) || p.getState() == State.turningRight || p.getState() == State.turningLeft);
  }

  @Test
  public void eatGuppyTest() {
    Piranha p = new Piranha(a);
    p.setX(35);
    p.setY(50);
    Guppy g = new Guppy(a);
    g.setX(50);
    g.setY(50);
    p.setHungry(true);
    a.getContentPiranha().add(p);
    a.getContentGuppy().add(g);
    a.updateState(0.1);
    assertTrue("Piranha didn't eat the Guppy", a.getContentGuppy().isEmpty());
  }

  @Test
  public void dropCoinTest() {
    Piranha p = new Piranha(a);
    p.setX(35);
    p.setY(50);
    Guppy g = new Guppy(a);
    g.setX(50);
    g.setY(50);
    p.setHungry(true);
    a.getContentPiranha().add(p);
    a.getContentGuppy().add(g);
    a.updateState(0.1);
    assertTrue("Piranha didn't produce coin after eating Guppy", !a.getContentCoin().isEmpty());
  }

  @Test
  public void deadTest() {
    Piranha p = new Piranha(a);
    a.getContentPiranha().add(p);
    a.updateState(p.getHungerTimeout() + p.getFullInterval());
    a.updateState(p.getHungerTimeout() + 1);
    assertTrue("Piranha goes divine", (p.getState() == State.deadLeft) || (p.getState() == State.deadRight)); // ?
  }
}