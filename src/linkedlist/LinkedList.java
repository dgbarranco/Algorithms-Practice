package linkedlists;
import java.util.*;
import java.util.NoSuchElementException;

/** A class representing a singly linked list. */
public class LinkedList {
	private Node head, tail;

	/** Constructor of class LinkedList */
	public LinkedList() {
		head = null;
		tail = null;
	}

	/**
	 * Creates a new node with the given element and adds it to the back of the
	 * list
	 * @param elem Element to append at the end of the linked list
	 */
	public void append(int elem) {
		Node newNode = new Node(elem);
		if (tail != null) {
			tail.setNext(newNode);
			tail = newNode;
		} else {
			head = tail = newNode;
		}
	}

	/**
	 * Returns a string representing this linked list
	 * @return string representation of the linked list
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node current = head;

		while (current != null) {
			sb.append(current.elem() + " ");
			current = current.next();
		}
		return sb.toString();
	}

	public Node head() {
		return head; // used for testing
	}

	public Node tail() {
		return tail; // used for testing
	}

	/**
	 * Remove the node at a given index i
	 * @param i index
	 */
	public void removeAtIndex(int i) {

		//makes sure that i is not a negative index
		if(i < 0){
			throw new IndexOutOfBoundsException();
		}

		//makes sure that the list it is iterating through exist
		if(head == null){
			throw new IndexOutOfBoundsException();
		}

		//makes sure that if i = 0 that the next node following head will then be set to the new head
		if(i == 0){
			head = head.next();

			//if the head then has no value then it sets the tail to have no value either
			if(head == null){
				tail = null;
			}

			//allows for the if statement to be broke out of
			return;
		}

		//sets the value of current to head
		Node current = head;

		//sets up a size so that we can iterate through the amount of times needed to be at the desired index
		int size = 0;

		//a while loop which makes sure that current is a node which contains a value and that the size/placement is less than the index given
		for(int id = 0; id < i; id++){

			if(current.next() == null){
				throw new IndexOutOfBoundsException();
			}

			//continues to iterate through the linked list
			current = current.next();

			//increases the size of the list so that we can find the entire size


		}

		//if current no longer has a following node then an exception will be thrown out
		if(current.next() == null){
			throw new IndexOutOfBoundsException();
		}

		//if the following method == the tail then the tail becomes the current node
		if(current.next() == tail){
			tail = current;
		}

		//sets the next node to be two iterations after current.
		current.setNext(current.next().next());




		// Remove a node at a given "index" i. Handle invalid indices - throw IndexOutOfBoundsException.
		// Example:
		// If the linked list is 5 -> 7 -> 3 -> 4,  and we call
		// removeAtIndex(2), the linked list will be
		// 5 -> 7 -> 4  because we deleted "3" since it was at index = 2.
		// You may NOT use an array, ArrayList, built in Java's LinkedList or any other data structures.
		// 1. FILL IN CODE below:

		// 2. Give the running time of the method in the README and provide an explanation.

	}

	/**
	 * Check if a linked list has a cycle.
	 * A cycle means that some node’s next points back to an earlier node (or to itself),
	 * so traversal would go forever.
	 * @return true if the list contains a cycle, false otherwise
	 */
	public boolean hasCycle() {

		//pointers which start at head
		Node slow = head;
		Node fast = head;

		//ensures that there is no empty list
		while(fast != null && fast.next() != null){

			//moves 1 node at a time
			slow = slow.next();

			//moves 2 nodes at a time
			fast = fast.next().next();

			//so eventually while slow and fast loop through there will be a point in which if a cycle exist then fast will lap persay slow and they will point to the same node
			if(slow == fast){
				return true;
			}


		}
		//if the link list is functioning of a cycle then the node after the tail needs to be the head
		//therefore the if statement will return true if the node following the tail is equal to the head

		//in any other case wether it be that nothing follows or that the head is not the following node false will be returned
		return false;
		/*


		 Example 1: 1 -> 2 -> 3 The method must return false (no cycle).
		 Example 2: 1 -> 2 -> 3 -> 2 -> 3 -> ...  The method must return true (a cycle 2->3->2).
		 Example 3: 1 -> 1 -> 1 -> ... Must return true (node 1's next points back to node 1).
		 Do NOT rely on tail for this method.
		 Constraints:
	     Do not use extra data structures (no array, ArrayList, HashSet, another LinkedList etc.).
		 Can use only O(1) additional memory. Make your method as efficient as possible.

		 Hint:
		 Use slow + fast pointers. Both start at the head.
		 fast moves with twice the speed.
		 If the list has a cycle, because fast moves one extra node per step compared to slow in a loop,
		 it must eventually land on the same node as slow.
		 */
		// 1. FILL IN CODE:

		// 2. Give the running time of the method in the README and provide an explanation.

	}

	/**
	 * Reverses this linked list in place (non-recursive).
	 * After calling this method, the order of nodes is reversed;
	 * the old head becomes the new tail, and the old tail becomes the new head.
	 */
	public void reverse() {


		//sets a node previous to empty
		Node prev = null;

		//creates current to the head
		Node current = head;

		//sets next to be empty
		Node next = null;

		//assigns tail to be head in order to begin the reversed list
		tail = head;


		//if the list is not empty
		while (current!= null){

			//will assign next with the node which follows head
			next = current.next();

			//this then sets the following node to be empty in order to flip the arrow persay
			current.setNext(prev);

			//sets the already reversed node
			prev = current;

			//moves to the next node in order to repeat the process again
			current = next;

		}

		//sets head to be the last value of previous so that it is completely reversed
		head = prev;




		/* Example:
		Before: 4->12->18->1
		After (the same list got rearranged):  1->18->12->4, the head is now node 1.

		Constraints:
		Must be iterative (no recursion).
	    Do not use extra data structures (no new linked list, no array, ArrayList, HashSet, another LinkedList etc.).
        Reverse "in place" by changing next pointers
		Update head and tail, handle edge cases.

	    Hints:
		Use three references: prev, curr, next (see the slides).
        As you traverse, reverse each link so that curr’s next is set to prev.
		Make sure you don’t lose access to the rest of the list while rewiring pointers.
		When done, update head and tail.
		*/
		// 1. FILL IN CODE:


		// 2. Give the running time of the method in the README and provide an explanation.

	}

   /**
    * Returns a new linked list that is the sorted merge of this linked list and another linked list,
    * assuming both input lists are already sorted in increasing order.
    * @param other linked list
    * @return a new linked list that contains elements from this and other lists, in sorted order
    */
	public LinkedList mergeSorted(LinkedList other) {

		//checks that the second given list is not empty
		if(other == null){
			throw new IllegalArgumentException();

		}

		//creates a new linkedlist to store the merge of both the og linked list and other linked list
		LinkedList result = new LinkedList();

		//sets values for both the first and second linked list to be set to the first node of each assigned list
		Node first = this.head;
		Node second = other.head;

		//makes sure that the list are not empty
		while(first != null && second != null){

			//if the first element of the first list is less than the second list
			if(first.elem() <= second.elem()){

				//adds the first node to the merged list
				result.append(first.elem());

				//then iterates to the next node in the first linked list
				first = first.next();
			}

			//otherwise then
			else{

				//adds the first node of the second linked list into the merged linked list
				result.append(second.elem());

				//then iterates through the second node of the linked list to then analyze it
				second = second.next();
			}

		}


		//because one of the lists might be longer than the other there still needs to be iterations of the left over nodes so these statements take these into consideration

		//keeps iterating through while there are still nodes in the list

		//will iterate through the left over nodes in the first list if needed
		while(first != null){

			//adds the node to the merged list
			result.append(first.elem());

			//goes to the second node that follows
			first = first.next();
		}

		//will iterate through the left over nodes in the second list if needed
		while (second != null){

			//adds the node to the merged list
			result.append(second.elem());

			//goes to the second node that follows
			second = second.next();
		}

		/*
		Example:
        This linked list:   1 -> 4 -> 7
        Other:  2 -> 3 -> 8 -> 10
        Result: 1 -> 2 -> 3 -> 4 -> 7 -> 8 -> 10

        Constraints:
	    You may NOT use arrays, ArrayList, HashSet, or any other extra linear data structure.
	    You may create new nodes for the result list (using append).
	    Do NOT call a built-in sorting method.
	    The result must be sorted in increasing order.

	    Hint:
	    Use two pointers starting at the heads of the two lists.
	    Repeatedly pick the smaller current element and append it to the result, then advance that pointer.
	    When one list ends, append the remainder of the other list to the result.
		 */


		// 1.  FILL IN CODE

		// 2. Give the running time of the method (as a function of n1 and n2, sizes of two linked lists) in the README and provide an explanation.
		//returns new merged link list
		return result;
	}


}
