package itb.arkavquarium;

/**
 * <h1>Fish! The abstract Fish class implements the behaviour of Fish. This contains the
 * information needed for the Fish to act.</h1>
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
abstract class Fish {

  private final int foodThres;
  private final double eatRadius;
  private final double fullInterval;
  private final double hungerTimeout;
  /*------------------------------------------*/
  /* -------------- Attributes -------------- */
  /*------------------------------------------*/
  private double lastEatTime;
  private double lastRandomTime;
  private double lastHungerTime;
  private int foodEaten;
  private boolean hungry;
  private int level;
  private double dirX;
  private double dirY;

  /*------------------------------------------*/
  /* ------------- Constructors ------------- */
  /*------------------------------------------*/

  /**
   * A constructor. Constructs a new Fish object.
   *
   * @param foodThres Number of food to level up.
   * @param eatRadius Eat radius of the fish.
   * @param fullInterval Full to hungry interval.
   * @param hungerTimeout Hungry to dead interval if there's no food.
   * @param createdTime Time created Fish
   */
  Fish(int foodThres, double eatRadius, double fullInterval, double hungerTimeout,
      double createdTime) {
    this.foodThres = foodThres;
    this.eatRadius = eatRadius;
    this.fullInterval = fullInterval;
    this.hungerTimeout = hungerTimeout;
    this.lastEatTime = createdTime;
    this.lastRandomTime = createdTime;
    this.lastHungerTime = createdTime;
    this.foodEaten = 0;
    this.hungry = false;
    this.level = 1;
  }

  /*------------------------------------------*/
  /* ------------ Setter & Getter ----------- */
  /*------------------------------------------*/

  /**
   * Getter for lastEatTime.
   *
   * @return The last eating time
   */
  double getLastEatTime() {
    return lastEatTime;
  }

  /**
   * Setter for lastEatTime.
   *
   * @param lastEatTime The value of the new eating time
   */
  void setLastEatTime(double lastEatTime) {
    this.lastEatTime = lastEatTime;
  }

  /**
   * Getter for lastRandomTime.
   *
   * @return The value of last random time
   */
  double getLastRandomTime() {
    return lastRandomTime;
  }

  /**
   * Setter for lastRandomTime.
   *
   * @param lastRandomTime The value of the new random time
   */
  void setLastRandomTime(double lastRandomTime) {
    this.lastRandomTime = lastRandomTime;
  }

  /**
   * Getter for lastHungerTime.
   *
   * @return The value of last hunger time
   */
  double getLastHungerTime() {
    return lastHungerTime;
  }

  /**
   * Setter for lastHungerTime.
   *
   * @param lastHungerTime The value of the new hunger time
   */
  void setLastHungerTime(double lastHungerTime) {
    this.lastHungerTime = lastHungerTime;
  }

  /**
   * Getter for foodEaten.
   *
   * @return The number of food eaten
   */
  int getFoodEaten() {
    return foodEaten;
  }

  /**
   * Setter for foodEaten.
   *
   * @param foodEaten The new number of food eaten.
   */
  void setFoodEaten(int foodEaten) {
    this.foodEaten = foodEaten;
  }

  /**
   * Getter for level.
   *
   * @return The Fish's level
   */
  public int getLevel() {
    return level;
  }

  /**
   * Setter for level.
   *
   * @param level The new Fish level
   */
  void setLevel(int level) {
    this.level = level;
  }

  /**
   * Getter for foodThres constant.
   *
   * @return The value of food threshold to level up
   */
  int getFoodThres() {
    return foodThres;
  }

  /**
   * Getter for eatRadius constant.
   *
   * @return The value of eat radius of the fish
   */
  double getEatRadius() {
    return eatRadius;
  }

  /**
   * Getter for fullInterval.
   *
   * @return The interval time between the last eat time to next hunger time
   */
  public double getFullInterval() {
    return fullInterval;
  }

  /**
   * Getter for hungerTimeout.
   *
   * @return The interval time between last hunger time to death
   */
  public double getHungerTimeout() {
    return hungerTimeout;
  }

  /**
   * Getter for dirX.
   *
   * @return The x-axis direction of the Fish.
   */
  public double getXDir() {
    return dirX;
  }

  /**
   * Setter for dirX.
   *
   * @param dirX The new x-axis direction of the Fish
   */
  void setXDir(double dirX) {
    this.dirX = dirX;
  }

  /**
   * Getter for dirY.
   *
   * @return The y-axis direction of the Fish.
   */
  double getYDir() {
    return dirY;
  }

  /**
   * Setter for dirY.
   *
   * @param dirY The new y-axis direction of the Fish
   */
  void setYDir(double dirY) {
    this.dirY = dirY;
  }

  /**
   * Getter for hungry.
   *
   * @return The hunger status of the Fish.
   */
  public boolean getHungry() {
    return hungry;
  }

  /**
   * Setter for hungru.
   *
   * @param hungry The new hunger state of the Fish
   */
  public void setHungry(boolean hungry) {

    this.hungry = hungry;
  }

  /*------------------------------------------*/
  /* ---------------- Methods --------------- */
  /*------------------------------------------*/

  /**
   * Eat object. Eat food if the food is in range
   */
  abstract void eat();

  /**
   * Drop coin. Drop coin following each subclasses rule
   */
  abstract void dropCoin();
}
