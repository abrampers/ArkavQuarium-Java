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

  // Assets
  private Image mainMenu;
  private Image winMenu;
  private Image loseMenu;
  private Image aquariumBackground;
  private Image aquariumUI;
  private HashMap<String, Image[]> guppyImage;

  // Game state
  private Aquarium aquarium;
  private GameState gameState;
  private int coin;
  private int egg;
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

    setBackground(Color.black);
    setFocusable(true);
    setPreferredSize(new Dimension(Constants.GRAPHICS_WIN_WIDTH, Constants.GRAPHICS_WIN_HEIGHT));

    loadAssets();
    gameState = GameState.uninitialized;

    // Init game timer, choose framerate
    timer = new Timer(Constants.GRAPHICS_FRAME_DELAY, this);
    timer.start();
  }

  private void loadAssets() {

    // Menu assets
    mainMenu = new ImageIcon("assets/graphics/statics/main_menu.jpg").getImage();
    winMenu = new ImageIcon("assets/graphics/statics/win_menu.jpg").getImage();
    loseMenu = new ImageIcon("assets/graphics/statics/lose_menu.jpg").getImage();

    // Gameplay assets
    aquariumBackground = new ImageIcon("assets/graphics/statics/aquarium_background.jpg").getImage();
    aquariumUI = new ImageIcon("assets/graphics/statics/aquarium_ui.png").getImage();

    // Guppy
    guppyImage = new HashMap<>();
    for (String key : Constants.guppyImageStyles) {
      Image[] images = new Image[10];
      for (int i = 0; i < Constants.GUPPY_SPRITE_LEN; i++) {
        images[i] = new ImageIcon(key + (i + 1) + ".png").getImage();
      }
      guppyImage.put(key, images);
    }
  }

  private void initGameState() {

    // Init game state
    aquarium = new Aquarium(Constants.GAME_SCREEN_LEFT_PADDING, Constants.GAME_SCREEN_TOP_PADDING,
      Constants.GRAPHICS_WIN_WIDTH - Constants.GAME_SCREEN_RIGHT_PADDING,
      Constants.GRAPHICS_WIN_HEIGHT - Constants.GAME_SCREEN_BOTTOM_PADDING);
    coin = 0;
    egg = 0;

    // Get aquarium objects
    contentGuppy = aquarium.getContentGuppy();
    contentPiranha = aquarium.getContentPiranha();
    contentSnail = aquarium.getContentSnail();
    contentCoin = aquarium.getContentCoin();
    contentPellet = aquarium.getContentPellet();
  }

  private void updateGameState() {

    // Update game state
    aquarium.updateState(Constants.GRAPHICS_FRAME_DELAY);

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
      graphics.drawImage(mainMenu, 0, 0, this);

    } else if (gameState == GameState.won) {
      graphics.drawImage(winMenu, 0, 0, this);

    } else if (gameState == GameState.lost) {
      graphics.drawImage(loseMenu, 0, 0, this);

    } else {
      graphics.drawImage(aquariumBackground, 0, 0, this);
      graphics.drawImage(aquariumUI, 0, 0, this);

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
        //drawPiranha(graphics, piranha);
      }

      // Draw snail
      Iterator<Snail> snailIterator = contentSnail.iterator();
      while (snailIterator.hasNext()) {
        Snail snail = snailIterator.next();
        //drawSnail(graphics, snail);
      }

      // Draw coin
      Iterator<Coin> coinIterator = contentCoin.iterator();
      while (coinIterator.hasNext()) {
        Coin coin = coinIterator.next();
        //drawCoin(graphics, coin);
      }

      // Draw pellet
      Iterator<Pellet> pelletIterator = contentPellet.iterator();
      while (pelletIterator.hasNext()) {
        Pellet pellet = pelletIterator.next();
        //drawPellet(graphics, pellet);
      }

    }
  }

  private void drawGuppy(Graphics graphics, Guppy guppy) {
    int level = 1;
    boolean hungry = false;
    int progress = guppy.getProgress();
    State state = guppy.getState();
    String assetPath = "assets/graphics/sprites/guppy";

    // TODO: Check level
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

    assetPath += "/" + (progress + 1) + ".png";
    Image guppyImage = new ImageIcon(assetPath).getImage();
    graphics.drawImage(guppyImage, (int) guppy.getX(), (int) guppy.getY(), this);
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
