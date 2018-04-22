package itb.arkavquarium;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoinTest {
  // Stub Aquarium
  private Aquarium a;

  @Before
  public void setUp() throws Exception {
    a = new Aquarium(0, 0, 100, 100);
  }

  /*
   This is a test just for the coin mechanics.
   For a test where the pellet gets consumed by a Snail, see Snail test.
  */

  @Test
  public void fallTest() {
    Coin c = new Coin(50, 50, 50, a);
    a.getContentCoin().add(c);
    a.updateState(5.0);
    assertTrue("Coin is not falling", c.getY() > 50);
  }

  @Test
  public void xTest() {
    Coin c = new Coin(50, 50, 50, a);
    a.getContentCoin().add(c);
    a.updateState(5.0);
    assertEquals("Coin's abscissa changed", 50, c.getX(), 0.01);
  }

  @Test
  public void bottomTest() {
    Coin c = new Coin(50, 99, 50, a);
    a.getContentCoin().add(c);
    a.updateState(8 + 2); // less than coin deletion interval
    assertEquals("Coin falls through the aquarium",  c.getY(), a.getYMax(), 0.01);
  }

  @Test
  public void deletionTest() {
    Coin c = new Coin(50, a.getYMax() + 100, 50, a);
    a.getContentCoin().add(c);
    //for (int i = 0; i < 8 + Constants.COIN_DELETION_INTERVAL + 1; i++) a.updateState(1);
    a.updateState((Constants.COIN_DELETION_INTERVAL + 1) / 1000); // more than coin deletion interval
    System.out.println(a.getContentCoin().getLength());
    assertTrue("Coin goes divine", a.getContentCoin().isEmpty());
  }
}