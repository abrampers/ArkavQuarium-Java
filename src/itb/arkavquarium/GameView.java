package itb.arkavquarium;

import javax.swing.*;

public class GameView extends JFrame {

  public GameView() {
    add(new GameController());
    setResizable(false);
    pack();
    setTitle("ArkavQuarium");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }
}
