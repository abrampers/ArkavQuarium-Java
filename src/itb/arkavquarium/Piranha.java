package itb.arkavquarium;

import java.util.Iterator;

/**
 * <h1>Piranha! The Piranha class inherits Fish and implements Aquatic interface.</h1>
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
public class Piranha extends Fish implements Aquatic {
  /*------------------------------------------*/
  /* -------------- Attributes -------------- */
  /*------------------------------------------*/

  private final double moveSpeed; /* Movement Speed per second */
  private Guppy nearestGuppy;
  /* ---------- Aquatic Attributes ---------- */
  private final Aquarium aquarium;
  private double abscissa;
  private double ordinate;
  private double lastCurrTime;
  private double lastProgressTime;
  private State currState;
  private int progress;

  /*------------------------------------------*/
  /* ------------- Constructors ------------- */
  /*------------------------------------------*/

  /**
   * A constructor. Constructs a new Piranha object.
   *
   * @param aquarium The Aquarium the Guppy is in.
   */
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
    this.abscissa = Constants.random(aquarium.getXMin(), aquarium.getXMax());
    this.ordinate = Constants.random(aquarium.getYMin(), aquarium.getYMax());
    this.lastCurrTime = aquarium.getCurrTime();
    this.lastProgressTime = aquarium.getCurrTime();
    this.moveSpeed = Constants.PIRANHA_MOVE_SPEED;
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
   * Getter for Aquarium.
   *
   * @return The Aquarium the Aquatic is in.
   */
  public Aquarium getAquarium() {
    return this.aquarium;
  }

  /**
   * Getter for Move Speed.
   *
   * @return The Aquatic's move speed.
   */
  public double getMoveSpeed() {
    return this.moveSpeed;
  }

  /**
   * Getter for abscissa.
   *
   * @return The x-axis position of the Aquatic.
   */
  public double getX() {
    return this.abscissa;
  }

  /**
   * Setter for x.
   *
   * @param x The new x-axis position of the Aquatic.
   */
  public void setX(double x) {
    this.abscissa = x;
  }

  /**
   * Getter for ordinate.
   *
   * @return y-axis position of the Aquatic.
   */
  public double getY() {
    return this.ordinate;
  }

  /**
   * Setter for ordinate.
   *
   * @param y The new y-axis position of the Aquatic.
   */
  public void setY(double y) {
    this.ordinate = y;
  }

  /**
   * Getter for lastCurrTime.
   *
   * @return The last update time of the Aquatic.
   */
  public double getLastCurrTime() {
    return this.lastCurrTime;
  }

  /**
   * Setter for last current time.
   *
   * @param time The last update time of the Aquatic.
   */
  public void setLastCurrTime(double time) {
    this.lastCurrTime = time;
  }

  /**
   * Getter for Aquatic's State.
   *
   * @return The Aquatic's current State.
   */
  public State getState() {
    return this.currState;
  }

  /**
   * Setter for State.
   *
   * @param state The new state of the Aquatic.
   */
  public void setState(State state) {
    this.currState = state;
  }

  /**
   * Getter for Progress.
   *
   * @return The Aquatic's current State Progress.
   */
  public int getProgress() {
    return this.progress;
  }

  /**
   * Setter for progress.
   *
   * @param progress The new progress of the Aquatic.
   */
  public void setProgress(int progress) {
    this.progress = progress;
  }

  /**
   * Setter for last progress time.
   *
   * @param time The new last update progress of the Aquatic.
   */
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
    while (guppyItr.hasNext()) {
      Guppy currentGuppy = guppyItr.next();
      if (currentNearestGuppy == null) {
        currentNearestGuppy = currentGuppy;
      } else if (distanceToGuppy(currentGuppy) < distanceToGuppy(currentNearestGuppy)) {
        currentNearestGuppy = currentGuppy;
      }
    }
    this.nearestGuppy = currentNearestGuppy;
  }

  private boolean nearestGuppyInRange() {
    return nearestGuppy != null && distanceToGuppy(nearestGuppy) < this.getEatRadius();
  }

  /**
   * Moves the object independently.
   */
  public void move() {
    double currentTime = this.getAquarium().getCurrTime();
    if (this.getState() != State.turningRight && this.getState() != State.turningLeft) {
      if (nearestGuppy != null && this.getHungry()) {
        double directionX = nearestGuppy.getX() - this.getX();
        double directionY = nearestGuppy.getY() - this.getY();
        double distance = distanceToGuppy(nearestGuppy);

        /* Check if this need to change */
        double dx = (directionX / distance) * this.getMoveSpeed() * ((currentTime - this
            .getLastCurrTime()));

        if (directionX >= 0 && this.getXDir() < 0) {
          this.setState(State.turningRight);
          this.setLastProgressTime(currentTime);
          this.setProgress(0);
        }

        if (directionX < 0 && this.getXDir() >= 0) {
          this.setState(State.turningLeft);
          this.setLastProgressTime(currentTime);
          this.setProgress(0);
        }

        double dy = (directionY / distance) * this.getMoveSpeed() * ((currentTime - this
            .getLastCurrTime()));

        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
        this.setXDir(directionX / distance);
        this.setYDir(directionY / distance);
      } else {
        /* Randomize move direction after some interval */
        if (currentTime - this.getLastRandomTime() > Constants.RANDOM_MOVE_INTERVAL) {
          this.setLastRandomTime(currentTime);
          double rad = Constants.random(0.1, 1.9 * Math.PI);

          double directionX = Math.cos(rad);
          if (directionX >= 0 && this.getXDir() < 0) {
            this.setState(State.turningRight);
            this.setLastProgressTime(currentTime);
            this.setProgress(0);
          }

          if (directionX < 0 && this.getXDir() >= 0) {
            this.setState(State.turningLeft);
            this.setLastProgressTime(currentTime);
            this.setProgress(0);
          }

          this.setXDir(directionX);
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
   * Updates the object position and eating mechanism independently.
   */
  public void updateState() {
    double currentTime = this.getAquarium().getCurrTime();
    if (this.getState() == State.deadLeft || this.getState() == State.deadRight || (this.getHungry()
        && (currentTime - this.getLastHungerTime()) >= this.getHungerTimeout())) {
      /* Dead piranha */
      this.dead();
    } else {
      this.updateProgress();
      this.findNearestGuppy();
      this.eat();
      this.findNearestGuppy();
      this.move();
      this.setLastCurrTime(currentTime);
    }
  }

  /**
   * Updates the object progress independently.
   */
  public void updateProgress() {
    double currentTime = this.getAquarium().getCurrTime();
    double progressIncrementTime;
    switch (this.getState()) {
      case movingRight:
        progressIncrementTime = Constants.PIRANHA_MOVE_PROGRESS_INCREMENT_TIME;
        break;
      case movingLeft:
        progressIncrementTime = Constants.PIRANHA_MOVE_PROGRESS_INCREMENT_TIME;
        break;
      case turningRight:
        progressIncrementTime = Constants.PIRANHA_TURN_PROGRESS_INCREMENT_TIME;
        break;
      case turningLeft:
        progressIncrementTime = Constants.PIRANHA_TURN_PROGRESS_INCREMENT_TIME;
        break;
      case eatingRight:
        progressIncrementTime = Constants.PIRANHA_EAT_PROGRESS_INCREMENT_TIME;
        break;
      case eatingLeft:
        progressIncrementTime = Constants.PIRANHA_EAT_PROGRESS_INCREMENT_TIME;
        break;
      default:
        progressIncrementTime = Constants.PIRANHA_MOVE_PROGRESS_INCREMENT_TIME;
    }

    if (this.getHungry()
        && (this.getState() != State.eatingRight && this.getState() != State.eatingLeft)
        && (this.nearestGuppy != null)
        && distanceToGuppy(this.nearestGuppy) < (2 * Constants.PIRANHA_EAT_RADIUS)) {
      if (this.getState() == State.movingRight) {
        this.setState(State.eatingRight);
      } else {
        this.setState(State.eatingLeft);
      }
      this.setProgress(0);
      this.setLastProgressTime(currentTime);
      return;
    }

    if (currentTime - this.lastProgressTime > progressIncrementTime) {
      if (this.getProgress() < Constants.PROGRESS_PERIOD - 1) {
        this.setProgress(this.getProgress() + 1);
      } else if (this.getState() == State.turningRight) {
        this.setState(State.movingRight);
        this.setProgress(0);
      } else if (this.getState() == State.turningLeft) {
        this.setState(State.movingLeft);
        this.setProgress(0);
      } else if (this.getXDir() >= 0) {
        this.setState(State.movingRight);
        this.setProgress(0);
      } else if (this.getXDir() < 0) {
        this.setState(State.movingLeft);
        this.setProgress(0);
      }
      this.setLastProgressTime(currentTime);
    }
  }

  /**
   * Executing dead progress.
   */
  public void dead() {
    if (this.getState() == State.movingRight || (this.getState() == State.turningRight
        && this.getProgress() >= 5) || (this.getState() == State.turningLeft
        && this.getProgress() < 5)) {
      this.setState(State.deadRight);
    } else if (this.getState() == State.movingLeft || (this.getState() == State.turningLeft
        && this.getProgress() >= 5) || (this.getState() == State.turningRight
        && this.getProgress() < 5)) {
      this.setState(State.deadLeft);
    }
    double currentTime = this.getAquarium().getCurrTime();
    if (currentTime - this.lastProgressTime > Constants.PIRANHA_DEAD_PROGRESS_INCREMENT_TIME) {
      this.setProgress(this.getProgress() + 1);
      this.setLastProgressTime(currentTime);
      if (this.getProgress() >= Constants.PROGRESS_PERIOD) {
        this.getAquarium().deletePiranha(this);
      }
    }
  }

  /**
   * Drop coin. Implements abstract method dropCoin() from Fish. Drop coin every c time.
   */
  void dropCoin() {
    this.getAquarium().createCoin(this.getX(), this.getY(), 100 * (nearestGuppy.getLevel() + 1));
  }

  /**
   * Eat object. Implements abstract method eat() from Fish. Eat food if the food is in range.
   */
  void eat() {
    double currentTime = this.getAquarium().getCurrTime();
    if (!this.getHungry() && (currentTime - this.getLastEatTime() > this.getFullInterval())) {
      /* Change guppy hunger state */
      this.setHungry(true);
      this.setLastHungerTime(currentTime);
    }

    if (this.getHungry() && nearestGuppyInRange()) {
      this.dropCoin();
      this.getAquarium().deleteGuppy(nearestGuppy);
      nearestGuppy = null;
      this.setHungry(false);
      this.setLastEatTime(currentTime);
      this.setFoodEaten(this.getFoodEaten() + 1);

      if (this.getLevel() < Constants.MAX_LEVEL && this.getFoodEaten() > this.getFoodThres()) {
        this.setLevel(this.getLevel() + 1);
        this.setFoodEaten(0);
      }
    }
  }
}
