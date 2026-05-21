// Introduction to Software Testing
// CSCI 658
// JUnit tests for FunWithNumbers: sum() and sumOfN()

import static org.junit.Assert.*;
import org.junit.*;

public class junit_testing {

    // Empty input test
    // Should return 0
    @Test
    public void tSum_emptyArray() {
        assertEquals(0, FunWithNumbers.sum(new int[]{}));
    }

    // Negative input test
    // Should return the value
    @Test
    public void tSum_singleNegative() {
        assertEquals(-5, FunWithNumbers.sum(new int[]{-5}));
    }

    // Duplicate
    // Should return the sum
    @Test
    public void tSum_duplicate() {
        assertEquals(8, FunWithNumbers.sum(new int[]{4, 4}));
    }

    // Multiple negative test
    // Should return the sum of the elements
    @Test
    public void tSum_allNegatives() {
        assertEquals(-6, FunWithNumbers.sum(new int[]{-1, -2, -3}));
    }

    
    // Zero input test
    // Should return 0
    @Test
    public void tSum_allZeros() {
        assertEquals(0, FunWithNumbers.sum(new int[]{0, 0, 0}));
    }
 

    // -------------------------------------------------------
    // Tests for sumOfN(int[] a, int n)
    // -------------------------------------------------------

    // Empty array test
    @Test
    public void tSumN_emptyArray() {
        assertFalse(FunWithNumbers.sumOfN(new int[]{}, 5));
    }

    
    // Single element test
    @Test
    public void tSumN_singleElement() {
        assertFalse(FunWithNumbers.sumOfN(new int[]{5}, 5));
    }
 
    // Compare sum equal to n
    @Test
    public void tSumN_twoPairMatch() {
        assertTrue(FunWithNumbers.sumOfN(new int[]{1, 2}, 3));
    }

    // Two elements that do NOT sum to n
    @Test
    public void tSumN_twoPairNoMatch() {
        assertFalse(FunWithNumbers.sumOfN(new int[]{1, 2}, 4));
    }

    // Duplicate positives
    @Test
    public void tSumN_pairAmongMany() {
        assertTrue(FunWithNumbers.sumOfN(new int[]{1, 5, 3, 2, 6}, 8));
    }*/

    // No pair sums in larger array
    @Test
    public void tSumN_noPairAmongMany() {
        assertFalse(FunWithNumbers.sumOfN(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 45));
    }

    // Duplicate positives
    @Test
    public void tSumN_duplicatesFormPair() {
        assertTrue(FunWithNumbers.sumOfN(new int[]{3, 3, 1, 3}, 6));
    }

    // Negative sums test
    @Test
    public void tSumN_negativesPairToN() {
        assertTrue(FunWithNumbers.sumOfN(new int[]{-1, -2, -3}, -5));
    }

    // Negative and zero test
    @Test
    public void tSumN_negativeAndPositiveSumToZero() {
        assertTrue(FunWithNumbers.sumOfN(new int[]{-1, 1, 2}, 0));
    }

}
