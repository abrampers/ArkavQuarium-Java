package itb.arkavquarium;

/**
 * <h1>Pellet! The Pellet class implements Aquatic interface.</h1>
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
public class Pellet implements Aquatic {
  /*------------------------------------------*/
  /* -------------- Attributes -------------- */
  /*------------------------------------------*/

  private final double moveSpeed; /* Movement Speed per second */
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
   * A constructor. Constructs a new Fish object.
   *
   * @param x X-axis position of the Pellet.
   * @param y Y-axis position of the Pellet.
   * @param aquarium The Aquarium the Pellet is on.
   */
  public Pellet(double x, double y, Aquarium aquarium) {
    this.abscissa = x;
    this.ordinate = y;
    this.lastCurrTime = aquarium.getCurrTime();
    this.aquarium = aquarium;
    this.currState = State.movingRight;
    this.lastProgressTime = 0;
    this.progress = 0;
    this.moveSpeed = Constants.PELLET_SPEED;
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
   * Setter for x.
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

  /*------------------------------------------*/
  /* ---------------- Methods --------------- */
  /*------------------------------------------*/

  /**
   * Check whether the object is inside the Aquarium.
   *
   * @return True if the object is inside the Aquarium.
   */
  private boolean isInside() {
    return abscissa > this.aquarium.getXMin()
        && ordinate > this.aquarium.getYMin()
        && ordinate < this.aquarium.getYMax()
        && abscissa < this.aquarium.getXMax();
  }

  /**
   * Moves the object independently.
   */
  @Override
  public void move() {
    double currentTime = this.getAquarium().getCurrTime();
    double dy = this.getMoveSpeed() * ((currentTime - this.getLastCurrTime()));
    if (this.isInside()) {
      if (this.getY() + dy > this.getAquarium().getYMax()) {
        this.setY(this.getAquarium().getYMax());
      } else {
        this.setY(this.getY() + dy);
      }
    }
  }

  /**
   * Updates the object position and eating mechanism independently.
   */
  @Override
  public void updateState() {
    this.updateProgress();
    this.move();
    if (this.getY() >= this.getAquarium().getYMax()) {
      this.dead();
    }
  }

  /**
   * Updates the object progress independently.
   */
  @Override
  public void updateProgress() {
    double currentTime = this.getAquarium().getCurrTime();
    if (currentTime - this.lastProgressTime > Constants.PELLET_MOVE_PROGRESS_INCREMENT_TIME) {
      if (this.getProgress() < Constants.PROGRESS_PERIOD - 1) {
        this.setProgress(this.getProgress() + 1);
      } else {
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
    this.getAquarium().deletePellet(this);
  }
}
