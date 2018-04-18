package itb.arkavquarium;

/**
 * <h1>Snail!</h1>
 * The Snail class implements Aquatic interface.
 *
 * @author  Abram Situmorang
 * @author  Faza Fahleraz
 * @author  Senapati Sang Diwangkara
 * @author  Yusuf Rahmat Pratama
 * @version 0.0
 * @since   2018-04-15
 */
public class Snail implements Aquatic {
    /*------------------------------------------*/
    /* -------------- Attributes -------------- */
    /*------------------------------------------*/

    private final double SNAIL_RADIUS;
    private Coin nearestCoin;
    private int holdCoinValue;
    private int xDir;

    /* ---------- Aquatic Attributes ---------- */
    private Aquarium aquarium;
    private double x;
    private double y;
    private double lastCurrTime;
    private double lastProgressTime;
    private final double MOVE_SPEED; /* Movement Speed per second */
    private State currState;
    private int progress;

    /*------------------------------------------*/
    /* ------------- Constructors ------------- */
    /*------------------------------------------*/

    /** A constructor.
     * Constructs a new Snail object.
     * @param aquarium The Aquarium the Snail is on.
     * */
    public Snail(Aquarium aquarium) {
        /* Aquatic attribute initialization */
        this.aquarium = aquarium;
        this.x = Constants.random(aquarium.getXMin(), aquarium.getXMax());
        this.y = Constants.random(aquarium.getYMin(), aquarium.getYMax());
        this.lastCurrTime = aquarium.getCurrTime();
        this.lastProgressTime = aquarium.getCurrTime();
        this.MOVE_SPEED = Constants.GUPPY_MOVE_SPEED;
        this.currState = State.movingRight;
        this.progress = 0;

        this.SNAIL_RADIUS = Constants.SNAIL_EAT_RADIUS;

        this.nearestCoin = null;
        this.holdCoinValue = 0;
        this.xDir = 0;
        this.setLastProgressTime(aquarium.getCurrTime());
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

    /** Getter for number of coins Snail holds.
     * @return The value of coin Snail holds.
     */
    public double getCoin() {
        return this.holdCoinValue;
    }

    /** Resetter for number of coins Snail holds
     */
    public double resetCoin() {
        return this.holdCoinValue = 0;
    }

    /*------------------------------------------*/
    /* ---------------- Methods --------------- */
    /*------------------------------------------*/

    private double getDistance(Aquatic a, Aquatic b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    private boolean isCoinOnTop() {
        return (Math.abs(this.getX() - nearestCoin.getX()) < this.SNAIL_RADIUS * 0.5);
    }

    private void pickCoin(Coin c) {
        holdCoinValue += c.getValue();
        this.getAquarium().deleteCoin(c);
        nearestCoin = null;
    }

    private void findNearestCoin() {
        /* TODO: Tunggu implement linkedlist */
    }

    private boolean nearestCoinInRange() {
        if (nearestCoin != null) {
            if (getDistance(this, nearestCoin) < this.SNAIL_RADIUS) {
                return true;
            }
        }
        return false;
    }

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
        double currentTime = this.getAquarium().getCurrTime();
        this.updateProgress();
        this.findNearestCoin();
        if (nearestCoinInRange()) {
            this.pickCoin(nearestCoin);
        } else {
            this.move();
        }
        this.setLastCurrTime(currentTime);
    }

    /**
     * Updates the object progress independently
     */
    @Override
    public void updateProgress() {
        if(this.getState() == State.stillRight || this.getState() == State.stillLeft) {
            this.setProgress(0);
        } else {
            double currentTime = this.getAquarium().getCurrTime();
            double progress_increment_time = (this.getState() == State.movingRight || this.getState() == State.movingLeft)
										? Constants.SNAIL_MOVE_PROGRESS_INCREMENT_TIME : Constants.SNAIL_TURN_PROGRESS_INCREMENT_TIME;
            if(currentTime - this.lastProgressTime > progress_increment_time) {
                this.setProgress(this.getProgress() + 1);
                if(this.getProgress() >= Constants.PROGRESS_PERIOD) {
                    if(this.getState() == State.turningRight) {
                        this.setProgress(0);
                        this.setState(State.movingRight);
                    } else if(this.getState() == State.turningLeft) {
                        this.setProgress(0);
                        this.setState(State.movingLeft);
                    } else {
                        this.setProgress(0);
                    }
                }
                this.setLastProgressTime(currentTime);
            }
        }
    }

    /**
     * Executing dead progress
     */
    @Override
    public void dead() {}
}
