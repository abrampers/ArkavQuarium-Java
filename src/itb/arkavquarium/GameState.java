package itb.arkavquarium;

/**
 * <h1>GameState! The GameState enum represents the current state of the game.</h1>
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
public enum GameState {
  /**
   * The game state is not initialized yet, which means the controller should show the main menu.
   */
  uninitialized,

  /**
   * The game state is initialized and the game is currently running.
   */
  running,

  /**
   * The game is won, which means the controller should show the win menu.
   */
  won,

  /**
   * The game is lost, which means the controller should show the lose menu.
   */
  lost
}
