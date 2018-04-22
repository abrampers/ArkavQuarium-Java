package itb.arkavquarium;

import java.util.Iterator;

/**
 * <h1>Snail!</h1> The Snail class implements Aquatic interface.
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
public class Snail implements Aquatic {
  /*------------------------------------------*/
  /* -------------- Attributes -------------- */
  /*------------------------------------------*/
  // TODO: Benerin style error
  private final double snailRadius;
  private final double moveSpeed; /* Movement Speed per second */
  private Coin nearestCoin;
  private int holdCoinValue;
  private int dirX;
  /* ---------- Aquatic Attributes ---------- */
  private Aquarium aquarium;
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
   * A constructor. Constructs a new Snail object.
   *
   * @param aquarium The Aquarium the Snail is on.
   */
  public Snail(Aquarium aquarium) {
    /* Aquatic attribute initialization */
    this.aquarium = aquarium;
    this.abscissa = Constants.random(aquarium.getXMin(), aquarium.getXMax());
    this.ordinate = aquarium.getYMax();
    this.lastCurrTime = aquarium.getCurrTime();
    this.lastProgressTime = aquarium.getCurrTime();
    this.moveSpeed = Constants.SNAIL_SPEED;
    this.currState = State.movingRight;
    this.progress = 0;

    this.snailRadius = Constants.SNAIL_EAT_RADIUS;

    this.nearestCoin = null;
    this.holdCoinValue = 0;
    this.dirX = 0;
    this.setLastProgressTime(aquarium.getCurrTime());
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
   * Getter for abscissa.
   *
   * @return The x-axis position of the Aquatic.
   */
  @Override
  public double getX() {
    return this.abscissa;
  }

  /**
   * Setter for abscissa.
   *
   * @param x The new x-axis position of the Aquatic.
   */
  @Override
  public void setX(double x) {
    this.abscissa = x;
  }

  /**
   * Getter for ordinate.
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

  /**
   * Getter for dirX.
   *
   * @return The x-axis direction of the Fish.
   */
  public int getXDir() {
    return dirX;
  }

  /**
   * Setter for dirX.
   *
   * @param dirX The new x-axis direction of the Fish
   */
  public void setXDir(int dirX) {
    this.dirX = dirX;
  }

  /**
   * Getter for number of coins Snail holds.
   *
   * @return The value of coin Snail holds.
   */
  public double getCoin() {
    return this.holdCoinValue;
  }

  /**
   * Resetter for number of coins Snail holds.
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
    return (Math.abs(this.getX() - nearestCoin.getX()) < this.snailRadius * 0.5);
  }

  private void pickCoin(Coin c) {
    holdCoinValue += c.getValue();
    this.getAquarium().deleteCoin(c);
    nearestCoin = null;
  }

  private void findNearestCoin() {
    LinkedList<Coin> contentCoin = this.getAquarium().getContentCoin();
    Coin currentNearestCoin = null;
    Iterator<Coin> coinItr = contentCoin.iterator();
    while (coinItr.hasNext()) {
      Coin currentCoin = coinItr.next();
      if (currentNearestCoin == null) {
        currentNearestCoin = currentCoin;
      } else if (getDistance(this, currentCoin) < getDistance(this, currentNearestCoin)) {
        currentNearestCoin = currentCoin;
      }
    }
    this.nearestCoin = currentNearestCoin;
  }

  private boolean nearestCoinInRange() {
    if (nearestCoin != null) {
      return getDistance(this, nearestCoin) < this.snailRadius;
    }
    return false;
  }

  /**
   * Moves the object independently.
   */
  @Override
  public void move() {
    double currentTime = this.getAquarium().getCurrTime();
    if (this.getState() != State.turningRight && this.getState() != State.turningLeft) {
      if (nearestCoin != null) {
        if (!isCoinOnTop()) {
          double dx = this.getMoveSpeed() * ((currentTime - this.getLastCurrTime()));
          double directionX = this.getXDir();
          if (nearestCoin.getX() > this.getX()) {
            this.setXDir(1);
            this.setX(this.getX() + dx);
          } else {
            this.setXDir(-1);
            this.setX(this.getX() - dx);
          }

          if (directionX == 0) {
            if (this.getState() == State.stillLeft && getXDir() == 1) {
              this.setState(State.turningRight);
              this.setLastProgressTime(currentTime);
              this.setProgress(0);
            } else if (this.getState() == State.stillRight && this.getXDir() == -1) {
              this.setState(State.turningLeft);
              this.setLastProgressTime(currentTime);
              this.setProgress(0);
            } else if (this.getState() == State.stillLeft && this.getXDir() == -1) {
              this.setState(State.movingLeft);
              this.setLastProgressTime(currentTime);
              this.setProgress(0);
            } else if (this.getState() == State.stillRight && this.getXDir() == 1) {
              this.setState(State.movingRight);
              this.setLastProgressTime(currentTime);
              this.setProgress(0);
            }
          }

          if (directionX == 1 && this.getXDir() == -1) {
            this.setState(State.turningLeft);
            this.setLastProgressTime(currentTime);
            this.setProgress(0);
          }

          if (directionX == -1 && this.getXDir() == 1) {
            this.setState(State.turningRight);
            this.setLastProgressTime(currentTime);
            this.setProgress(0);
          }
        } else {
          if (this.getXDir() == 1) {
            this.setState(State.stillRight);
          } else {
            this.setState(State.stillLeft);
          }
          this.setXDir(0);
        }
      } else {
        if (this.getXDir() == 1) {
          this.setState(State.stillRight);
        } else {
          this.setState(State.stillLeft);
        }
        this.setXDir(0);
      }
    }
  }

  /**
   * Updates the object position and eating mechanism independently.
   */
  @Override
  public void updateState() {
    // double currentTime = this.getAquarium().getCurrTime();
    this.updateProgress();
    this.findNearestCoin();
    if (nearestCoinInRange()) {
      this.pickCoin(nearestCoin);
    } else {
      this.move();
    }
    this.setLastCurrTime(this.getAquarium().getCurrTime()); // TODO: Cek aman apa engga
  }

  /**
   * Updates the object progress independently.
   */
  @Override
  public void updateProgress() {
    if (this.getState() == State.stillRight || this.getState() == State.stillLeft) {
      this.setProgress(0);
    } else {
      double currentTime = this.getAquarium().getCurrTime();
      double progressIncrementTime =
          (this.getState() == State.movingRight || this.getState() == State.movingLeft)
              ? Constants.SNAIL_MOVE_PROGRESS_INCREMENT_TIME
              : Constants.SNAIL_TURN_PROGRESS_INCREMENT_TIME;
      if (currentTime - this.lastProgressTime > progressIncrementTime) {
        this.setProgress(this.getProgress() + 1);
        if (this.getProgress() >= Constants.PROGRESS_PERIOD) {
          if (this.getState() == State.turningRight) {
            this.setProgress(0);
            this.setState(State.movingRight);
          } else if (this.getState() == State.turningLeft) {
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
   * Executing dead progress.
   */
  @Override
  public void dead() {
  }
}
