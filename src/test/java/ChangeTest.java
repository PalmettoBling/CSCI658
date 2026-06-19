import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChangeTest {

    // Verifies the exact denomination breakdown for representative and edge inputs.
    @ParameterizedTest(name = "{index}: paid={0}, cost={1}")
    @MethodSource("exactBreakdownCases")
    void returnsExpectedBreakdownForCommonAndBoundaryCases(double paid, double cost, int[] expected) {
        assertArrayEquals(expected, change.changeCalc(paid, cost));
    }

    // Confirms that equal or insufficient payment produces no change at all.
    @ParameterizedTest(name = "{index}: paid={0}, cost={1}")
    @MethodSource("nonPositiveCases")
    void returnsAllZerosWhenChangeIsNotPositive(double paid, double cost) {
        assertArrayEquals(new int[] {0, 0, 0, 0, 0}, change.changeCalc(paid, cost));
    }

    // Rebuilds the total cent value from the returned buckets to catch arithmetic mutations.
    @ParameterizedTest(name = "{index}: paid={0}, cost={1}")
    @MethodSource("exactBreakdownCases")
    void reconstructedCentsMatchesTruncatedComputation(double paid, double cost, int[] expectedIgnored) {
        int[] result = change.changeCalc(paid, cost);
        int reconstructed = result[0] * 100 + result[1] * 25 + result[2] * 10 + result[3] * 5 + result[4];
        int truncatedCents = (int) ((paid - cost) * 100.0);

        assertEquals(truncatedCents, reconstructed);
    }

    // Ensures each denomination count stays within the valid remainder range after larger coins are removed.
    @ParameterizedTest(name = "{index}: paid={0}, cost={1}")
    @MethodSource("exactBreakdownCases")
    void denominationBucketsStayWithinValidRanges(double paid, double cost, int[] expectedIgnored) {
        int[] result = change.changeCalc(paid, cost);

        assertTrue(result[0] >= 0);
        assertTrue(result[1] >= 0 && result[1] <= 3);
        assertTrue(result[2] >= 0 && result[2] <= 2);
        assertTrue(result[3] >= 0 && result[3] <= 1);
        assertTrue(result[4] >= 0 && result[4] <= 4);
    }

    // Exercises the console entry point end-to-end, including prompt order and printed denomination lines.
    @Test
    void mainReadsInputInCostThenPaidOrderAndPrintsBreakdownLines() {
        String input = "18.41\n20.00\n";
        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputBuffer = new ByteArrayOutputStream();

        java.io.InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            System.setIn(testInput);
            System.setOut(new PrintStream(outputBuffer, true));
            change.main(new String[0]);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        String[] lines = new String(outputBuffer.toByteArray(), StandardCharsets.UTF_8)
                .replace("\r\n", "\n")
                .split("\n");

        assertEquals("Please enter the cost:", lines[0]);
        assertEquals("Please enter the paid amount:", lines[1]);
        assertEquals("1", lines[2]);
        assertEquals("2", lines[3]);
        assertEquals("0", lines[4]);
        assertEquals("1", lines[5]);
        assertEquals("4", lines[6]);
    }

    // Includes standard cases plus small-cent boundaries that expose the method's current truncation behavior.
    private static Stream<Arguments> exactBreakdownCases() {
        return Stream.of(
                Arguments.of(20.00, 18.41, new int[] {1, 2, 0, 1, 4}),
                Arguments.of(1.41, 0.00, new int[] {1, 1, 1, 1, 1}),
                Arguments.of(5.00, 0.00, new int[] {5, 0, 0, 0, 0}),
                Arguments.of(1.00, 0.75, new int[] {0, 1, 0, 0, 0}),
                Arguments.of(1.10, 1.00, new int[] {0, 0, 1, 0, 0}),
                Arguments.of(2.05, 2.00, new int[] {0, 0, 0, 0, 4}),
                Arguments.of(1.04, 1.00, new int[] {0, 0, 0, 0, 4}),
                Arguments.of(3.99, 2.00, new int[] {1, 3, 2, 0, 4}),
                Arguments.of(100.00, 0.01, new int[] {99, 3, 2, 0, 4}),
                Arguments.of(10.37, 7.01, new int[] {3, 1, 1, 0, 0}),
                Arguments.of(6.88, 4.12, new int[] {2, 3, 0, 0, 1}),
                Arguments.of(8.13, 5.63, new int[] {2, 2, 0, 0, 0}),
                Arguments.of(12.34, 11.33, new int[] {1, 0, 0, 0, 0}),
                Arguments.of(2.01, 2.00, new int[] {0, 0, 0, 0, 0}),
                Arguments.of(2.02, 2.00, new int[] {0, 0, 0, 0, 2}));
    }

    // These inputs all avoid the change-making branch and should return zero counts.
    private static Stream<Arguments> nonPositiveCases() {
        return Stream.of(
                Arguments.of(10.00, 10.00),
                Arguments.of(9.99, 10.00),
                Arguments.of(0.00, 0.00),
                Arguments.of(-1.00, 1.00));
    }
}
