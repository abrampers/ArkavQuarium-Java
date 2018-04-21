package itb.arkavquarium;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.Iterator;

public class GameController extends JPanel implements ActionListener {

  private Timer timer;

  // Events
  private int mouseClickX;
  private int mouseClickY;
  private boolean mouseClickValid;

  // Game state
  private Aquarium aquarium;
  private GameState gameState;
  private int coinCount;
  private int eggCount;
  LinkedList<Guppy> contentGuppy;
  LinkedList<Piranha> contentPiranha;
  LinkedList<Snail> contentSnail;
  LinkedList<Coin> contentCoin;
  LinkedList<Pellet> contentPellet;

  public GameController() {

    // Add keyboard event listener
    addKeyListener(new KAdapter());

    // Add mouse event listener
    addMouseListener(new MAdapter());
    mouseClickValid = false;

    // Setup window
    setBackground(Color.black);
    setFocusable(true);
    setPreferredSize(new Dimension(Constants.GRAPHICS_WIN_WIDTH, Constants.GRAPHICS_WIN_HEIGHT));

    // Init game state
    gameState = GameState.uninitialized;

    // Init game timer, choose framerate
    timer = new Timer(Constants.GRAPHICS_FRAME_DELAY, this);
    timer.start();
  }

  private void initGameState() {

    // Init game state
    aquarium = new Aquarium(Constants.GAME_SCREEN_LEFT_PADDING, Constants.GAME_SCREEN_TOP_PADDING,
      Constants.GRAPHICS_WIN_WIDTH - Constants.GAME_SCREEN_RIGHT_PADDING,
      Constants.GRAPHICS_WIN_HEIGHT - Constants.GAME_SCREEN_BOTTOM_PADDING);
    coinCount = 0;
    eggCount = 0;

    // Get aquarium objects
    contentGuppy = aquarium.getContentGuppy();
    contentPiranha = aquarium.getContentPiranha();
    contentSnail = aquarium.getContentSnail();
    contentCoin = aquarium.getContentCoin();
    contentPellet = aquarium.getContentPellet();
  }

  private void updateGameState() {

    // Update game state
    aquarium.updateState((double) Constants.GRAPHICS_FRAME_DELAY);

    // Get updated aquarium objects
    contentGuppy = aquarium.getContentGuppy();
    contentPiranha = aquarium.getContentPiranha();
    contentSnail = aquarium.getContentSnail();
    contentCoin = aquarium.getContentCoin();
    contentPellet = aquarium.getContentPellet();
  }

  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);

    drawAssets(graphics);
  }

  private void drawAssets(Graphics graphics) {

    if (gameState == GameState.uninitialized) {
      drawMainMenu(graphics);

    } else if (gameState == GameState.won) {
      drawWinMenu(graphics);

    } else if (gameState == GameState.lost) {
      drawLoseMenu(graphics);

    } else {
      drawAquarium(graphics);
      drawTopBar(graphics);

      // Draw guppy
      Iterator<Guppy> guppyIterator = contentGuppy.iterator();
      while (guppyIterator.hasNext()) {
          Guppy guppy = guppyIterator.next();
          drawGuppy(graphics, guppy);
      }

      // Draw piranha
      Iterator<Piranha> piranhaIterator = contentPiranha.iterator();
      while (piranhaIterator.hasNext()) {
        Piranha piranha = piranhaIterator.next();
        drawPiranha(graphics, piranha);
      }

      // Draw snail
      Iterator<Snail> snailIterator = contentSnail.iterator();
      while (snailIterator.hasNext()) {
        Snail snail = snailIterator.next();
        drawSnail(graphics, snail);
      }

      // Draw coin
      Iterator<Coin> coinIterator = contentCoin.iterator();
      while (coinIterator.hasNext()) {
        Coin coin = coinIterator.next();
        drawCoin(graphics, coin);
      }

      // Draw pellet
      Iterator<Pellet> pelletIterator = contentPellet.iterator();
      while (pelletIterator.hasNext()) {
        Pellet pellet = pelletIterator.next();
        drawPellet(graphics, pellet);
      }

    }
  }

  private void drawAquarium(Graphics graphics) {
    String assetPath = "assets/graphics/statics/aquarium_background.jpg";
    Image aquariumImage = new ImageIcon(assetPath).getImage();
    graphics.drawImage(aquariumImage, 0, 0, this);
  }

  private void drawTopBar(Graphics graphics) {
    // Draw top bar UI
    String assetPath = "assets/graphics/statics/aquarium_ui.png";
    Image topBarImage = new ImageIcon(assetPath).getImage();
    graphics.drawImage(topBarImage, 0, 0, this);

    // Draw texts
//    drawText(to_string(coin_count), coinTextSize, coinTextX,
//      coinTextY, coinTextColorR, coinTextColorG, coinTextColorB);
//
//    drawText(to_string(guppyPrice), priceTextSize, guppyPriceTextX,
//      guppyPriceTextY, priceTextColorR, priceTextColorG, priceTextColorB);
//
//    drawText(to_string(pelletPrice), priceTextSize, pelletPriceTextX,
//      pelletPriceTextY, priceTextColorR, priceTextColorG, priceTextColorB);
//
//    drawText(to_string(piranhaPrice), priceTextSize, piranhaPriceTextX,
//      piranhaPriceTextY, priceTextColorR, priceTextColorG, priceTextColorB);
//
//    drawText(to_string(snailPrice), priceTextSize, snailPriceTextX,
//      snailPriceTextY, priceTextColorR, priceTextColorG, priceTextColorB);
//
//    drawText(to_string(eggPrice), priceTextSize, eggPriceTextX,
//      eggPriceTextY, priceTextColorR, priceTextColorG, priceTextColorB);
//
//    drawText(to_string(egg_count), eggCountTextSize, eggCountTextX,
//      eggCountTextY, eggCountTextColorR, eggCountTextColorG, eggCountTextColorB);
  }

  private void drawMainMenu(Graphics graphics) {
    String assetPath = "assets/graphics/statics/main_menu.jpg";
    Image mainMenuImage = new ImageIcon(assetPath).getImage();
    graphics.drawImage(mainMenuImage, 0, 0, this);
  }

  private void drawWinMenu(Graphics graphics) {
    String assetPath = "assets/graphics/statics/win_menu.jpg";
    Image winMenuImage = new ImageIcon(assetPath).getImage();
    graphics.drawImage(winMenuImage, 0, 0, this);
  }

  private void drawLoseMenu(Graphics graphics) {
    String assetPath = "assets/graphics/statics/lose_menu.jpg";
    Image loseMenuImage = new ImageIcon(assetPath).getImage();
    graphics.drawImage(loseMenuImage, 0, 0, this);
  }

  private void drawGuppy(Graphics graphics, Guppy guppy) {
    int level = 1;
    boolean hungry = false;
    int progress = guppy.getProgress();
    State state = guppy.getState();
    String assetPath = "assets/graphics/sprites/guppy";

    // Check level
    if (level == 1) {
      assetPath += "/small";
    } else if (level == 2) {
      assetPath += "/medium";
    } else {
      assetPath += "/large";
    }

    // Check state
    if (state == State.movingLeft) {
      assetPath += hungry ? "/move/hungry/left" : "/move/normal/left";

    } else if (state == State.movingRight) {
      assetPath += hungry ? "/move/hungry/right" : "/move/normal/right";

    } else if (state == State.turningLeft) {
      assetPath += hungry ? "/turn/hungry/left" : "/turn/normal/left";

    } else if (state == State.turningRight) {
      assetPath += hungry ? "/turn/hungry/right" : "/turn/normal/right";

    } else if (state == State.eatingLeft) {
      assetPath += hungry ? "/eat/hungry/left" : "/eat/normal/left";

    } else if (state == State.eatingRight) {
      assetPath += hungry ? "/eat/hungry/right" : "/eat/normal/right";

    } else if (state == State.deadLeft) {
      assetPath += "/dead/left";

    } else if (state == State.deadRight) {
      assetPath += "/dead/right";
    }

    // Draw asset
    assetPath += "/" + (progress + 1) + ".png";
    Image guppyImage = new ImageIcon(assetPath).getImage();
    graphics.drawImage(guppyImage, (int) guppy.getX(), (int) guppy.getY(), this);
  }

  private void drawPiranha(Graphics graphics, Piranha piranha) {
    boolean hungry = false;
    int progress = piranha.getProgress();
    State state = piranha.getState();
    String assetPath = "assets/graphics/sprites/piranha";

    // Check state
    if (state == State.movingLeft) {
      assetPath += hungry ? "/move/hungry/left" : "/move/normal/left";

    } else if (state == State.movingRight) {
      assetPath += hungry ? "/move/hungry/right" : "/move/normal/right";

    } else if (state == State.turningLeft) {
      assetPath += hungry ? "/turn/hungry/left" : "/turn/normal/left";

    } else if (state == State.turningRight) {
      assetPath += hungry ? "/turn/hungry/right" : "/turn/normal/right";

    } else if (state == State.eatingLeft) {
      assetPath += hungry ? "/eat/hungry/left" : "/eat/normal/left";

    } else if (state == State.eatingRight) {
      assetPath += hungry ? "/eat/hungry/right" : "/eat/normal/right";

    } else if (state == State.deadLeft) {
      assetPath += "/dead/left";

    } else if (state == State.deadRight) {
      assetPath += "/dead/right";
    }

    // Draw asset
    assetPath += "/" + (progress + 1) + ".png";
    Image piranhaImage = new ImageIcon(assetPath).getImage();
    graphics.drawImage(piranhaImage, (int) piranha.getX(), (int) piranha.getY(), this);
  }

  private void drawSnail(Graphics graphics, Snail snail) {
    int progress = snail.getProgress();
    State state = snail.getState();
    String assetPath = "assets/graphics/sprites/snail";

    // Check state
    if (state == State.movingLeft) {
      assetPath += "/move/left";

    } else if (state == State.movingRight) {
      assetPath += "/move/right";

    } else if (state == State.turningLeft) {
      assetPath += "/turn/left";

    } else if (state == State.turningRight) {
      assetPath += "/turn/right";

    } else if (state == State.stillRight) {
      assetPath += "/move/right";

    } else if (state == State.stillLeft) {
      assetPath += "/move/left";
    }

    // Draw asset
    assetPath += "/" + (progress + 1) + ".png";
    Image snailImage = new ImageIcon(assetPath).getImage();
    graphics.drawImage(snailImage, (int) snail.getX(), (int) snail.getY(), this);
  }

  private void drawCoin(Graphics graphics, Coin coin) {
    boolean isGold = coin.getValue() > 20;
    int progress = coin.getProgress();
    String assetPath = "assets/graphics/sprites/coin";

    // Draw asset
    assetPath += isGold ? "/gold" : "/silver";
    assetPath += "/" + (progress + 1) + ".png";
    Image coinImage = new ImageIcon(assetPath).getImage();
    graphics.drawImage(coinImage, (int) coin.getX(), (int) coin.getY(), this);
  }

  private void drawPellet(Graphics graphics, Pellet pellet) {
    int progress = pellet.getProgress();
    String assetPath = "assets/graphics/sprites/pellet";

    // Draw asset
    assetPath += "/" + (progress + 1) + ".png";
    Image pelletImage = new ImageIcon(assetPath).getImage();
    graphics.drawImage(pelletImage, (int) pellet.getX(), (int) pellet.getY(), this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (gameState == GameState.uninitialized) {
      // Check if start button clicked
      if (areaClicked(Constants.MAIN_START_BUTTON_X_START, Constants.MAIN_START_BUTTON_X_END,
        Constants.MAIN_START_BUTTON_Y_START, Constants.MAIN_START_BUTTON_Y_END)) {
        gameState = GameState.running;
        initGameState();
      }

    } else if (gameState == GameState.won) {
      // Check if start button clicked
      if (areaClicked(Constants.WIN_START_BUTTON_X_START, Constants.WIN_START_BUTTON_X_END,
        Constants.WIN_START_BUTTON_Y_START, Constants.WIN_START_BUTTON_Y_END)) {
        gameState = GameState.running;
        initGameState();
      }

    } else if (gameState == GameState.lost) {
      // Check if start button clicked
      if (areaClicked(Constants.LOSE_START_BUTTON_X_START, Constants.LOSE_START_BUTTON_X_END,
        Constants.LOSE_START_BUTTON_Y_START, Constants.LOSE_START_BUTTON_Y_END)) {
        gameState = GameState.running;
        initGameState();
      }

    } else {
      // Update game state
      updateGameState();

      // TODO: Handle clicks

    }

    // Render frame
    repaint();
  }

  private boolean areaClicked(int xStart, int xEnd, int yStart, int yEnd) {

    if (mouseClickX >= xStart && mouseClickX <= xEnd && mouseClickY >= yStart && mouseClickY <= yEnd &&
      mouseClickValid) {
      mouseClickValid = false;
      return true;
    } else {
      return false;
    }
  }

  private class KAdapter extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

      int key = e.getKeyCode();

      if ((key == KeyEvent.VK_LEFT)) {

      }

      if ((key == KeyEvent.VK_RIGHT)) {

      }

      if ((key == KeyEvent.VK_UP)) {

      }

      if ((key == KeyEvent.VK_DOWN)) {

      }
    }
  }

  private class MAdapter extends MouseAdapter {

    @Override
    public void mouseReleased(MouseEvent e) {

      mouseClickX = e.getX();
      mouseClickY = e.getY();
      mouseClickValid = true;
    }
  }

  public static void main(String[] args) {

    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        JFrame ex = new GameView();
        ex.setVisible(true);
      }
    });
  }
}
