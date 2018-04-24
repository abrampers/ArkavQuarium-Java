package itb.arkavquarium;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * <h1>GameView! TODO: Isi ye jon.</h1>
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-15
 */
class GameView extends JFrame {

  /**
   * TODO: ISI YA.
   */
  public GameView() {
    add(new GameController());
    setResizable(false);
    pack();
    setTitle("ArkavQuarium");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
