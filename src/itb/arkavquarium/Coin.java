package itb.arkavquarium;

/**
 * <h1>Coin!</h1> The Coin class implements Aquatic interface.
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
public class Coin implements Aquatic {
  /*------------------------------------------*/
  /* -------------- Attributes -------------- */
  /*------------------------------------------*/

  private final int value;
  private final double moveSpeed; /* Movement Speed per second */
  private double lastBottomTime;
  /* ---------- Aquatic Attributes ---------- */
  private final Aquarium aquarium;
  private double xpos;
  private double ypos;
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
   * @param xpos X-axis position of the Pellet.
   * @param ypos Y-axis position of the Pellet.
   * @param aquarium The Aquarium the Pellet is on.
   */
  public Coin(double xpos, double ypos, int value, Aquarium aquarium) {
    this.xpos = xpos;
    this.ypos = ypos;
    this.lastCurrTime = aquarium.getCurrTime();
    this.aquarium = aquarium;
    this.currState = State.movingRight;
    this.lastProgressTime = 0;
    this.progress = 0;
    this.moveSpeed = Constants.COIN_MOVE_SPEED;

    this.lastBottomTime = 0;
    this.setState(State.movingRight);

    this.value = value;
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
    return this.xpos;
  }

  /**
   * Setter for x.
   *
   * @param xpos The new x-axis position of the Aquatic.
   */
  @Override
  public void setX(double xpos) {
    this.xpos = xpos;
  }

  /**
   * Getter for y.
   *
   * @return y-axis position of the Aquatic.
   */
  @Override
  public double getY() {
    return this.ypos;
  }

  /**
   * Setter for y.
   *
   * @param ypos The new y-axis position of the Aquatic.
   */
  @Override
  public void setY(double ypos) {
    this.ypos = ypos;
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
   * Getter for Value.
   *
   * @return The Coin's value.
   */
  public int getValue() {
    return value;
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
  public boolean isInside() {
    return xpos > this.aquarium.getXMin()
            && ypos > this.aquarium.getYMin()
            && ypos < this.aquarium.getYMax()
            && xpos < this.aquarium.getXMax();
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
        lastBottomTime = currentTime;
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
    double currentTime = this.getAquarium().getCurrTime();
    this.updateProgress();
    this.move();
    if (this.getY() == this.getAquarium().getYMax()) {
      if (currentTime - lastBottomTime > Constants.COIN_DELETION_INTERVAL) {
        this.dead();
      }
    }
    this.setLastCurrTime(currentTime);
  }

  /**
   * Updates the object progress independently.
   */
  @Override
  public void updateProgress() {
    double currentTime = this.getAquarium().getCurrTime();
    if (this.getY() == this.getAquarium().getYMax()) {
      this.setState(State.fading);
      this.setProgress(0);
    }
    if (currentTime - this.lastProgressTime > Constants.COIN_PROGRESS_INCREMENT_TIME) {
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
    this.getAquarium().deleteCoin(this);
  }
}
