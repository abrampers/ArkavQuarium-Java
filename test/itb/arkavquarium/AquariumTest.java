package itb.arkavquarium;

import java.util.Iterator;

import org.junit.Test;

import static org.junit.Assert.*;

public class AquariumTest {
  /* Aquarium object for testing */
  private Aquarium a = new Aquarium(0, 0, 100, 100);

  /*
  Test for Aquarium's mechanics
   */

  @Test
  public void updateState() {
    a.updateState(50);
    assertEquals("Current time not incremented properly", 50, a.getCurrTime(), 0.01);
  }

  @Test
  public void createPiranha() {
    int initLength = a.getContentPiranha().getLength();
    a.createPiranha();
    assertTrue("Piranha creation failed", a.getContentPiranha().getLength() > initLength);
  }

  @Test
  public void createGuppy() {
    int initLength = a.getContentGuppy().getLength();
    a.createGuppy();
    assertTrue("Guppy creation failed", a.getContentGuppy().getLength() > initLength);
  }

  @Test
  public void createSnail() {
    int initLength = a.getContentSnail().getLength();
    a.createSnail();
    assertTrue("Snail creation failed", a.getContentSnail().getLength() > initLength);
  }

  @Test
  public void createPellet() {
    int initLength = a.getContentPellet().getLength();
    a.createPellet(10, 10);
    assertTrue("Pellet creation failed", a.getContentPellet().getLength() > initLength);
  }

  @Test
  public void createCoin() {
    int initLength = a.getContentCoin().getLength();
    a.createCoin(15, 15, 100);
    assertTrue("Coin creation failed", a.getContentCoin().getLength() > initLength);
  }

  @Test
  public void deletePiranha() {
    Piranha p = null;
    Iterator<Piranha> piranhaIterator = a.getContentPiranha().iterator();
    a.createPiranha();
    if (piranhaIterator.hasNext()) {
      p = piranhaIterator.next();
    }
    else {
      fail("Piranha creation failed");
    }
    a.deletePiranha(p);
    piranhaIterator = a.getContentPiranha().iterator();
    while (piranhaIterator.hasNext()) {
      if (piranhaIterator.next() == p) {
        fail("Piranha deletion failed");
      }
    }
  }

  @Test
  public void deleteGuppy() {
    Guppy g = null;
    Iterator<Guppy> guppyIterator = a.getContentGuppy().iterator();
    a.createGuppy();
    if (guppyIterator.hasNext()) {
      g = guppyIterator.next();
    }
    else {
      fail("Guppy creation failed");
    }
    a.deleteGuppy(g);
    guppyIterator = a.getContentGuppy().iterator();
    while (guppyIterator.hasNext()) {
      if (guppyIterator.next() == g) {
        fail("Guppy deletion failed");
      }
    }
  }

  @Test
  public void deleteSnail() {
    Snail s = null;
    Iterator<Snail> snailIterator = a.getContentSnail().iterator();
    a.createSnail();
    if (snailIterator.hasNext()) {
      s = snailIterator.next();
    }
    else {
      fail("Snail creation failed");
    }
    a.deleteSnail(s);
    snailIterator = a.getContentSnail().iterator();
    while (snailIterator.hasNext()) {
      if (snailIterator.next() == s) {
        fail("Snail deletion failed");
      }
    }
  }

  @Test
  public void deletePellet() {
    Pellet p = null;
    Iterator<Pellet> pelletIterator = a.getContentPellet().iterator();
    a.createPellet(10, 10);
    if (pelletIterator.hasNext()) {
      p = pelletIterator.next();
    }
    else {
      fail("Pellet creation failed");
    }
    a.deletePellet(p);
    pelletIterator = a.getContentPellet().iterator();
    while (pelletIterator.hasNext()) {
      if (pelletIterator.next() == p) {
        fail("Pellet deletion failed");
      }
    }
  }

  @Test
  public void deleteCoin() {
    Coin c = null;
    Iterator<Coin> coinIterator = a.getContentCoin().iterator();
    a.createCoin(15, 15, 100);
    if (coinIterator.hasNext()) {
      c = coinIterator.next();
    }
    else {
      fail("Coin creation failed");
    }
    a.deleteCoin(c);
    coinIterator = a.getContentCoin().iterator();
    while (coinIterator.hasNext()) {
      if (coinIterator.next() == c) {
        fail("Coin deletion failed");
      }
    }
  }
}