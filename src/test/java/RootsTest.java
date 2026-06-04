import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RootsTest {

    private static final double DELTA = 1e-9;

    @Test
    void aNotZero_qPositive_hasTwoRoots() {
        Roots.calculate_roots(1, -3, 2);

        assertEquals(2.0, Roots.num_roots(), DELTA);
        assertEquals(2.0, Roots.first_root(), DELTA);
        assertEquals(1.0, Roots.second_root(), DELTA);
    }

    @Test
    void aNotZero_qZero_hasOneRoot() {
        Roots.calculate_roots(1, 2, 1);

        assertEquals(1.0, Roots.num_roots(), DELTA);
        assertEquals(-1.0, Roots.first_root(), DELTA);
        assertEquals(-1.0, Roots.second_root(), DELTA);
    }

    @Test
    void aNotZero_qNegative_hasNoRoots() {
        Roots.calculate_roots(1, 0, 1);

        assertEquals(0.0, Roots.num_roots(), DELTA);
        assertEquals(-1.0, Roots.first_root(), DELTA);
        assertEquals(-1.0, Roots.second_root(), DELTA);
    }

    @Test
    void aZero_qPositive_currentCodeReturnsNoRootsSentinelValues() {
        Roots.calculate_roots(0, 2, 1);

        assertEquals(0.0, Roots.num_roots(), DELTA);
        assertEquals(-1.0, Roots.first_root(), DELTA);
        assertEquals(-1.0, Roots.second_root(), DELTA);
    }

    @Test
    void aZero_qZero_currentCodeProducesNaNRoots() {
        Roots.calculate_roots(0, 0, 5);

        assertEquals(1.0, Roots.num_roots(), DELTA);
        assertTrue(Double.isNaN(Roots.first_root()));
        assertTrue(Double.isNaN(Roots.second_root()));
    }
}
