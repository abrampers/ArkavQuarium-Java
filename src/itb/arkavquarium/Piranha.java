package itb.arkavquarium;

import java.util.Iterator;

/**
 * <h1>Piranha!</h1>
 * The Piranha class inherits Fish and implements Aquatic interface.
 *
 * @author  Abram Situmorang
 * @author  Faza Fahleraz
 * @author  Senapati Sang Diwangkara
 * @author  Yusuf Rahmat Pratama
 * @version 0.0
 * @since   2018-04-15
 */
public class Piranha extends Fish implements Aquatic {
    /*------------------------------------------*/
    /* -------------- Attributes -------------- */
    /*------------------------------------------*/

    private Guppy nearestGuppy;

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
     * Constructs a new Piranha object.
     * @param aquarium The Aquarium the Guppy is in.
     * */
    public Piranha(Aquarium aquarium) {
        super(
                Constants.PIRANHA_FOOD_THRES,
                Constants.PIRANHA_EAT_RADIUS,
                Constants.PIRANHA_FULL_INTERVAL,
                Constants.PIRANHA_HUNGER_INTERVAL,
                aquarium.getCurrTime()
        );

        /* Aquatic attribute initialization */
        this.aquarium = aquarium;
        this.x = Constants.random(aquarium.getXMin(), aquarium.getXMax());
        this.y = Constants.random(aquarium.getYMin(), aquarium.getYMax());
        this.lastCurrTime = aquarium.getCurrTime();
        this.lastProgressTime = aquarium.getCurrTime();
        this.MOVE_SPEED = Constants.GUPPY_MOVE_SPEED;
        this.currState = State.movingRight;
        this.progress = 0;
        /* Initialize random movement */
        double rad = Constants.random(0, 2 * Math.PI);
        this.setXDir(Math.cos(rad));
        this.setYDir(Math.sin(rad));
        State state = this.getXDir() >= 0 ? State.movingRight : State.movingLeft;
        this.setState(state);

        /* Guppy attribute initialization */
        this.nearestGuppy = null;
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

    private double distanceToGuppy(Guppy g) {
        double piranhaXPosition = this.getX();
        double piranhaYPosition = this.getY();
        double guppyXPosition = g.getX();
        double guppyYPosition = g.getY();

        return Math.sqrt(
                (piranhaXPosition - guppyXPosition) * (piranhaXPosition - guppyXPosition)
                        + (piranhaYPosition - guppyYPosition) * (piranhaYPosition - guppyYPosition)
        );
    }

    private void findNearestGuppy() {
        LinkedList<Guppy> contentGuppy = this.getAquarium().getContentGuppy();
        Guppy currentNearestGuppy = null;
        Iterator<Guppy> guppyItr = contentGuppy.iterator();
        while(guppyItr.hasNext()) {
            Guppy currentGuppy = guppyItr.next();
            if(currentNearestGuppy == null) {
                currentNearestGuppy = currentGuppy;
            } else if(distanceToGuppy(currentGuppy) < distanceToGuppy(currentNearestGuppy)) {
                currentNearestGuppy = currentGuppy;
            }
        }
        this.nearestGuppy =  currentNearestGuppy;
    }

    private boolean nearestGuppyInRange() {
        if(nearestGuppy == null) {
            return false;
        } else if(distanceToGuppy(nearestGuppy) < this.getEatRadius()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Moves the object independently
     */
    @Override
    public void move() {
        double currentTime = this.getAquarium().getCurrTime();
        if(this.getState() != State.turningRight && this.getState() != State.turningLeft) {
            if(nearestGuppy != null && this.getHungry()) {
                double xDirection = nearestGuppy.getX() - this.getX();
                double yDirection = nearestGuppy.getY() - this.getY();
                double distance = distanceToGuppy(nearestGuppy);

                /* Check if this need to change */
                double dx = (xDirection / distance) * this.getMoveSpeed() * ((currentTime - this.getLastCurrTime()));
                double dy = (yDirection / distance) * this.getMoveSpeed() * ((currentTime - this.getLastCurrTime()));

                if(xDirection >= 0 && this.getXDir() < 0) {
                    this.setState(State.turningRight);
                    this.setLastProgressTime(currentTime);
                    this.setProgress(0);
                }

                if(xDirection < 0 && this.getXDir() >= 0) {
                    this.setState(State.turningLeft);
                    this.setLastProgressTime(currentTime);
                    this.setProgress(0);
                }

                this.setX(this.getX() + dx);
                this.setY(this.getY() + dy);
                this.setXDir(xDirection / distance);
                this.setYDir(yDirection / distance);
            } else {
                /* Randomize move direction after some interval */
                if(currentTime - this.getLastRandomTime() > Constants.RANDOM_MOVE_INTERVAL) {
                    this.setLastRandomTime(currentTime);
                    double rad = Constants.random(0.1, 1.9 * Math.PI);

                    double xDirection = Math.cos(rad);
                    if(xDirection >= 0 && this.getXDir() < 0) {
                        this.setState(State.turningRight);
                        this.setLastProgressTime(currentTime);
                        this.setProgress(0);
                    }

                    if(xDirection < 0 && this.getXDir() >= 0) {
                        this.setState(State.turningLeft);
                        this.setLastProgressTime(currentTime);
                        this.setProgress(0);
                    }

                    this.setXDir(xDirection);
                    this.setYDir(Math.sin(rad));
                }


                /* Continue movement */
                double dx = this.getXDir() * this.getMoveSpeed() * (currentTime - this.getLastCurrTime());
                double dy = this.getYDir() * this.getMoveSpeed() * (currentTime - this.getLastCurrTime());


                if (getX() + dx >= getAquarium().getXMax() && this.getXDir() > 0.0) {
                    this.setXDir(this.getXDir() * -1.0);
                    this.setState(State.turningLeft);
                    this.setLastProgressTime(currentTime);
                    this.setProgress(0);
                } else if (getX() + dx <= getAquarium().getXMin() && this.getXDir() < 0.0) {
                    this.setXDir(this.getXDir() * -1.0);
                    this.setState(State.turningRight);
                    this.setLastProgressTime(currentTime);
                    this.setProgress(0);
                } else {
                    this.setX(this.getX() + dx);
                }


                if (getY() + dx >= getAquarium().getYMax() && this.getYDir() > 0.0) {
                    this.setYDir(this.getYDir() * -1.0);
                } else if (getY() + dy <= getAquarium().getYMin() && this.getYDir() < 0.0) {
                    this.setYDir(this.getYDir() * -1.0);
                } else {
                    this.setY(this.getY() + dy);
                }
            }
        }
    }

    /**
     * Updates the object position and eating mechanism independently
     */
    @Override
    public void updateState() {
        double currentTime = this.getAquarium().getCurrTime();
        if(this.getState() == State.deadLeft || this.getState() == State.deadRight || (this.getHungry() && (currentTime - this.getLastHungerTime()) > this.getHungerTimeout())) {
            /* Dead guppy */
            this.dead();
        } else {
            this.updateProgress();
            this.dropCoin();
            this.findNearestGuppy();
            this.eat();
            this.findNearestGuppy();
            this.move();
            this.setLastCurrTime(currentTime);
        }
    }

    /**
     * Updates the object progress independently
     */
    @Override
    public void updateProgress() {
        double currentTime = this.getAquarium().getCurrTime();
        double progress_increment_time;
        switch(this.getState()) {
            case movingRight :
                progress_increment_time = Constants.GUPPY_MOVE_PROGRESS_INCREMENT_TIME;
                break;
            case movingLeft :
                progress_increment_time = Constants.GUPPY_MOVE_PROGRESS_INCREMENT_TIME;
                break;
            case turningRight :
                progress_increment_time = Constants.GUPPY_TURN_PROGRESS_INCREMENT_TIME;
                break;
            case turningLeft :
                progress_increment_time = Constants.GUPPY_TURN_PROGRESS_INCREMENT_TIME;
                break;
            case eatingRight :
                progress_increment_time = Constants.GUPPY_EAT_PROGRESS_INCREMENT_TIME;
                break;
            case eatingLeft :
                progress_increment_time = Constants.GUPPY_EAT_PROGRESS_INCREMENT_TIME;
                break;
            default:
                progress_increment_time = Constants.GUPPY_MOVE_PROGRESS_INCREMENT_TIME;
        }

        if(this.getHungry() && (this.getState() != State.eatingRight && this.getState() != State.eatingLeft) && (this.nearestGuppy != null) && distanceToGuppy(this.nearestGuppy) < (2 * Constants.GUPPY_EAT_RADIUS)) {
            if(this.getState() == State.movingRight) {
                this.setState(State.eatingRight);
            } else {
                this.setState(State.eatingLeft);
            }
            this.setProgress(0);
            this.setLastProgressTime(currentTime);
            return;
        }

        if(currentTime - this.lastProgressTime > progress_increment_time) {
            if(this.getProgress() < Constants.PROGRESS_PERIOD - 1) {
                this.setProgress(this.getProgress() + 1);
            } else if(this.getState() == State.turningRight) {
                this.setState(State.movingRight);
                this.setProgress(0);
            } else if(this.getState() == State.turningLeft) {
                this.setState(State.movingLeft);
                this.setProgress(0);
            } else if(this.getXDir() >= 0) {
                this.setState(State.movingRight);
                this.setProgress(0);
            } else if(this.getXDir() < 0) {
                this.setState(State.movingLeft);
                this.setProgress(0);
            }
            this.setLastProgressTime(currentTime);
        }
    }

    /**
     * Executing dead progress
     */
    @Override
    public void dead() {
        if(this.getState() == State.movingRight || (this.getState() == State.turningRight && this.getProgress() >= 5) || (this.getState() == State.turningLeft && this.getProgress() < 5)) {
            this.setState(State.deadRight);
        } else if(this.getState() == State.movingLeft || (this.getState() == State.turningLeft && this.getProgress() >= 5) || (this.getState() == State.turningRight && this.getProgress() < 5)) {
            this.setState(State.deadLeft);
        }
        double current_time = this.getAquarium().getCurrTime();
        if(current_time - this.lastProgressTime > Constants.GUPPY_DEAD_PROGRESS_INCREMENT_TIME) {
            this.setProgress(this.getProgress() + 1);
            this.setLastProgressTime(current_time);
            if(this.getProgress() >= Constants.PROGRESS_PERIOD) {
                this.getAquarium().deletePiranha(this);
            }
        }
    }

    /**
     * Drop coin.
     * Implements abstract method dropCoin() from Fish.
     * Drop coin every c time.
     */
    @Override
    void dropCoin() {
        this.getAquarium().createCoin(this.getX(), this.getY(), 100 * (nearestGuppy.getLevel() + 1));
    }

    /**
     * Eat object.
     * Implements abstract method eat() from Fish.
     * Eat food if the food is in range
     */
    @Override
    void eat() {
        double currentTime = this.getAquarium().getCurrTime();
        if(!this.getHungry() && (currentTime - this.getLastEatTime() > this.getFullInterval())) {
            /* Change guppy hunger state */
            this.setHungry(true);
            this.setLastHungerTime(currentTime);
        }

        if(this.getHungry() && nearestGuppyInRange()) {
            this.getAquarium().deleteGuppy(nearestGuppy);
            nearestGuppy = null;
            this.setHungry(false);
            this.setLastEatTime(currentTime);
            this.setFoodEaten(this.getFoodEaten() + 1);

            if(this.getLevel() < Constants.MAX_LEVEL && this.getFoodEaten() > this.getFoodThres()) {
                this.setLevel(this.getLevel() + 1);
                this.setFoodEaten(0);
            }
        }
    }
}
