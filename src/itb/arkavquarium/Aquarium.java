package itb.arkavquarium;

import java.util.Iterator;

/**
 * <h1>Aquarium! The Aquarium class implements and updates all objects in Aquarium. This
 * simulates movement and state of the objects.</h1>
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
public class Aquarium {
  /* ---------------------------------------- */
  /* -------------- Attributes -------------- */
  /* ---------------------------------------- */

  private final double xmin;
  private final double ymin;
  private final double xmax;
  private final double ymax;
  private double currTime;
  private final LinkedList<Piranha> contentPiranha;
  private final LinkedList<Guppy> contentGuppy;
  private final LinkedList<Snail> contentSnail;
  private final LinkedList<Pellet> contentPellet;
  private final LinkedList<Coin> contentCoin;

  /*------------------------------------------*/
  /* ------------- Constructors ------------- */
  /*------------------------------------------*/

  /**
   * A constructor. Constructs a new Aquarium object.
   *
   * @param xmin Minium x value in the aquarium.
   * @param ymin Minium y value in the aquarium.
   * @param xmax Maximum x value in the aquarium.
   * @param ymax Maximum y value in the aquarium.
   */
  public Aquarium(double xmin, double ymin, double xmax, double ymax) {
    this.currTime = 0;
    this.xmin = xmin;
    this.ymin = ymin;
    this.xmax = xmax;
    this.ymax = ymax;

    this.contentPiranha = new LinkedList<>();
    this.contentGuppy = new LinkedList<>();
    this.contentSnail = new LinkedList<>();
    this.contentCoin = new LinkedList<>();
    this.contentPellet = new LinkedList<>();

    this.createGuppy();
    this.createSnail();
  }

  /*------------------------------------------*/
  /* ------------ Setter & Getter ----------- */
  /*------------------------------------------*/

  /**
   * Getter for X_MIN.
   *
   * @return The value of X_MIN
   */
  public double getXMin() {
    return xmin;
  }

  /**
   * Getter for Y_MIN.
   *
   * @return The value of Y_MIN
   */
  public double getYMin() {
    return ymin;
  }

  /**
   * Getter for X_MAX.
   *
   * @return The value of X_MAX
   */
  public double getXMax() {
    return xmax;
  }

  /**
   * Getter for Y_MAX.
   *
   * @return The value of Y_MAX
   */
  public double getYMax() {
    return ymax;
  }

  /**
   * Getter for currentTime.
   *
   * @return The value of currentTime
   */
  public double getCurrTime() {
    return currTime;
  }

  /**
   * Getter for contentPiranha.
   *
   * @return The contentPiranha list
   */
  public LinkedList<Piranha> getContentPiranha() {
    return contentPiranha;
  }

  /**
   * Getter for contentGuppy.
   *
   * @return The contentGuppy list
   */
  public LinkedList<Guppy> getContentGuppy() {
    return contentGuppy;
  }

  /**
   * Getter for contentSnail.
   *
   * @return The contentSnail list
   */
  public LinkedList<Snail> getContentSnail() {
    return contentSnail;
  }

  /**
   * Getter for contentPellet.
   *
   * @return The contentPellet list
   */
  public LinkedList<Pellet> getContentPellet() {
    return contentPellet;
  }

  /**
   * Getter for contentCoin.
   *
   * @return The contentCoin list
   */
  public LinkedList<Coin> getContentCoin() {
    return contentCoin;
  }

  /*------------------------------------------*/
  /* ---------------- Methods --------------- */
  /*------------------------------------------*/

  /**
   * Updates all the aquarium object state.
   *
   * @param delta The delta of the last current time and new current time
   */
  public void updateState(double delta) {
    this.currTime += delta;
    /* Piranha */
    Iterator<Piranha> piranhaItr = contentPiranha.iterator();
    while (piranhaItr.hasNext()) {
      piranhaItr.next().updateState();
    }
    /* Guppy */
    Iterator<Guppy> guppyItr = contentGuppy.iterator();
    while (guppyItr.hasNext()) {
      guppyItr.next().updateState();
    }
    /* Snail */
    Iterator<Snail> snailItr = contentSnail.iterator();
    while (snailItr.hasNext()) {
      snailItr.next().updateState();
    }
    /* Pellet */
    Iterator<Pellet> pelletItr = contentPellet.iterator();
    while (pelletItr.hasNext()) {
      pelletItr.next().updateState();
    }
    /* Coin */
    Iterator<Coin> coinItr = contentCoin.iterator();
    while (coinItr.hasNext()) {
      coinItr.next().updateState();
    }
  }

  /**
   * Create Piranha. Creates a new Piranha and adds it to contentPiranha list.
   */
  public void createPiranha() {
    contentPiranha.add(new Piranha(this));
  }

  /**
   * Create Guppy. Creates a new Guppy and adds it to contentGuppy list.
   */
  public void createGuppy() {
    contentGuppy.add(new Guppy(this));
  }

  /**
   * Create Snail. Creates a new Snail and adds it to contentSnail list.
   */
  public void createSnail() {
    contentSnail.add(new Snail(this));
  }

  /**
   * Create Pellet. Creates a new Pellet and adds it to contentPellet list.
   *
   * @param x x-axis position of the newly created Pellet.
   * @param y y-axis position of the newly created Pellet.
   */
  public void createPellet(double x, double y) {
    contentPellet.add(new Pellet(x, y, this));
  }

  /**
   * Create Coin. Creates a new Coin and adds it to contentCoin list.
   *
   * @param x x-axis position of the newly created Coin.
   * @param y y-axis position of the newly created Coin.
   * @param value the value of the Coin
   */
  public void createCoin(double x, double y, int value) {
    contentCoin.add(new Coin(x, y, value, this));
  }

  /**
   * Delete Piranha. Deletes a Piranha and removes it from contentPiranha list.
   *
   * @param p The Piranha object to be deleted.
   */
  public void deletePiranha(Piranha p) {
    contentPiranha.remove(p);
  }

  /**
   * Delete Guppy. Deletes a Guppy and removes it from contentGuppy list.
   *
   * @param g The Guppy object to be deleted.
   */
  public void deleteGuppy(Guppy g) {
    contentGuppy.remove(g);
  }

  /**
   * Delete Snail. Deletes a Snail and removes it from contentSnail list.
   *
   * @param s The Snail object to be deleted.
   */
  public void deleteSnail(Snail s) {
    contentSnail.remove(s);
  }

  /**
   * Delete Pellet. Deletes a Pellet and removes it from contentPellet list.
   *
   * @param p The Pellet object to be deleted.
   */
  public void deletePellet(Pellet p) {
    contentPellet.remove(p);
  }

  /**
   * Delete Coin. Deletes a Coin and removes it from contentCoin list.
   *
   * @param c The Coin object to be deleted.
   */
  public void deleteCoin(Coin c) {
    contentCoin.remove(c);
  }
}
