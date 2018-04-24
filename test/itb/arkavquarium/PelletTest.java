package itb.arkavquarium;

import org.junit.Test;

import static org.junit.Assert.*;

public class PelletTest {
  // Stub Aquarium
  private final Aquarium a = new Aquarium(0, 0, 100, 100);

  /*
   This is a test just for the pellet mechanics.
   For a test where the pellet gets eaten by a Guppy, see Guppy test.
  */

  @Test
  public void fallTest() {
    Pellet p = new Pellet(50, 50, a);
    a.getContentPellet().add(p);
    a.updateState(5.0); // this will move the pellet
    assertTrue("Pellet is not falling", p.getY() > 50);
  }

  @Test
  public void xTest() {
    Pellet p = new Pellet(50, 50, a);
    a.getContentPellet().add(p);
    a.updateState(5.0);
    assertEquals("Pellet's abcissa changed", 50, p.getX(), 0.01);
  }

  @Test
  public void deadTest() {
    Pellet p = new Pellet(50, 99, a);
    a.getContentPellet().add(p);
    a.updateState(100.0);
    // At this time, the pellet is in the bottom of the aquarium
    assertTrue("Pellet goes divine", a.getContentPellet().isEmpty());
  }
}