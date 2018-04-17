package itb.arkavquarium;
import java.util.*;

/**
 * <h1>LinkedList!</h1>
 * Class that contains linked list for storage of classes' data.
 *
 * @author  Abram Situmorang
 * @author  Faza Fahleraz
 * @author  Senapati Sang Diwangkara
 * @author  Yusuf Rahmat Pratama
 * @version 0.0
 * @since   2018-04-15
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

    /** A constructor.
     * Constructs a new LinkedList object.
     *
     * */
    public LinkedList() {
		this.head = new Node<E>();
		this.length = 0;
	}










	/**
	* Private Class For Iterator
	*/
	private class LLIterator<E> implements Iterator<E> {

		/*------------------------------------------*/
	    /* -------------- Attributes -------------- */
	    /*------------------------------------------*/

	    private Node<E> cursor;
	    private int lastRet;

	    /*------------------------------------------*/
	    /* ------------- Constructors ------------- */
	    /*------------------------------------------*/

	    /** A constructor.
	     * Constructs a new LinkedList object.
	     *
	     * */
	    public LLIterator() {
			this.cursor = head.getNext();
			this.lastRet = -1;
		}

		/*------------------------------------------*/
	    /* ---------------- Methods --------------- */
	    /*------------------------------------------*/

		/**
	     * Implementation of ListIterator
	     * Add new element
	     */
		public void add(E e) {
			Node<E> n = new Node<E>(e);
			Node<E> currNode = this.head;
			if (this.head.getNext() == null) {
				this.cursor = n;
				this.cursorIndex = 0;
			}
			while (currNode.getNext() != null) {
				currNode = currNode.getNext();
			}
			this.length += 1;
			currNode.setNext(n);
			n.setPrev(currNode);
		}

		/**
	     * Implementation of ListIterator
	     * check if there is next element
	     */
		public boolean hasNext() {
			return cursor.getNext() != null;
		}

		/**
	     * Implementation of ListIterator
	     * get next element
	     * @return value of next element
	     */
		public E next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			else {
				this.cursor = this.cursor.getNext();
				return this.cursor.getValue();
			}
		}

		/**
	     * Implementation of ListIterator
	     * get index of next element, or list size if cursor is at the end of list
	     * @return index of next element, or list size if cursor is at the end of list
	     */
		public int nextIndex() {
			if (!this.hasNext()) {
				return this.length;
			}
			else {
				return (this.cursorIndex + 1);
			}
		}

		/**
	     * Implementation of ListIterator
	     * get prev element
	     * @return value of prev element
	     */
		public E prev() {
			if (!this.hasPrev()) {
				throw new NoSuchElementException();
			}
			else {
				this.cursor = this.cursor.getPrev();
				this.cursorIndex -= 1;
				return this.cursor.getValue();
			}
		}

		/**
	     * Implementation of ListIterator
	     * get index of next element, or -1 if cursor is at the beginning of list
	     * @return index of next element, or -1 if cursor is at the beginning of list
	     */
		public int prevIndex() {
			if (!this.hasPrev()) {
				return -1;
			}
			else {
				return (this.cursorIndex - 1);
			}
		}

		/**
	     * Implementation of ListIterator
	     * remove element in cursor, can only be called only after next() and prev() and not after add() has been called
	     * Set current cursor to the next element, or prev element if there is no next element, or null if there is no element
	     */
		public void remove() {
			if (this.isEmpty()) {
				throw new IllegalStateException();
			}
			else {
				Node<E> prevNode = this.cursor.getPrev();
				Node<E> nextNode = this.cursor.getNext();
				prevNode.setNext(nextNode);
				if (this.hasNext()) {
					nextNode.setPrev(prevNode);
					this.cursor = nextNode;
				}
				else {
					this.cursor = prevNode;
					this.cursorIndex -= 1;
					if (this.cursorIndex < 0) {
						this.cursor = null;
					}
				}
				this.length -= 1;
			}
		}

		/**
	     * Implementation of ListIterator
	     * set new element in cursor, can only be called only after next() and prev() and not after add() has been called
	     */
		public void set(E e) {
			if (this.isEmpty()) {
				throw new IllegalStateException();
			}
			else {
				this.cursor.setValue(e);
			}
		}

		/**
	     * Return value of first element and Reset cursor to first element
	     * @return value of first element
	     */
		public E getFirst() {
			if (this.isEmpty()) {
				throw new NoSuchElementException();
			}
			else {
				this.cursor = this.head.getNext();
				this.cursorIndex = 0;
				return this.cursor.getValue();
			}
		}

		/**
	     * Check if list is empty (excluding dummy head)
	     * @return status of emptiness of list
	     */
		public boolean isEmpty() {
			return (this.length <= 0);
		}
	}




}
