package itb.arkavquarium;

import java.util.*;

/**
 * <h1>LinkedList!</h1> Class that contains linked list for storage of classes' data.
 *
 * @author Abram Situmorang
 * @author Faza Fahleraz
 * @author Senapati Sang Diwangkara
 * @author Yusuf Rahmat Pratama
 * @version 0.0
 * @since 2018-04-17
 */
public class LinkedList<E> {
  /*------------------------------------------*/
  /* -------------- Attributes -------------- */
  /*------------------------------------------*/

  private Node<E> head;
  private int length;

  /*------------------------------------------*/
  /* ------------- Constructors ------------- */
  /*------------------------------------------*/

  /**
   * A constructor. Constructs a new LinkedList object.
   */
  public LinkedList() {
    this.head = new Node<E>();
    this.length = 0;
  }

  /*------------------------------------------*/
  /* ------------ Setter & Getter ----------- */
  /*------------------------------------------*/

  /**
   * Getter for Length
   *
   * @return int length of list (excluding dummy)
   */
  int getLength() {
    return this.length;
  }

  /*------------------------------------------*/
  /* ---------------- Methods --------------- */
  /*------------------------------------------*/

  /**
   * Check if list is empty
   *
   * @return true if list is empty
   */
  public boolean isEmpty() {
    return this.head.getNext() == null;
  }

  /**
   * Add new element
   */
  public void add(E e) {
    Node<E> n = new Node<E>(e);
    Node<E> currNode = this.head;
    while (currNode.getNext() != null) {
      currNode = currNode.getNext();
    }
    currNode.setNext(n);
    n.setPrev(currNode);
    this.length += 1;
  }

  /**
   * Remove element, do nothing if element not found
   */
  public void remove(E e) {
    if (!this.isEmpty()) {
      Node<E> currNode = this.head;
      while (currNode.getNext() != null) {
        currNode = currNode.getNext();
      }
      Node<E> prevNode = currNode.getPrev();
      Node<E> nextNode = currNode.getNext();
      prevNode.setNext(nextNode);
      if (currNode.getNext() != null) {
        nextNode.setPrev(prevNode);
      }
      length -= 1;
    }
  }

  /**
   * Returns an iterator over the elements in the list in proper sequence
   *
   * @return an iterator for the linkedlist
   */
  public Iterator<E> iterator() {
    return new LLIterator();
  }

  /**
   * Private Class For Iterator
   */
  private class LLIterator implements Iterator<E> {

    /*------------------------------------------*/
    /* -------------- Attributes -------------- */
    /*------------------------------------------*/

    private Node<E> cursor;
    private int lastRet;

    /*------------------------------------------*/
    /* ------------- Constructors ------------- */
    /*------------------------------------------*/

    /**
     * A constructor. Constructs a new Iterator object.
     */
    public LLIterator() {
      this.cursor = LinkedList.this.head;
      this.lastRet = -1;
    }

    /*------------------------------------------*/
    /* ---------------- Methods --------------- */
    /*------------------------------------------*/

    /**
     * Implementation of Iterator check if there is next element
     */
    public boolean hasNext() {
      return (this.cursor.getNext() != null);
    }

    /**
     * Implementation of Iterator get next element
     *
     * @return value of next element
     */
    public E next() {
      if (!this.hasNext()) {
        throw new NoSuchElementException();
      } else {
        this.cursor = this.cursor.getNext();
        this.lastRet = 0;
        return this.cursor.getValue();
      }
    }

    /**
     * Implementation of Iterator remove element in cursor, can only be called only after next() has
     * been called Set current cursor to prev element
     */
    public void remove() {
      if (this.lastRet < 0) {
        throw new IllegalStateException();
      } else {
        LinkedList.this.remove(this.cursor.getValue());
        this.cursor = this.cursor.getPrev();
        this.lastRet = -1;
      }
    }
  }

  private class Node<E> {
    /*------------------------------------------*/
    /* -------------- Attributes -------------- */
    /*------------------------------------------*/

    private E value;
    private Node<E> prev;
    private Node<E> next;

    /*------------------------------------------*/
    /* ------------- Constructors ------------- */
    /*------------------------------------------*/

    /**
     * Default constructor. Constructs a new Node object.
     */
    public Node() {
      this.value = null;
      this.prev = null;
      this.next = null;
    }

    /**
     * User-defined constructor. Constructs a new Node object.
     *
     * @param value The Value of the Node.
     */
    public Node(E value) {
      this.value = value;
      this.prev = null;
      this.next = null;
    }

    /*------------------------------------------*/
    /* ------------ Setter & Getter ----------- */
    /*------------------------------------------*/

    /**
     * Getter for Value
     *
     * @return The value of the Node
     */
    E getValue() {
      return this.value;
    }

    /**
     * Setter for Value
     *
     * @param value New value of the node
     */
    void setValue(E value) {
      this.value = value;
    }

    /**
     * Getter for Next Node
     *
     * @return The next Node
     */
    Node<E> getNext() {
      return this.next;
    }

    /**
     * Setter for Next Node
     *
     * @param n New Node for next
     */
    void setNext(Node<E> n) {
      this.next = n;
    }

    /**
     * Getter for Prev Node
     *
     * @return The previous Node
     */
    Node<E> getPrev() {
      return this.prev;
    }

    /**
     * Setter for Previous Node
     *
     * @param p New Node for previous
     */
    void setPrev(Node<E> p) {
      this.prev = p;
    }
  }
}
