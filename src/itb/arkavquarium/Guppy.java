package itb.arkavquarium;

import java.util.Iterator;

/**
 * <h1>Guppy! The Guppy class inherits Fish and implements Aquatic interface.</h1>
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
public class Guppy extends Fish implements Aquatic {
  /*------------------------------------------*/
  /* -------------- Attributes -------------- */
  /*------------------------------------------*/

  private final double moveSpeed; /* Movement Speed per second */
  /* ---------- Aquatic Attributes ---------- */
  private final Aquarium aquarium;
  private Pellet nearestPellet;
  private double lastDropCoin;
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
   * A constructor. Constructs a new Guppy object.
   *
   * @param aquarium The Aquarium the Guppy is in.
   */
  public Guppy(Aquarium aquarium) {
    super(
      Constants.GUPPY_FOOD_THRES,
      Constants.GUPPY_EAT_RADIUS,
      Constants.GUPPY_FULL_INTERVAL,
      Constants.GUPPY_HUNGER_INTERVAL,
      aquarium.getCurrTime()
    );

    /* Aquatic attribute initialization */
    this.aquarium = aquarium;
    this.abscissa = Constants.random(aquarium.getXMin(), aquarium.getXMax());
    this.ordinate = Constants.random(aquarium.getYMin(), aquarium.getYMax());
    this.lastCurrTime = aquarium.getCurrTime();
    this.lastProgressTime = aquarium.getCurrTime();
    this.moveSpeed = Constants.GUPPY_MOVE_SPEED;
    this.currState = State.movingRight;
    this.progress = 0;
    /* Initialize random movement */
    double rad = Constants.random(0, 2 * Math.PI);
    this.setXDir(Math.cos(rad));
    this.setYDir(Math.sin(rad));
    State state = this.getXDir() >= 0 ? State.movingRight : State.movingLeft;
    this.setState(state);

    /* Guppy attribute initialization */
    this.nearestPellet = null;
    this.lastDropCoin = aquarium.getCurrTime();
  }

  /*------------------------------------------*/
  /* ------------ Setter & Getter ----------- */
  /*------------------------------------------*/

  /**
   * Getter for Aquarium.
   *
   * @return The Aquarium the Aquatic is in.
   */
  @Override
  public Aquarium getAquarium() {
    return this.aquarium;
  }

  /**
   * Getter for Move Speed.
   *
   * @return The Aquatic's move speed.
   */
  @Override
  public double getMoveSpeed() {
    return this.moveSpeed;
  }

  /**
   * Getter for x.
   *
   * @return The x-axis position of the Aquatic.
   */
  @Override
  public double getX() {
    return this.abscissa;
  }

  /**
   * Setter for x.
   *
   * @param x The new x-axis position of the Aquatic.
   */
  @Override
  public void setX(double x) {
    this.abscissa = x;
  }

  /**
   * Getter for y.
   *
   * @return y-axis position of the Aquatic.
   */
  @Override
  public double getY() {
    return this.ordinate;
  }

  /**
   * Setter for y.
   *
   * @param y The new y-axis position of the Aquatic.
   */
  @Override
  public void setY(double y) {
    this.ordinate = y;
  }

  /**
   * Getter for lastCurrTime.
   *
   * @return The last update time of the Aquatic.
   */
  @Override
  public double getLastCurrTime() {
    return this.lastCurrTime;
  }

  /**
   * Setter for last current time.
   *
   * @param time The last update time of the Aquatic.
   */
  @Override
  public void setLastCurrTime(double time) {
    this.lastCurrTime = time;
  }

  /**
   * Getter for Aquatic's State.
   *
   * @return The Aquatic's current State.
   */
  @Override
  public State getState() {
    return this.currState;
  }

  /**
   * Setter for State.
   *
   * @param state The new state of the Aquatic.
   */
  @Override
  public void setState(State state) {
    this.currState = state;
  }

  /**
   * Getter for Progress.
   *
   * @return The Aquatic's current State Progress.
   */
  @Override
  public int getProgress() {
    return this.progress;
  }

  /**
   * Setter for progress.
   *
   * @param progress The new progress of the Aquatic.
   */
  @Override
  public void setProgress(int progress) {
    this.progress = progress;
  }

  /**
   * Setter for last progress time.
   *
   * @param time The new last update progress of the Aquatic.
   */
  @Override
  public void setLastProgressTime(double time) {
    this.lastProgressTime = time;
  }

  /*------------------------------------------*/
  /* ---------------- Methods --------------- */
  /*------------------------------------------*/

  private double distanceToPellet(Pellet p) {
    double guppyXPosition = this.getX();
    double guppyYPosition = this.getY();
    double pelletXPosition = p.getX();
    double pelletYPosition = p.getY();

    return Math.sqrt(
      (guppyXPosition - pelletXPosition) * (guppyXPosition - pelletXPosition)
        + (guppyYPosition - pelletYPosition) * (guppyYPosition - pelletYPosition)
    );
  }

  private void findNearestPellet() {
    LinkedList<Pellet> contentPellet = this.getAquarium().getContentPellet();
    Pellet currentNearestPellet = null;
    Iterator<Pellet> pelletItr = contentPellet.iterator();
    while (pelletItr.hasNext()) {
      Pellet currentPellet = pelletItr.next();
      if (currentNearestPellet == null) {
        currentNearestPellet = currentPellet;
      } else if (distanceToPellet(currentPellet) < distanceToPellet(currentNearestPellet)) {
        currentNearestPellet = currentPellet;
      }
    }
    this.nearestPellet = currentNearestPellet;
  }

  private boolean nearestPelletInRange() {
    return (nearestPellet != null) && (distanceToPellet(nearestPellet) < this.getEatRadius());
  }

  /**
   * Moves the object independently.
   */
  @Override
  public void move() {
    double currentTime = this.getAquarium().getCurrTime();
    if (this.getState() != State.turningRight && this.getState() != State.turningLeft) {
      if (nearestPellet != null && this.getHungry()) {
        double directionX = nearestPellet.getX() - this.getX();
        double directionY = nearestPellet.getY() - this.getY();
        double distance = distanceToPellet(nearestPellet);

        /* Check if this need to change */
        double dx = (directionX / distance)
            * this.getMoveSpeed() * ((currentTime - this.getLastCurrTime()));

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

        double dy = (directionY / distance)
            * this.getMoveSpeed() * ((currentTime - this.getLastCurrTime()));

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
  @Override
  public void updateState() {
    double currentTime = this.getAquarium().getCurrTime();
    if (this.getState() == State.deadLeft
        || this.getState() == State.deadRight
        || (this.getHungry()
            && (currentTime - this.getLastHungerTime()) > this.getHungerTimeout())) {
      /* Dead guppy */
      this.dead();
    } else {
      this.updateProgress();
      this.dropCoin();
      this.findNearestPellet();
      this.eat();
      this.findNearestPellet();
      this.move();
      this.setLastCurrTime(currentTime);
    }
  }

  /**
   * Updates the object progress independently.
   */
  @Override
  public void updateProgress() {
    double currentTime = this.getAquarium().getCurrTime();
    double progressIncrementTime;
    switch (this.getState()) {
      case movingRight:
        progressIncrementTime = Constants.GUPPY_MOVE_PROGRESS_INCREMENT_TIME;
        break;
      case movingLeft:
        progressIncrementTime = Constants.GUPPY_MOVE_PROGRESS_INCREMENT_TIME;
        break;
      case turningRight:
        progressIncrementTime = Constants.GUPPY_TURN_PROGRESS_INCREMENT_TIME;
        break;
      case turningLeft:
        progressIncrementTime = Constants.GUPPY_TURN_PROGRESS_INCREMENT_TIME;
        break;
      case eatingRight:
        progressIncrementTime = Constants.GUPPY_EAT_PROGRESS_INCREMENT_TIME;
        break;
      case eatingLeft:
        progressIncrementTime = Constants.GUPPY_EAT_PROGRESS_INCREMENT_TIME;
        break;
      default:
        progressIncrementTime = Constants.GUPPY_MOVE_PROGRESS_INCREMENT_TIME;
    }

    if (this.getHungry()
        && (this.getState() != State.eatingRight
        && this.getState() != State.eatingLeft)
        && (this.nearestPellet != null)
        && distanceToPellet(this.nearestPellet) < (2 * Constants.GUPPY_EAT_RADIUS)) {
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
  @Override
  public void dead() {
    if (this.getState() == State.movingRight
        || (this.getState() == State.turningRight && this.getProgress() >= 5)
        || (this.getState() == State.turningLeft && this.getProgress() < 5)) {
      this.setState(State.deadRight);
    } else if (this.getState() == State.movingLeft
        || (this.getState() == State.turningLeft && this.getProgress() >= 5)
        || (this.getState() == State.turningRight && this.getProgress() < 5)) {
      this.setState(State.deadLeft);
    }
    double currentTime = this.getAquarium().getCurrTime();
    if (currentTime - this.lastProgressTime > Constants.GUPPY_DEAD_PROGRESS_INCREMENT_TIME) {
      this.setProgress(this.getProgress() + 1);
      this.setLastProgressTime(currentTime);
      if (this.getProgress() >= Constants.PROGRESS_PERIOD) {
        this.getAquarium().deleteGuppy(this);
      }
    }
  }

  /**
   * Drop coin. Implements abstract method dropCoin() from Fish. Drop coin every c time.
   */
  @Override
  void dropCoin() {
    double currentTime = this.getAquarium().getCurrTime();
    if (currentTime - this.lastDropCoin > Constants.GUPPY_COIN_INTERVAL) {
      this.getAquarium().createCoin(this.getX(), this.getY(),
          (int) (this.getLevel() * Constants.GUPPY_COIN_MULTIPLIER));
      this.lastDropCoin = currentTime;
    }
  }

  /**
   * Eat object. Implements abstract method eat() from Fish. Eat food if the food is in range
   */
  @Override
  void eat() {
    double currentTime = this.getAquarium().getCurrTime();
    if (!this.getHungry() && (currentTime - this.getLastEatTime() > this.getFullInterval())) {
      /* Change guppy hunger state */
      this.setHungry(true);
      this.setLastHungerTime(currentTime);
    }

    if (this.getHungry() && nearestPelletInRange()) {
      this.getAquarium().deletePellet(nearestPellet);
      nearestPellet = null;
      this.setHungry(false);
      this.setLastEatTime(currentTime);
      this.setFoodEaten(this.getFoodEaten() + 1);

      if (this.getLevel() < Constants.MAX_LEVEL
          && this.getFoodEaten() > this.getFoodThres()) {
        this.setLevel(this.getLevel() + 1);
        this.setFoodEaten(0);
      }
    }
  }
}
