package itb.arkavquarium;

/**
 * <h1>Aquarium!</h1>
 * The Aquarium class implements and updates all objects in Aquarium.
 * This simulates movement and state of the objects.
 *
 * @author  Abram Situmorang
 * @author  Faza Fahleraz
 * @author  Senapati Sang Diwangkara
 * @author  Yusuf Rahmat Pratama
 * @version 0.0
 * @since   2018-04-15
 */
public class Aquarium {
    /*------------------------------------------*/
    /* -------------- Attributes -------------- */
    /*------------------------------------------*/

    private final double X_MIN;
    private final double Y_MIN;
    private final double X_MAX;
    private final double Y_MAX;
    private double currTime;
    private LinkedList<Piranha> contentPiranha;
    private LinkedList<Guppy> contentGuppy;
    private LinkedList<Snail> contentSnail;
    private LinkedList<Pellet> contentPellet;
    private LinkedList<Coin> contentCoin;

    /*------------------------------------------*/
    /* ------------- Constructors ------------- */
    /*------------------------------------------*/

    /** A constructor.
     * Constructs a new Aquarium object.
     * @param xMin Minium x value in the aquarium.
     * @param yMin Minium y value in the aquarium.
     * @param xMax Maximum x value in the aquarium.
     * @param yMax Maximum y value in the aquarium.
     * */
    public Aquarium(double xMin, double yMin, double xMax, double yMax) {
        this.currTime = 0;
        this.X_MIN = xMin;
        this.Y_MIN = yMin;
        this.X_MAX = xMax;
        this.Y_MAX = yMax;
        this.createGuppy();
    }

    /*------------------------------------------*/
    /* ------------ Setter & Getter ----------- */
    /*------------------------------------------*/

    /** Getter for X_MIN.
     * @return The value of X_MIN
     * */
    public double getXMin() {
        return X_MIN;
    }

    /** Getter for Y_MIN.
     * @return The value of Y_MIN
     * */
    public double getYMin() {
        return Y_MIN;
    }

    /** Getter for X_MAX.
     * @return The value of X_MAX
     * */
    public double getXMax() {
        return X_MAX;
    }

    /** Getter for Y_MAX.
     * @return The value of Y_MAX
     * */
    public double getYMax() {
        return Y_MAX;
    }

    /** Getter for currentTime.
     * @return The value of currentTime
     * */
    public double getCurrTime() {
        return currTime;
    }

    /** Getter for contentPiranha.
     * @return The contentPiranha list
     * */
    public ArrayList<Piranha> getContentPiranha() {
        return contentPiranha;
    }

    /** Getter for contentGuppy.
     * @return The contentGuppy list
     * */
    public ArrayList<Guppy> getContentGuppy() {
        return contentGuppy;
    }

    /** Getter for contentSnail.
     * @return The contentSnail list
     * */
    public ArrayList<Snail> getContentSnail() {
        return contentSnail;
    }

    /** Getter for contentPellet.
     * @return The contentPellet list
     * */
    public ArrayList<Pellet> getContentPellet() {
        return contentPellet;
    }

    /** Getter for contentCoin.
     * @return The contentCoin list
     * */
    public ArrayList<Coin> getContentCoin() {
        return contentCoin;
    }

    /** Setter for currentTime.
     * @param currTime  The value of the new current time
     * */
    public void setCurrTime(double currTime) {
        this.currTime = currTime;
    }

    /*------------------------------------------*/
    /* ---------------- Methods --------------- */
    /*------------------------------------------*/

    /** Updates all the aquarium object state.
     * @param current_time The new current time
     * */
    public void updateState(double current_time) {
        // TODO: Implement
    }

    /** Create Piranha.
     * Creates a new Piranha and adds it to contentPiranha list.
     * */
    public void createPiranha() {
        contentPiranha.add(new Piranha(this));
    }

    /** Create Guppy.
     * Creates a new Guppy and adds it to contentGuppy list.
     * */
    public void createGuppy() {
        contentGuppy.add(new Guppy(this));
    }

    /** Create Snail.
     * Creates a new Snail and adds it to contentSnail list.
     * */
    public void createSnail() {
        contentSnail.add(new Snail(this));
    }

    /** Create Pellet.
     * Creates a new Pellet and adds it to contentPellet list.
     * @param x x-axis position of the newly created Pellet.
     * @param y y-axis position of the newly created Pellet.
     * */
    public void createPellet(double x, double y) {
        contentPellet.add(new Pellet(x, y, this));
    }

    /** Create Coin.
     * Creates a new Coin and adds it to contentCoin list.
     * @param x x-axis position of the newly created Coin.
     * @param y y-axis position of the newly created Coin.
     * @param value the value of the Coin
     * */
    public void createCoin(double x, double y, int value) {
        contentCoin.add(new Coin(x, y, value, this));
    }

    /** Delete Piranha.
     * Deletes a Piranha and removes it from contentPiranha list.
     * @param p The Piranha object to be deleted.
     * */
    public void deletePiranha(Piranha p) {
        contentPiranha.remove(p);
    }

    /** Delete Guppy.
     * Deletes a Guppy and removes it from contentGuppy list.
     * @param g The Guppy object to be deleted.
     * */
    public void deleteGuppy(Guppy g) {
        contentGuppy.remove(g);
    }

    /** Delete Snail.
     * Deletes a Snail and removes it from contentSnail list.
     * @param s The Snail object to be deleted.
     * */
    public void deleteSnail(Snail s) {
        contentSnail.remove(s);
    }

    /** Delete Pellet.
     * Deletes a Pellet and removes it from contentPellet list.
     * @param p The Pellet object to be deleted.
     * */
    public void deletePellet(Pellet p) {
        contentPellet.remove(p);
    }

    /** Delete Coin.
     * Deletes a Coin and removes it from contentCoin list.
     * @param c The Coin object to be deleted.
     * */
    public void deleteCoin(Coin c) {
        contentCoin.remove(c);
    }
}
