package itb.arkavquarium;

/**
 * <h1>Constants!</h1> Class that contains a widely used constants in ArkavQuarium.
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
public class Constants {

  /* Game constants */
  public static final int GAME_INITIAL_COIN = 200;
  public static final int GAME_FRAME_RATE = 35;
  public static final int GAME_SCREEN_WITH = 1024;
  public static final int GAME_SCREEN_HEIGHT = 768;
  public static final int GAME_SCREEN_LEFT_PADDING = 20;
  public static final int GAME_SCREEN_RIGHT_PADDING = 20;
  public static final int GAME_SCREEN_TOP_PADDING = 170;
  public static final int GAME_SCREEN_BOTTOM_PADDING = 65;
  public static final int MAX_LEVEL = 3;
  public static final int EGG_PRICE = 2000;
  public static final double RANDOM_MOVE_INTERVAL = 2;    /* Ini juga detik ya brok */

  /* Coin's constant */
  public static final double COIN_MOVE_SPEED = 100;      /* TBD */
  public static final double COIN_DELETION_INTERVAL = 5;    /* TBD */
  public static final int COIN_CLICK_RADIUS = 20;

  /* Guppy constants */
  public static final int GUPPY_FOOD_THRES = 2;
  public static final int GUPPY_PRICE = 100;
  public static final double GUPPY_EAT_RADIUS = 20;
  public static final double GUPPY_FULL_INTERVAL = 15;    /* Ini detik ye bos */
  public static final double GUPPY_HUNGER_INTERVAL = 15;
  public static final double GUPPY_MOVE_SPEED = 60;      /* Pixels per second */
  public static final double GUPPY_COIN_INTERVAL = 8;
  public static final double GUPPY_COIN_MULTIPLIER = 10;


  /* Pellet's constant */
  public static final double PELLET_SPEED = 0.125;        /* TBD */
  public static final int PELLET_PRICE = 10;

  /* Piranha constants */
  public static final int PIRANHA_FOOD_THRES = 50;
  public static final int PIRANHA_PRICE = 400;
  public static final double PIRANHA_EAT_RADIUS = 50;
  public static final double PIRANHA_FULL_INTERVAL = 15;    /* Ini detik ye bos */
  public static final double PIRANHA_HUNGER_INTERVAL = 15;
  public static final double PIRANHA_MOVE_SPEED = 82;
  public static final double PIRANHA_COIND_INTERVAL = 8;

  /* Snail's constant */
  public static final int SNAIL_PRICE = 1000;
  public static final double SNAIL_SPEED = 80;
  public static final double SNAIL_EAT_RADIUS = 50;

  /* Progress */
  public static final double PROGRESS_PERIOD = 10;
  public static final double GUPPY_TURN_PROGRESS_INCREMENT_TIME = 0.05;
  public static final double GUPPY_MOVE_PROGRESS_INCREMENT_TIME = 0.05;
  public static final double GUPPY_DEAD_PROGRESS_INCREMENT_TIME = 0.16;
  public static final double GUPPY_EAT_PROGRESS_INCREMENT_TIME = 0.05;
  public static final double PIRANHA_TURN_PROGRESS_INCREMENT_TIME = 0.05;
  public static final double PIRANHA_MOVE_PROGRESS_INCREMENT_TIME = 0.05;
  public static final double PIRANHA_DEAD_PROGRESS_INCREMENT_TIME = 0.16;
  public static final double PIRANHA_EAT_PROGRESS_INCREMENT_TIME = 0.05;
  public static final double SNAIL_TURN_PROGRESS_INCREMENT_TIME = 0.05;
  public static final double SNAIL_MOVE_PROGRESS_INCREMENT_TIME = 0.05;
  public static final double PELLET_MOVE_PROGRESS_INCREMENT_TIME = 0.05;
  public static final double COIN_PROGRESS_INCREMENT_TIME = 0.05;
  public static final double COIN_FADE_PROGRESS_INCREMENT_TIME =
      COIN_DELETION_INTERVAL / PROGRESS_PERIOD;

  /*------------------------------------------*/
  /* ---------------- Methods --------------- */
  /*------------------------------------------*/

  /**
   * Generates random double in [min, max].
   *
   * @param min Lower bound
   * @param max Upper bound
   */
  public static double random(double min, double max) {
    return min + Math.random() * (max - min);
  }
}
