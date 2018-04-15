package itb.arkavquarium;

/**
 * <h1>Guppy!</h1>
 * The Guppy class inherits Fish and implements Aquatic interface.
 *
 * @author  Abram Situmorang
 * @author  Faza Fahleraz
 * @author  Senapati Sang Diwangkara
 * @author  Yusuf Rahmat Pratama
 * @version 0.0
 * @since   2018-04-15
 */
public class Guppy extends Fish implements Aquatic {
    /*------------------------------------------*/
    /* -------------- Attributes -------------- */
    /*------------------------------------------*/

    private Aquarium aquarium;
    private double x, y;
    private double lastCurrTime;
    private double lastProgressTime;
    private final double MOVE_SPEED; /* Movement Speed per second */
    private State currState;
    private int progress;

    /*------------------------------------------*/
    /* ------------- Constructors ------------- */
    /*------------------------------------------*/
    public Guppy() {

    }

    /*------------------------------------------*/
    /* ------------ Setter & Getter ----------- */
    /*------------------------------------------*/

    /**
     * Getter for Aquarium
     *
     * @return The Aquarium the Aquatic is in
     */
    @Override
    public Aquarium getAquarium() {
        return this.aquarium;
    }

    /**
     * Getter for Move Speed
     *
     * @return The Aquatic's move speed
     */
    @Override
    public double getMoveSpeed() {
        return this.MOVE_SPEED;
    }

    /**
     * Getter for x
     *
     * @return The x-axis position of the Aquatic
     */
    @Override
    public double getX() {
        return this.x;
    }

    /**
     * Getter for y
     *
     * @return y-axis position of the Aquatic
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * Getter for lastCurrTime
     *
     * @return The last update time of the Aquatic
     */
    @Override
    public double getLastCurrTime() {
        return this.lastCurrTime;
    }

    /**
     * Getter for Aquatic's State
     *
     * @return The Aquatic's current State
     */
    @Override
    public State getState() {
        return this.currState;
    }

    /**
     * Getter for Progress
     *
     * @return The Aquatic's current State Progress
     */
    @Override
    public int getProgress() {
        return this.progress;
    }

    /**
     * Setter for x
     *
     * @param x The new x-axis position of the Aquatic
     */
    @Override
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Setter for y
     *
     * @param y The new y-axis position of the Aquatic
     */
    @Override
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Setter for last current time
     *
     * @param time The last update time of the Aquatic
     */
    @Override
    public void setLastCurrTime(double time) {
        this.lastCurrTime = time;
    }

    /**
     * Setter for State
     *
     * @param state The new state of the Aquatic
     */
    @Override
    public void setState(State state) {
        this.currState = state;
    }

    /**
     * Setter for progress
     *
     * @param progress The new progress of the Aquatic
     */
    @Override
    public void setProgress(int progress) {
        this.progress = progress;
    }

    /**
     * Setter for last progress time
     *
     * @param time The new last update progress of the Aquatic
     */
    @Override
    public void setLastProgressTime(double time) {
        this.lastProgressTime = time;
    }

    /*------------------------------------------*/
    /* ---------------- Methods --------------- */
    /*------------------------------------------*/

    /**
     * Moves the object independently
     */
    @Override
    public void move() {

    }

    /**
     * Updates the object position and eating mechanism independently
     */
    @Override
    public void updateState() {

    }

    /**
     * Updates the object progress independently
     */
    @Override
    public void updateProgress() {

    }

    /**
     * Executing dead progress
     */
    @Override
    public void dead() {

    }
}
