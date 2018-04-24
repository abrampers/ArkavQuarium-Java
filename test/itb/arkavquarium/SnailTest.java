package itb.arkavquarium;

import org.junit.Test;

import static org.junit.Assert.*;

public class SnailTest {
  // Stub Aquarium
  private final Aquarium a = new Aquarium(0, 0, 100, 100);

  @Test
  public void chaseCoinTest() {
    Coin c = new Coin(45, a.getYMax(), 50, a);
    Snail s = new Snail(a);
    s.setX(15);
    a.getContentCoin().add(c);
    a.getContentSnail().add(s);
    a.updateState(0.2);
    assertEquals("Snail didn't correctly chase coin", State.movingRight,  s.getState());
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