import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

public class BubbleSortTest {

    @Test
    void emptyArray() {
        int[] values = {};
        new bubbleSort().bubbleSorter(values);
        assertArrayEquals(new int[] {}, values);
    }

    @Test
    void singleElement() {
        int[] values = {117};
        new bubbleSort().bubbleSorter(values);
        assertArrayEquals(new int[] {117}, values);
    }

    @Test
    void noswap() {
        int[] values = {1, 2, 3, 4};
        new bubbleSort().bubbleSorter(values);
        assertArrayEquals(new int[] {1, 2, 3, 4}, values);
    }

    @Test
    void allswap() {
        int[] values = {4, 3, 2, 1};
        new bubbleSort().bubbleSorter(values);
        assertArrayEquals(new int[] {1, 2, 3, 4}, values);
    }

    @Test
    void mixed() {
        int[] values = {3, 1, 2, 4};
        new bubbleSort().bubbleSorter(values);
        assertArrayEquals(new int[] {1, 2, 3, 4}, values);
    }

    @Test
    void duplicates() {
        int[] values = {3, 1, 5, 1};
        new bubbleSort().bubbleSorter(values);
        assertArrayEquals(new int[] {1, 1, 3, 5}, values);
    }
}
