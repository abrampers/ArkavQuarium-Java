package itb.arkavquarium;

/**
 * <h1>Constants! Class that contains a widely used constants in ArkavQuarium.</h1>
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
class Constants {

  /**
   * Graphics UI constants.
   */
  /* Window */
  public static final int GRAPHICS_WIN_WIDTH = 1024;
  public static final int GRAPHICS_WIN_HEIGHT = 768;
  public static final int GRAPHICS_FRAME_DELAY = 20;

  /* Texts */
  public static final int COIN_TEXT_SIZE = 23;
  public static final int COIN_TEXT_X = 885;
  public static final int COIN_TEXT_Y = 89;
  public static final int COIN_TEXT_COLOR_R = 166;
  public static final int COIN_TEXT_COLOR_G = 255;
  public static final int COIN_TEXT_COLOR_B = 112;

  public static final int PRICE_TEXT_SIZE = 15;
  public static final int PRICE_TEXT_COLOR_R = 166;
  public static final int PRICE_TEXT_COLOR_G = 255;
  public static final int PRICE_TEXT_COLOR_B = 112;

  public static final int GUPPY_PRICE_TEXT_X = 46;
  public static final int GUPPY_PRICE_TEXT_Y = 91;

  public static final int PELLET_PRICE_TEXT_X = 157;
  public static final int PELLET_PRICE_TEXT_Y = 92;

  public static final int PIRANHA_PRICE_TEXT_X = 367;
  public static final int PIRANHA_PRICE_TEXT_Y = 92;

  public static final int SNAIL_PRICE_TEXT_X = 485;
  public static final int SNAIL_PRICE_TEXT_Y = 92;

  public static final int EGG_PRICE_TEXT_X = 722;
  public static final int EGG_PRICE_TEXT_Y = 92;

  public static final int EGG_COUNT_TEXT_SIZE = 30;
  public static final int EGG_COUNT_TEXT_X = 739;
  public static final int EGG_COUNT_TEXT_Y = 55;
  public static final int EGG_COUNT_TEXT_COLOR_R = 80;
  public static final int EGG_COUNT_TEXT_COLOR_G = 24;
  public static final int EGG_COUNT_TEXT_COLOR_B = 186;

  /* Click targets */
  public static final int BUY_GUPPY_BUTTON_X_START = 33;
  public static final int BUY_GUPPY_BUTTON_X_END = 109;
  public static final int BUY_GUPPY_BUTTON_Y_START = 7;
  public static final int BUY_GUPPY_BUTTON_Y_END = 72;

  public static final int BUY_PIRANHA_BUTTON_X_START = 354;
  public static final int BUY_PIRANHA_BUTTON_X_END = 430;
  public static final int BUY_PIRANHA_BUTTON_Y_START = 7;
  public static final int BUY_PIRANHA_BUTTON_Y_END = 72;

  public static final int BUY_SNAIL_BUTTON_X_START = 473;
  public static final int BUY_SNAIL_BUTTON_X_END = 547;
  public static final int BUY_SNAIL_BUTTON_Y_START = 7;
  public static final int BUY_SNAIL_BUTTON_Y_END = 72;

  public static final int BUY_EGG_BUTTON_X_START = 708;
  public static final int BUY_EGG_BUTTON_X_END = 783;
  public static final int BUY_EGG_BUTTON_Y_START = 7;
  public static final int BUY_EGG_BUTTON_Y_END = 72;

  public static final int MAIN_START_BUTTON_X_START = 15;
  public static final int MAIN_START_BUTTON_X_END = 184;
  public static final int MAIN_START_BUTTON_Y_START = 13;
  public static final int MAIN_START_BUTTON_Y_END = 115;

  public static final int WIN_START_BUTTON_X_START = 444;
  public static final int WIN_START_BUTTON_X_END = 518;
  public static final int WIN_START_BUTTON_Y_START = 440;
  public static final int WIN_START_BUTTON_Y_END = 460;

  public static final int LOSE_START_BUTTON_X_START = 444;
  public static final int LOSE_START_BUTTON_X_END = 518;
  public static final int LOSE_START_BUTTON_Y_START = 440;
  public static final int LOSE_START_BUTTON_Y_END = 460;

  /* Game constants */
  public static final int GAME_INITIAL_COIN = 200;
  public static final int GAME_SCREEN_LEFT_PADDING = 20;
  public static final int GAME_SCREEN_RIGHT_PADDING = 60;
  public static final int GAME_SCREEN_TOP_PADDING = 170;
  public static final int GAME_SCREEN_BOTTOM_PADDING = 100;
  public static final int MAX_LEVEL = 3;
  public static final int EGG_PRICE = 2000;
  public static final double RANDOM_MOVE_INTERVAL = 2;    /* Ini juga detik ya brok */

  /* Coin's constant */
  public static final double COIN_MOVE_SPEED = 100;         /* TBD */
  public static final double COIN_DELETION_INTERVAL = 5;    /* TBD */
  public static final int COIN_GOLD_THRESHOLD = 20;
  public static final int COIN_CLICK_AREA_WIDTH = 40;
  public static final int COIN_CLICK_AREA_HEIGHT = 40;

  /* Guppy constants */
  public static final int GUPPY_FOOD_THRES = 2;
  public static final int GUPPY_PRICE = 100;
  public static final double GUPPY_EAT_RADIUS = 20;
  public static final double GUPPY_FULL_INTERVAL = 10;    /* Ini detik ye bos */
  public static final double GUPPY_HUNGER_INTERVAL = 10;
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

  /*------------------------------------------*/
  /* ---------------- Methods --------------- */
  /*------------------------------------------*/

  /**
   * Generates random double in [min, max].
   *
   * @param min Lower bound.
   * @param max Upper bound.
   * @return Random value generated.
   */
  public static double random(double min, double max) {
    return min + Math.random() * (max - min);
  }
}
