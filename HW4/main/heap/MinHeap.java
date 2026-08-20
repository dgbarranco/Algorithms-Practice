package heap;

import java.lang.classfile.ClassHierarchyResolver;

/**
 * Min-heap storing Patient objects.
 * Heap ordering (min first):
 *  1) category (ascending; 1 most urgent)
 *  2) arrivalTime (ascending)
 *  3) name (lexicographic)
 *
 *  symptom1/symptom2 are stored for triage, but do NOT affect heap ordering.
 *  category ("urgency") is assigned when the patient is triaged.
 * TODO: fill in code in the insert and pushdown methods
 */
public class MinHeap {
    private Patient[] heap;  // the array to store the heap
    private int maxsize;     // the size of the array
    private int size;        // the current number of elements in the array

    /**
     * Constructor for the min heap
     * @param max the maximum size of the heap array
     */
    public MinHeap(int max) {
        maxsize = max;
        heap = new Patient[maxsize];
        size = 0;
        // Sentinel at index 0 for easier bubble-up comparisons.
        heap[0] = Patient.sentinelMin();
    }

    private int leftChild(int pos) { return 2 * pos; }

    private int rightChild(int pos) { return 2 * pos + 1; }

    private int parent(int pos) { return pos / 2; }

    private boolean isLeaf(int pos) {
        return ((pos > size / 2) && (pos <= size));
    }

    private void swap(int pos1, int pos2) {
        Patient tmp = heap[pos1];
        heap[pos1] = heap[pos2];
        heap[pos2] = tmp;
    }

    /** Insert an element into the min heap */
    public void insert(Patient elem) {
        if (elem == null) throw new IllegalArgumentException("Cannot insert null.");
        if (size + 1 >= maxsize) throw new IllegalStateException("Heap full. maxsize=" + maxsize);

        size++;
        heap[size] = elem;
        int current = size;

        //element is traversed up the heap uniyil it is smaller than the parent
        while (current > 1 && heap[current].compareTo(heap[parent(current)]) < 0){

            //swap the vals in the heap
            swap(current, parent(current));

            //the value of the heap val of parent is assigned
            current = parent(current);
        }
        // Bubble up: compare with the parent and swap, if needed; update the current and continue bubbling up as needed.
        // FILL IN CODE:
    }

    /** Remove minimum element (top of min heap). Returns null if empty. */
    public Patient removeMin() {
        if (size == 0) return null;

        swap(1, size);
        size--;
        if (size != 0) pushdown(1);

        return heap[size + 1];
    }

    /**
     * Bubble down the element at a given position
     * @param position
     */
    private void pushdown(int position) {
        int smallestChild;
        while (!isLeaf(position)) {
            smallestChild = leftChild(position);

            //if the right child exists and it is smaller than the left child then it will update the smallestchild to be the right child
            if(rightChild(position) <= size && heap[rightChild(position)].compareTo(heap[leftChild(position)]) < 0){
                smallestChild = rightChild(position);
            }

            //using compareto method if the heap position of position is smaller than the smallest child by returning and if the value is negative or equal to 0 then the pushdown must go into the default
            if(heap[position].compareTo(heap[smallestChild]) <= 0){
                return;
            }

            //the position is swapped if the smaller child is the smallest in the heap
            swap(position, smallestChild);

            //the new heap position is assigned to be the smallestchild val
            position = smallestChild;

            // FILL IN CODE:
            // Find the smaller child (left or right) and swap with the smaller child, if needed
            // Update the position and repeat until reach a leaf.

        }
    }

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    /** Print heap contents (uses Patient.toString()). */
    public void print() {
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
