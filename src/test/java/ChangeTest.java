import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class ChangeTest {

    // Adds a tiny epsilon so floating-point conversion does not truncate below the target cent value.
    private int[] runForCents(int cents) {
        double paid = (cents / 100.0) + 0.000001;
        return change.changeCalc(paid, 0.0);
    }

    @Test
    void testChangeEqualsZero() {
        int[] actual = change.changeCalc(5.00, 5.00);
        assertArrayEquals(new int[] {0, 0, 0, 0, 0}, actual);
    }

    @Test
    void testChangeGreaterThanZeroEntryNode() {
        int[] actual = runForCents(1);
        assertArrayEquals(new int[] {0, 0, 0, 0, 1}, actual);
    }

    @Test
    void testChangeGreaterThan100Node() {
        int[] actual = runForCents(101);
        assertArrayEquals(new int[] {1, 0, 0, 0, 1}, actual);
    }

    @Test
    void testChangeGreaterThan25Node() {
        int[] actual = runForCents(26);
        assertArrayEquals(new int[] {0, 1, 0, 0, 1}, actual);
    }

    @Test
    void testChangeGreaterThan10Node() {
        int[] actual = runForCents(11);
        assertArrayEquals(new int[] {0, 0, 1, 0, 1}, actual);
    }

    @Test
    void testChangeGreaterThan5Node() {
        int[] actual = runForCents(6);
        assertArrayEquals(new int[] {0, 0, 0, 1, 1}, actual);
    }

    @Test
    void testChangeGreaterThan1Node() {
        int[] actual = runForCents(2);
        assertArrayEquals(new int[] {0, 0, 0, 0, 2}, actual);
    }

    @Test
    void testNegativeChangeSkipsBranch() {
        int[] actual = change.changeCalc(4.00, 5.00);
        assertArrayEquals(new int[] {0, 0, 0, 0, 0}, actual);
    }
}
