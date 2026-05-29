/*
Disclaimer: I wrote these test cases for an auto-grader while new to JUnit.
They certainly don't adhere to all best practices.
 */

package edu.citadel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DequeTest {
    Deque<Integer> deque;

    @Before
    public void setUp() {
        deque = new Deque<>();
    }

    private String show() {
        String s = "";
        for (Integer i : deque)
            s += i + " ";
        return s.trim();
    }

    @Test
    public void testConstructor() {/* if setUp() doesn't crash it works */ }

    @Test
    public void testIsEmpty() {
        assertTrue("expect empty to be true for size 0", deque.empty());
    }

    @Test
    public void testIsEmptyAfterAddFirst() {
        assertTrue("expect empty to be true for size 0", deque.empty());
        deque.addFront(1);
        assertFalse("expect empty to be false after addFirst", deque.empty());
    }

    @Test
    public void testIsEmptyAfterAddFirstRemoveFirst() {
        assertTrue("expect empty to be true for size 0", deque.empty());
        deque.addFront(1);
        assertFalse("expect empty to be false after addFirst", deque.empty());
        deque.removeFront();
        assertTrue("expect empty to be true for size 0 after removeFirst", deque.empty());
    }

    @Test
    public void testSizeZero() {
        assertEquals("expect size 0 for new Deque", 0, deque.size());
    }

    @Test
    public void testSizeAfterAddFirst() {
        assertEquals("expect size 0 for new Deque", 0, deque.size());
        deque.addFront(1);
        assertEquals("expect size 1 after addFirst", 1, deque.size());
    }

    @Test
    public void testSizeAfterAddFirstRemoveFirst() {
        assertEquals("expect size 0 for new Deque", 0, deque.size());
        deque.addFront(1);
        assertEquals("expect size 1 after addFirst", 1, deque.size());
        deque.removeFront();
        assertEquals("expect size 0 after removeFirst", 0, deque.size());
    }

    @Test
    public void testAddFirst() {
        assertEquals("expect new Deque to be empty", "", show());
        deque.addFront(3);
        assertEquals("expect 1 item after 1x addFirst", "3", show());
        deque.addFront(2);
        assertEquals("expect 2 items LIFO after 2x addFirst", "2 3", show());
        deque.addFront(1);
        assertEquals("expect 3 items LIFO after 3x addFirst", "1 2 3", show());
    }

    @Test
    public void testAddLast() {
        assertEquals("expect new Deque to be empty", "", show());
        deque.addBack(1);
        assertEquals("expect 1 item after 1x addLast", "1", show());
        deque.addBack(2);
        assertEquals("expect 2 items FIFO after 2x addLast", "1 2", show());
        deque.addBack(3);
        assertEquals("expect 3 items FIFO after 3x addLast", "1 2 3", show());
    }

    @Test
    public void testRemoveFirstEmpty() {
        try {
            deque.removeFront();
            fail("expect exception to be thrown for removeFirst from empty list.");
        } catch (java.util.NoSuchElementException nsee) {
        }
    }

    @Test
    public void testRemoveFirstSingleItem() {
        deque.addFront(1);
        assertTrue("expect removeFirst of only item to match added item", deque.removeFront().equals(1));
    }

    @Test
    public void testRemoveFirstSingleItem2() {
        deque.addFront(1);
        assertTrue("expect removeFirst of only item to match added item", deque.removeFront().equals(1));
        assertEquals("expect size to decrement after removeFirst", 0, deque.size());
    }

    @Test
    public void testRemoveFirstMultiItem1() {
        deque.addFront(3);
        deque.addFront(2);
        deque.addFront(1);
        assertTrue("expect removeFirst to remove first of multiple items", deque.removeFront().equals(1));
    }

    @Test
    public void testRemoveFirstMultiItem2() {
        deque.addFront(3);
        deque.addFront(2);
        deque.addFront(1);
        assertTrue("expect removeFirst to remove first of multiple items", deque.removeFront().equals(1));
        assertEquals("expect size to decrement after removeFirst", 2, deque.size());
    }

    @Test
    public void testRemoveFirstMultiItem3() {
        deque.addFront(3);
        deque.addFront(2);
        deque.addFront(1);
        assertTrue("expect removeFirst to remove first of multiple items", deque.removeFront().equals(1));
        assertTrue("expect removeFirst to remove first of multiple items", deque.removeFront().equals(2));
    }

    @Test
    public void testRemoveFirstMultiItem4() {
        deque.addFront(3);
        deque.addFront(2);
        deque.addFront(1);
        assertTrue("expect removeFirst to remove first of multiple items", deque.removeFront().equals(1));
        assertTrue("expect removeFirst to remove first of multiple items", deque.removeFront().equals(2));
        assertTrue("expect removeFirst to remove first of multiple items", deque.removeFront().equals(3));
    }

    @Test
    public void testRemoveFirstMultiItem5() {
        deque.addFront(3);
        deque.addFront(2);
        deque.addFront(1);
        assertTrue("expect removeFirst to remove first of multiple items", deque.removeFront().equals(1));
        assertTrue("expect removeFirst to remove first of multiple items", deque.removeFront().equals(2));
        assertTrue("expect removeFirst to remove first of multiple items", deque.removeFront().equals(3));
        assertEquals("expect size to decrement after removeFirst", 0, deque.size());
    }

    @Test
    public void testRemoveLastEmpty() {
        try {
            deque.removeBack();
            fail("expect exception to be thrown for removeLast from empty list.");
        } catch (java.util.NoSuchElementException nsee) {
        }
    }

    @Test
    public void testRemoveLastSingleItem1() {
        deque.addFront(1);
        assertTrue("expect removeLast of only item to match added item", deque.removeBack().equals(1));
    }

    @Test
    public void testRemoveLastSingleItem2() {
        deque.addFront(1);
        assertTrue("expect removeLast of only item to match added item", deque.removeBack().equals(1));
        assertEquals("expect size to decrement after removeLast", 0, deque.size());
    }

    @Test
    public void testRemoveLastMultiItem1() {
        deque.addFront(1);
        deque.addFront(2);
        deque.addFront(3);
        assertTrue("expect removeLast to remove Last of multiple items", deque.removeBack().equals(1));
    }

    @Test
    public void testRemoveLastMultiItem2() {
        deque.addFront(1);
        deque.addFront(2);
        deque.addFront(3);
        assertTrue("expect removeLast to remove Last of multiple items", deque.removeBack().equals(1));
        assertEquals("expect size to decrement after removeLast", 2, deque.size());
    }

    @Test
    public void testRemoveLastMultiItem3() {
        deque.addFront(1);
        deque.addFront(2);
        deque.addFront(3);
        assertTrue("expect removeLast to remove Last of multiple items", deque.removeBack().equals(1));
        assertTrue("expect removeLast to remove Last of multiple items", deque.removeBack().equals(2));
    }

    @Test
    public void testRemoveLastMultiItem4() {
        deque.addFront(1);
        deque.addFront(2);
        deque.addFront(3);
        assertTrue("expect removeLast to remove Last of multiple items", deque.removeBack().equals(1));
        assertTrue("expect removeLast to remove Last of multiple items", deque.removeBack().equals(2));
        assertTrue("expect removeLast to remove Last of multiple items", deque.removeBack().equals(3));
    }

    @Test
    public void testRemoveLastMultiItem5() {
        deque.addFront(1);
        deque.addFront(2);
        deque.addFront(3);
        assertTrue("expect removeLast to remove Last of multiple items", deque.removeBack().equals(1));
        assertTrue("expect removeLast to remove Last of multiple items", deque.removeBack().equals(2));
        assertTrue("expect removeLast to remove Last of multiple items", deque.removeBack().equals(3));
        assertEquals("expect size to decrement after removeLast", 0, deque.size());
    }

    @Test
    public void testIteratorMethod() {
        assertNotNull("expect iterator method to return non-null", deque.iterator());
    }

    @Test
    public void testIteratorRemoveException() {
        try {
            deque.iterator().remove();
            fail("expect exception to be thrown for removeLast from empty list.");
        } catch (java.lang.UnsupportedOperationException uoe) {
        }

    }

    @Test
    public void testIteratorMultipleItems() {
        deque.addFront(1);
        deque.addFront(2);
        deque.addFront(3);
        assertEquals("expect iterator to go front to back", "3 2 1", show());
    }

    @Test
    public void testIndependentIterators() {
        deque.addFront(3);
        deque.addFront(2);
        deque.addFront(1);
        String s = "";
        for (Integer i : deque) {
            for (Integer j : deque) {
                s += i + " " + j + " ";
            }
        }
        assertEquals("expect nested iterators to report independently",
                "1 1 1 2 1 3 2 1 2 2 2 3 3 1 3 2 3 3", s.trim());
    }

    @Test
    public void testRemoveSecondTooSmall() {
        try {
            deque.removeSecond();
            fail("expect exception for removeSecond on empty deque.");
        } catch (java.util.NoSuchElementException nsee) {
        }
    }

    @Test
    public void testRemoveSecondSingleItem() {
        deque.addFront(1);
        try {
            deque.removeSecond();
            fail("expect exception for removeSecond less than two sized deque.");
        } catch (java.util.NoSuchElementException nsee) {
        }
    }

    @Test
    public void testREmoveSecond() {
        deque.addFront(1);
        deque.addFront(2);
        deque.addFront(3);
        assertTrue("expect removeSecond to remove second item", deque.removeSecond().equals(2));
        assertEquals("expect size to decrement after removeSecond", 2, deque.size());
    }
}
