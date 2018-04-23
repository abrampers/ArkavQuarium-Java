package itb.arkavquarium;

/**
 * <h1>State! The State enum represents the current condition of the object.</h1>
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
public enum State {
  /**
   * The object is moving to the left direction.
   */
  movingLeft,

  /**
   * The object is moving to the right direction.
   */
  movingRight,

  /**
   * The object is turning from heading right to heading left.
   */
  turningLeft,

  /**
   * The object is turning from heading left to heading right.
   */
  turningRight,

  /**
   * The object is dead while heading left.
   */
  deadLeft,

  /**
   * The object is dead while heading right.
   */
  deadRight,

  /**
   * The object is fading whether it's heading right or left.
   */
  fading,

  /**
   * The object is not moving and heading to the right.
   */
  stillRight,

  /**
   * The object is not moving and heading to the left.
   */
  stillLeft,

  /**
   * The object is eating and heading to the right.
   */
  eatingRight,

  /**
   * The object is eating and heading to the left.
   */
  eatingLeft
}
