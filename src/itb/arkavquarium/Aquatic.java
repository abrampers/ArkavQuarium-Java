package itb.arkavquarium;

/**
 * <h1>Aquatic!</h1> The Aquatic interface defines all objects in Aquarium. This contains all the
 * needed information of the object in the Aquarium to be drawn.
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
public interface Aquatic {

  /**
   * Getter for Aquarium.
   *
   * @return The Aquarium the Aquatic is in.
   */
  Aquarium getAquarium();

  /**
   * Getter for Move Speed.
   *
   * @return The Aquatic's move speed.
   */
  double getMoveSpeed();

  /**
   * Getter for x.
   *
   * @return The x-axis position of the Aquatic.
   */
  double getX();

  /**
   * Setter for x.
   *
   * @param x The new x-axis position of the Aquatic.
   */
  void setX(double x);

  /**
   * Getter for y.
   *
   * @return y-axis position of the Aquatic.
   */
  double getY();

  /**
   * Setter for y.
   *
   * @param y The new y-axis position of the Aquatic.
   */
  void setY(double y);

  /**
   * Getter for lastCurrTime.
   *
   * @return The last update time of the Aquatic.
   */
  double getLastCurrTime();

  /**
   * Setter for last current time.
   *
   * @param time The last update time of the Aquatic.
   */
  void setLastCurrTime(double time);

  /**
   * Getter for Aquatic's State.
   *
   * @return The Aquatic's current State.
   */
  State getState();

  /**
   * Setter for State.
   *
   * @param state The new state of the Aquatic.
   */
  void setState(State state);

  /**
   * Getter for Progress.
   *
   * @return The Aquatic's current State Progress.
   */
  int getProgress();

  /**
   * Setter for progress.
   *
   * @param progress The new progress of the Aquatic.
   */
  void setProgress(int progress);

  /**
   * Setter for last progress time.
   *
   * @param time The new last update progress of the Aquatic.
   */
  void setLastProgressTime(double time);

  /**
   * Moves the object independently.
   */
  void move();

  /**
   * Updates the object position and eating mechanism independently.
   */
  void updateState();

  /**
   * Updates the object progress independently.
   */
  void updateProgress();

  /**
   * Executing dead progress.
   */
  void dead();
}
