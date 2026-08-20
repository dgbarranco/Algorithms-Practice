package linkedlists;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkedListTest {

    private LinkedList listOf(int... vals) {
        LinkedList list = new LinkedList();
        for (int v : vals) list.append(v);
        return list;
    }

    /* ---------- removeAtIndex ---------- */
    @Test
    public void testRemoveAtIndexMiddle() {
        LinkedList list = listOf(5, 7, 3, 4);
        list.removeAtIndex(2);
        assertEquals("5 7 4 ", list.toString());
    }

    @Test
    public void testRemoveAtIndexHead() {
        LinkedList list = listOf(5, 7, 3, 4);
        list.removeAtIndex(0);
        assertEquals("7 3 4 ", list.toString());
    }

    @Test
    public void testRemoveAtIndexTail() {
        LinkedList list = listOf(5, 7, 3, 4);
        list.removeAtIndex(3);
        assertEquals("5 7 3 ", list.toString());
    }

    @Test
    public void testRemoveAtIndexSingleElement() {
        LinkedList list = listOf(9);
        list.removeAtIndex(0);
        assertEquals("", list.toString());
    }

    @Test
    public void testRemoveAtIndexInvalidNegativeThrows() {
        LinkedList list = listOf(1, 2, 3);
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(-1));
    }

    @Test
    public void testRemoveAtIndexInvalidTooLargeThrows() {
        LinkedList list = listOf(1, 2, 3);
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAtIndex(3));
    }

    /* ---------- hasCycle ---------- */
    @Test
    public void testHasCycleFalseLinearList() {
        LinkedList list = listOf(1, 2, 3);
        assertFalse(list.hasCycle());
    }

    @Test
    public void testHasCycleTrueTailToSecond() {
        LinkedList list = listOf(1, 2, 3);

        Node head = list.head();
        Node tail = list.tail();
        tail.setNext(head.next()); // create cycle: 3 -> 2

        assertTrue(list.hasCycle());
    }

    @Test
    public void testHasCycleTrueSelfCycle() {
        LinkedList list = listOf(1);

        Node head = list.head();
        head.setNext(head); // 1 -> 1

        assertTrue(list.hasCycle());
    }

    @Test
    public void testHasCycleEmptyListFalse() {
        LinkedList list = new LinkedList();
        assertFalse(list.hasCycle());
    }

    /* ---------- reverse ---------- */
    @Test
    public void testReverseTypical() {
        LinkedList list = listOf(4, 12, 18, 1);
        list.reverse();
        assertEquals("1 18 12 4 ", list.toString());
    }

    @Test
    public void testReverseSingle() {
        LinkedList list = listOf(42);
        list.reverse();
        assertEquals("42 ", list.toString());
    }

    @Test
    public void testReverseEmpty() {
        LinkedList list = new LinkedList();
        list.reverse();
        assertEquals("", list.toString());
    }

    /* ---------- mergeSorted ---------- */
    @Test
    public void testMergeSortedTypical() {
        LinkedList a = listOf(1, 4, 7);
        LinkedList b = listOf(2, 3, 8, 10);

        LinkedList merged = a.mergeSorted(b);
        assertEquals("1 2 3 4 7 8 10 ", merged.toString());
    }

    @Test
    public void testMergeSortedOneEmpty() {
        LinkedList a = new LinkedList();
        LinkedList b = listOf(2, 5);

        LinkedList merged = a.mergeSorted(b);
        assertEquals("2 5 ", merged.toString());
    }

    @Test
    public void testMergeSortedBothEmpty() {
        LinkedList a = new LinkedList();
        LinkedList b = new LinkedList();

        LinkedList merged = a.mergeSorted(b);
        assertEquals("", merged.toString());
    }

    @Test
    public void testMergeSortedWithDuplicates() {
        LinkedList a = listOf(1, 3, 3, 7);
        LinkedList b = listOf(3, 4);

        LinkedList merged = a.mergeSorted(b);
        assertEquals("1 3 3 3 4 7 ", merged.toString());
    }
}
