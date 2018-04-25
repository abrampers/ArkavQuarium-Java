package itb.arkavquarium;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FishTest {
  // Stub Aquarium
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
  public void eatTest() {
    Pellet p = new Pellet(50, 50, a);
    Guppy g = new Guppy(a);
    g.setX(35);
    g.setY(50);
    g.setHungry(true);
    a.getContentPellet().add(p);
    a.getContentGuppy().add(g);
    a.updateState(0.1);
    assertTrue("Guppy didn't eat the pellet", a.getContentPellet().isEmpty());
  }

  @Test
  public void dropCoinTest() {
    Guppy g = new Guppy(a);
    a.getContentGuppy().add(g);
    a.updateState(Constants.GUPPY_COIN_INTERVAL + 1);
    assertTrue("Guppy didn't produce coin after the interval", !a.getContentCoin().isEmpty());
  }
}