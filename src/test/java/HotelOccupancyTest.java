import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class HotelOccupancyTest {

    private String runWithProvidedInput(String input) {
        InputStream originalIn = System.in;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            return runCalcRateAndCaptureOutput();
        } finally {
            System.setIn(originalIn);
        }
    }

    private String runCalcRateAndCaptureOutput() {
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        try {
            System.setOut(new PrintStream(testOut));
            hotelOccupancy hotel = new hotelOccupancy();
            hotel.calcRate();
            return testOut.toString();
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void testCase1AllValidMixedValues() {
        String input = "15\n18\n20\n10\n5\n8\n";
        String output = runWithProvidedInput(input);

        assertTrue(output.contains("The hotel has a total of 120 suites"));
        assertTrue(output.contains("76 are currently occupied"));
    }

    @Test
    void testCase2BoundaryValues() {
        String input = "0\n20\n1\n19\n10\n10\n";
        String output = runWithProvidedInput(input);

        assertTrue(output.contains("The hotel has a total of 120 suites"));
        assertTrue(output.contains("60 are currently occupied"));
    }

    @Test
    void testCase3Floor13Skipped() {
        String input = "5\n10\n15\n8\n12\n20\n";
        String output = runWithProvidedInput(input);

        assertTrue(output.contains("Floor 10"));
        assertTrue(output.contains("Floor 12"));
        assertFalse(output.contains("Floor 13"));
        assertTrue(output.contains("Floor 14"));
        assertTrue(output.contains("70 are currently occupied"));
    }

    @Test
    void testCase4NegativeInputRetryValidation() {
        String input = "-5\n10\n0\n0\n0\n0\n0\n";
        String output = runWithProvidedInput(input);

        assertTrue(output.contains("must be between 0 and 20"));
        assertTrue(output.contains("10 are currently occupied"));
    }

    @Test
    void testCase5OverMaximumInputRetryValidation() {
        String input = "25\n15\n0\n0\n0\n0\n0\n";
        String output = runWithProvidedInput(input);

        assertTrue(output.contains("must be between 0 and 20"));
        assertTrue(output.contains("15 are currently occupied"));
    }

    @Test
    void testCase6MultipleRetriesThenValidInput() {
        String input = "30\n25\n15\n0\n0\n0\n0\n0\n";
        String output = runWithProvidedInput(input);

        assertTrue(output.contains("must be between 0 and 20"));
        assertTrue(output.contains("15 are currently occupied"));
    }
}
