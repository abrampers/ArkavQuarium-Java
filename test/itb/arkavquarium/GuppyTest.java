package itb.arkavquarium;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.junit.Test;

import static org.junit.Assert.*;

public class GuppyTest {
  // Stub Aquarium
  private Aquarium a = new Aquarium(0, 0, 100, 100);

  @Test
  public void chasePelletTest() {
    Pellet p = new Pellet(50, 50, a);
    Guppy g = new Guppy(a);
    g.setX(25);
    g.setY(50);
    g.setHungry(true);
    a.getContentPellet().add(p);
    a.getContentGuppy().add(g);
    a.updateState(0.1);
    // if the test is negative, it's definitely wrong
    // if the test is positive, it's probably right. Test it repeatedly
    assertTrue("Guppy turns left, while the pellet is in the right", g.getX() > 25);
  }

  @Test
  public void eatPelletTest() {
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

  @Test
  public void deadTest() {
    Guppy g = new Guppy(a);
    g.setHungry(true);
    a.getContentGuppy().add(g);
    a.updateState(1000);
    System.out.println(a.getContentGuppy().getLength());
    assertTrue("Guppy goes divine", a.getContentGuppy().isEmpty());
  }

}