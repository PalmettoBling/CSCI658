import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ChangeTest {

    private int[] runForCents(int cents) {
        if (cents <= 0) {
            return change.changeCalc(5.00, 5.00);
        }

        double paid = (cents / 100.0) + 0.000001;
        return change.changeCalc(paid, 0.0);
    }

    static Stream<Arguments> controlFlowPaths() {
        return Stream.of(
            // change <= 0 path
            Arguments.of(0, new int[] {0, 0, 0, 0, 0}),

            // change > 0 with change <= 100
            Arguments.of(1, new int[] {0, 0, 0, 0, 1}),
            Arguments.of(2, new int[] {0, 0, 0, 0, 2}),
            Arguments.of(6, new int[] {0, 0, 0, 1, 1}),
            Arguments.of(7, new int[] {0, 0, 0, 1, 2}),
            Arguments.of(11, new int[] {0, 0, 1, 0, 1}),
            Arguments.of(12, new int[] {0, 0, 1, 0, 2}),
            Arguments.of(16, new int[] {0, 0, 1, 1, 1}),
            Arguments.of(17, new int[] {0, 0, 1, 1, 2}),
            Arguments.of(26, new int[] {0, 1, 0, 0, 1}),
            Arguments.of(27, new int[] {0, 1, 0, 0, 2}),
            Arguments.of(31, new int[] {0, 1, 0, 1, 1}),
            Arguments.of(32, new int[] {0, 1, 0, 1, 2}),
            Arguments.of(36, new int[] {0, 1, 1, 0, 1}),
            Arguments.of(37, new int[] {0, 1, 1, 0, 2}),
            Arguments.of(41, new int[] {0, 1, 1, 1, 1}),
            Arguments.of(42, new int[] {0, 1, 1, 1, 2}),

            // change > 100
            Arguments.of(101, new int[] {1, 0, 0, 0, 1}),
            Arguments.of(102, new int[] {1, 0, 0, 0, 2}),
            Arguments.of(106, new int[] {1, 0, 0, 1, 1}),
            Arguments.of(107, new int[] {1, 0, 0, 1, 2}),
            Arguments.of(111, new int[] {1, 0, 1, 0, 1}),
            Arguments.of(112, new int[] {1, 0, 1, 0, 2}),
            Arguments.of(116, new int[] {1, 0, 1, 1, 1}),
            Arguments.of(117, new int[] {1, 0, 1, 1, 2}),
            Arguments.of(126, new int[] {1, 1, 0, 0, 1}),
            Arguments.of(127, new int[] {1, 1, 0, 0, 2}),
            Arguments.of(131, new int[] {1, 1, 0, 1, 1}),
            Arguments.of(132, new int[] {1, 1, 0, 1, 2}),
            Arguments.of(136, new int[] {1, 1, 1, 0, 1}),
            Arguments.of(137, new int[] {1, 1, 1, 0, 2}),
            Arguments.of(141, new int[] {1, 1, 1, 1, 1}),
            Arguments.of(142, new int[] {1, 1, 1, 1, 2})
        );
    }

    @ParameterizedTest
    @MethodSource("controlFlowPaths")
    void testAllControlFlowPaths(int cents, int[] expected) {
        int[] actual = runForCents(cents);
        assertArrayEquals(expected, actual);
    }
}
