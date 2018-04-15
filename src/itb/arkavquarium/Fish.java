package itb.arkavquarium;

/**
 * <h1>Fish!</h1>
 * The abstract Fish class implements the behaviour of Fish.
 * This contains the information needed for the Fish to act.
 *
 * @author  Abram Situmorang
 * @author  Faza Fahleraz
 * @author  Senapati Sang Diwangkara
 * @author  Yusuf Rahmat Pratama
 * @version 0.0
 * @since   2018-04-15
 */
public abstract class Fish {
    /*------------------------------------------*/
    /* -------------- Attributes -------------- */
    /*------------------------------------------*/

    private double lastEatTime;
    private double lastRandomTime;
    private double lastHungerTime;
    private int foodEaten;
    private boolean hungry;
    private int level;
    private final int FOOD_THRES;
    private final double EAT_RADIUS;
    private final double FULL_INTERVAL;
    private final double HUNGER_TIMEOUT;
    private double xDir;
    private double yDir;

    /*------------------------------------------*/
    /* ------------- Constructors ------------- */
    /*------------------------------------------*/
    /** A constructor.
     * Constructs a new Fish object.
     * @param foodThres Number of food to level up.
     * @param eatRadius
     * @param fullInterval Full to hungry interval.
     * @param hungerTimeout Hungry to dead interval if there's no food.
     * @param createdTime
     * */
    public Fish(int foodThres, double eatRadius, double fullInterval, double hungerTimeout, double createdTime) {

    }

    /*------------------------------------------*/
    /* ------------ Setter & Getter ----------- */
    /*------------------------------------------*/

    /** Getter for lastEatTime.
     * @return The last eating time
     * */
    public double getLastEatTime() {
        return lastEatTime;
    }

    /** Setter for lastEatTime.
     * @param lastEatTime The value of the new eating time
     * */
    public void setLastEatTime(double lastEatTime) {
        this.lastEatTime = lastEatTime;
    }

    /** Getter for lastRandomTime.
     * @return The value of last random time
     * */
    public double getLastRandomTime() {
        return lastRandomTime;
    }

    /** Setter for lastRandomTime.
     * @param lastRandomTime The value of the new random time
     * */
    public void setLastRandomTime(double lastRandomTime) {
        this.lastRandomTime = lastRandomTime;
    }

    /** Getter for lastHungerTime.
     * @return The value of last hunger time
     * */
    public double getLastHungerTime() {
        return lastHungerTime;
    }

    /** Setter for lastHungerTime.
     * @param lastHungerTime The value of the new hunger time
     * */
    public void setLastHungerTime(double lastHungerTime) {
        this.lastHungerTime = lastHungerTime;
    }

    /** Getter for foodEaten.
     * @return The number of food eaten
     * */
    public int getFoodEaten() {
        return foodEaten;
    }

    /** Setter for foodEaten.
     * @param foodEaten The new number of food eaten.
     * */
    public void setFoodEaten(int foodEaten) {
        this.foodEaten = foodEaten;
    }

    /** Getter for hungry.
     * @return The value of hungry
     * */
    public boolean isHungry() {
        return hungry;
    }

    /** Setter for hungry.
     * @param hungry The value of the new hungry
     * */
    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    /** Getter for level.
     * @return The Fish's level
     * */
    public int getLevel() {
        return level;
    }

    /** Setter for level.
     * @param level The new Fish level
     * */
    public void setLevel(int level) {
        this.level = level;
    }

    /** Getter for FOOD_THRES constant.
     * @return The value of food threshold to level up
     * */
    public int getFoodThres() {
        return FOOD_THRES;
    }

    /** Getter for EAT_RADIUS constant.
     * @return The value of eat radius of the fish
     * */
    public double getEatRadius() {
        return EAT_RADIUS;
    }

    /** Getter for FULL_INTERVAL.
     * @return The interval time between the last eat time to next hunger time
     * */
    public double getFullInterval() {
        return FULL_INTERVAL;
    }

    /** Getter for HUNGER_TIMEOUT.
     * @return The interval time between last hunger time to death
     * */
    public double getHungerTimeout() {
        return HUNGER_TIMEOUT;
    }

    /** Getter for X_MIN.
     * @return The value of X_MIN
     * */
    public double getXDir() {
        return xDir;
    }

    /** Setter for currentTime.
     * @param currTime  The value of the new current time
     * */
    public void setXDir(double xDir) {
        this.xDir = xDir;
    }

    /** Getter for X_MIN.
     * @return The value of X_MIN
     * */
    public double getYDir() {
        return yDir;
    }

    /** Setter for currentTime.
     * @param currTime  The value of the new current time
     * */
    public void setYDir(double yDir) {
        this.yDir = yDir;
    }

    /*------------------------------------------*/
    /* ---------------- Methods --------------- */
    /*------------------------------------------*/
    /** Eat object.
     * Eat food if the food is in range
     * */
    abstract void eat();

    /** Drop coin.
     * Drop coin following each subclasses rule
     * */
    abstract void dropCoin();
}
