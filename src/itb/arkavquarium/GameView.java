package itb.arkavquarium;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameView extends JFrame {

  /**
   * TODO: ISI YA
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
