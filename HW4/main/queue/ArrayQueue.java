package queue;
/** A class that implements a Queue ADT using a circular array */
public class ArrayQueue implements Queue {
	private Object data[]; // the array that will store the queue
	private int head;
	private int currSize; // # of elements in the queue

	public ArrayQueue(int maxsize) {
		data = new Object[maxsize];
		head = 0;
		currSize = 0;
	}

	/** Add an element to the end of the queue, if it's not full */
	public void enqueue(Object elem) {
		if (currSize == data.length) {
			System.out.println("Queue is full: Can't add an element");
			return;
		}
		int index = (head + currSize) % data.length;
		data[index] = elem;
		currSize++;
	}

	/**
	 * Dequeues the element from the queue
	 * @return element that was dequeued
	 */
	public Object dequeue() {
		Object retval;
		if (empty()) {
			System.out.println("Queue is empty");
			return null;
		}
		retval = data[head];
		head = (head + 1) % data.length;
		currSize--;
		return retval;
	}

	public boolean empty() {
		return currSize == 0;
	}

	public String toString() {
		String result = "[";
		int tmpHead = head;
		int count = 0;
		while (count < currSize) {
			result = result + data[tmpHead];
			if (count < currSize - 1)
				result = result + ",";
			tmpHead = (tmpHead + 1) % data.length;
			count++;
		}
		result = result + "]";
		return result;
	}

}
