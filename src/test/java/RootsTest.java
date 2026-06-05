import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RootsTest {

    private static final double DELTA = 1e-15;

    @Test
    void qPositive_aNotZero() {
        Roots.calculate_roots(1, -3, 2);

        System.out.println("a: 1, b: -3, c: 2");
        System.out.println("Number of roots: " + Roots.num_roots() + ", First root: " + Roots.first_root() + ", Second root: " + Roots.second_root());
        
        assertEquals(2.0, Roots.num_roots(), DELTA);
        assertEquals(2.0, Roots.first_root(), DELTA);
        assertEquals(1.0, Roots.second_root(), DELTA);
    }

    @Test
    void qZero_aNotZero() {
        Roots.calculate_roots(1, 2, 1);
        System.out.println("a: 1, b: 2, c: 1");
        System.out.println("Number of roots: " + Roots.num_roots() + ", First root: " + Roots.first_root() + ", Second root: " + Roots.second_root());

        assertEquals(1.0, Roots.num_roots(), DELTA);
        assertEquals(-1.0, Roots.first_root(), DELTA);
        assertEquals(-1.0, Roots.second_root(), DELTA);
    }

    @Test
    void qNegative_aNotZero() {
        Roots.calculate_roots(1, 0, 1);
        System.out.println("a: 1, b: 0, c: 1");
        System.out.println("Number of roots: " + Roots.num_roots() + ", First root: " + Roots.first_root() + ", Second root: " + Roots.second_root());

        assertEquals(0.0, Roots.num_roots(), DELTA);
        assertEquals(-1.0, Roots.first_root(), DELTA);
        assertEquals(-1.0, Roots.second_root(), DELTA);
    }

    @Test
    void qPositive_aZero_currentCodeReturnsNoRootsSentinelValues() {
        Roots.calculate_roots(0, 2, 1);
        System.out.println("a: 0, b: 2, c: 1");
        System.out.println("Number of roots: " + Roots.num_roots() + ", First root: " + Roots.first_root() + ", Second root: " + Roots.second_root());

        assertEquals(0.0, Roots.num_roots(), DELTA);
        assertEquals(-1.0, Roots.first_root(), DELTA);
        assertEquals(-1.0, Roots.second_root(), DELTA);
    }

    @Test
    void qZero_aZero_currentCodeProducesNaNRoots() {
        Roots.calculate_roots(0, 0, 5);
        System.out.println("a: 0, b: 0, c: 5");
        System.out.println("Number of roots: " + Roots.num_roots() + ", First root: " + Roots.first_root() + ", Second root: " + Roots.second_root());

        assertEquals(1.0, Roots.num_roots(), DELTA);
        assertTrue(Double.isNaN(Roots.first_root()));
        assertTrue(Double.isNaN(Roots.second_root()));
    }
}
