import static org.junit.Assert.*;
import org.junit.*;

public class BookInStock_part3 {

    private BookInStock book;

    // Create a fresh BookInStock before each test
    @Before
    public void setUp() {
        book = new BookInStock("978-3-16-148410-0", 59.95);
    }

    //
    // Constructor — state verification

    // Both fields should be readable immediately after construction
    @Test
    public void testConstructor_bothFieldsInitialized() {
        BookInStock b = new BookInStock("ABC", 1.25);
        assertNotNull(b.getIsbn());
        assertTrue(b.getPrice() > 0);
    }

    // Two separate objects constructed with same args should be independent
    @Test
    public void testConstructor_twoInstancesAreIndependent() {
        BookInStock b1 = new BookInStock("X1", 10.00);
        BookInStock b2 = new BookInStock("X1", 10.00);
        b1.setIsbn("CHANGED");
        assertEquals("X1", b2.getIsbn());
    }

    // Price passed to constructor should survive a getPrice call unchanged
    @Test
    public void testConstructor_priceNotRounded() {
        BookInStock b = new BookInStock("Z99", 7.777);
        assertEquals(7.777, b.getPrice(), 0.0001);
    }

    //
    // setIsbn / getIsbn — state and sequencing

    // isbn should reflect the most recent setIsbn call
    @Test
    public void testSetIsbn_secondCallOverwritesFirst() {
        book.setIsbn("FIRST");
        book.setIsbn("SECOND");
        assertEquals("SECOND", book.getIsbn());
    }

    // setIsbn should not affect the price field
    @Test
    public void testSetIsbn_doesNotAlterPrice() {
        double priceBefore = book.getPrice();
        book.setIsbn("NEW-ISBN");
        assertEquals(priceBefore, book.getPrice(), 0.0001);
    }

    // isbn can be set to a numeric string
    @Test
    public void testSetIsbn_numericString() {
        book.setIsbn("0000000000");
        assertEquals("0000000000", book.getIsbn());
    }

    // isbn can be set to a string with hyphens and letters
    @Test
    public void testSetIsbn_alphanumericWithHyphens() {
        book.setIsbn("978-0-306-40615-7");
        assertEquals("978-0-306-40615-7", book.getIsbn());
    }

    //
    // setPrice / getPrice — state and sequencing

    // price should reflect the most recent setPrice call
    @Test
    public void testSetPrice_secondCallOverwritesFirst() {
        book.setPrice(5.00);
        book.setPrice(75.00);
        assertEquals(75.00, book.getPrice(), 0.0001);
    }

    // setPrice should not affect the isbn field
    @Test
    public void testSetPrice_doesNotAlterIsbn() {
        String isbnBefore = book.getIsbn();
        book.setPrice(20.00);
        assertEquals(isbnBefore, book.getIsbn());
    }

    // a failed setPrice (zero) should leave price unchanged
    @Test
    public void testSetPrice_priceUnchangedAfterZeroRejected() {
        double priceBefore = book.getPrice();
        try {
            book.setPrice(0.0);
        } catch (IllegalArgumentException e) {
            // expected
        }
        assertEquals(priceBefore, book.getPrice(), 0.0001);
    }

    // a failed setPrice (negative) should leave price unchanged
    @Test
    public void testSetPrice_priceUnchangedAfterNegativeRejected() {
        double priceBefore = book.getPrice();
        try {
            book.setPrice(-99.99);
        } catch (IllegalArgumentException e) {
            // expected
        }
        assertEquals(priceBefore, book.getPrice(), 0.0001);
    }

    // setPrice with a very small positive value should succeed
    @Test
    public void testSetPrice_smallestPositiveAccepted() {
        book.setPrice(0.01);
        assertEquals(0.01, book.getPrice(), 0.0001);
    }

    //
    // priceAsString — format correctness

    // result should be exactly two characters long after the dollar sign decimal portion
    @Test
    public void testPriceAsString_alwaysTwoDecimalPlaces() {
        book.setPrice(5.00);
        String result = book.priceAsString();
        int dotIndex = result.indexOf('.');
        assertNotEquals(-1, dotIndex);
        assertEquals(2, result.length() - dotIndex - 1);
    }

    // priceAsString should start with '$'
    @Test
    public void testPriceAsString_startsWithDollarSign() {
        assertEquals('$', book.priceAsString().charAt(0));
    }

    // priceAsString should match expected format for a round dollar amount
    @Test
    public void testPriceAsString_roundAmount() {
        book.setPrice(100.00);
        assertEquals("$100.00", book.priceAsString());
    }

    // priceAsString should match expected format for sub-dollar amount
    @Test
    public void testPriceAsString_subDollarAmount() {
        book.setPrice(0.75);
        assertEquals("$0.75", book.priceAsString());
    }

    // priceAsString should reflect price updated by setPrice
    @Test
    public void testPriceAsString_updatesAfterSetPrice() {
        book.setPrice(3.50);
        assertEquals("$3.50", book.priceAsString());
        book.setPrice(12.99);
        assertEquals("$12.99", book.priceAsString());
    }

    // priceAsString output length should be > 1 (at minimum "$X.YZ")
    @Test
    public void testPriceAsString_minimumLength() {
        book.setPrice(0.01);
        assertTrue(book.priceAsString().length() >= 5);
    }
}
