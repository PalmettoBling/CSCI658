import static org.junit.Assert.*;
import org.junit.*;

public class BookInStock_testing {

    // Valid constructor input test
    @Test
    public void testConstructor_valid() {
        BookInStock book = new BookInStock("12345", 20.00);
        assertEquals("12345", book.getIsbn());
        assertEquals(20.00, book.getPrice(), 0.0001);
    }

    // Empty isbn should throw IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_emptyIsbn() {
        new BookInStock("", 20.00);
    }

    // Zero price should throw IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_zeroPrice() {
        new BookInStock("12345", 0.0);
    }

    // Negative price should throw IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_negativePrice() {
        new BookInStock("12345", -10.0);
    }

    //
    // Tests for getIsbn() and setIsbn(String isbn)

    // getIsbn should return initial isbn
    @Test
    public void testGetIsbn_returnsValue() {
        BookInStock book = new BookInStock("11111", 9.99);
        assertEquals("11111", book.getIsbn());
    }

    // setIsbn should update isbn value
    @Test
    public void testSetIsbn_updatesValue() {
        BookInStock book = new BookInStock("11111", 9.99);
        book.setIsbn("22222");
        assertEquals("22222", book.getIsbn());
    }

    // 
    // Tests for getPrice() and setPrice(double price)

    // getPrice should return initial price
    @Test
    public void testGetPrice_returnsValue() {
        BookInStock book = new BookInStock("11111", 9.99);
        assertEquals(9.99, book.getPrice(), 0.0001);
    }

    // setPrice should update price value
    @Test
    public void testSetPrice_updatesValue() {
        BookInStock book = new BookInStock("11111", 9.99);
        book.setPrice(15.50);
        assertEquals(15.50, book.getPrice(), 0.0001);
    }

    // setPrice zero should throw IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testSetPrice_zero() {
        BookInStock book = new BookInStock("11111", 9.99);
        book.setPrice(0.0);
    }

    // setPrice negative should throw IllegalArgumentException
    @Test(expected = IllegalArgumentException.class)
    public void testSetPrice_negative() {
        BookInStock book = new BookInStock("11111", 9.99);
        book.setPrice(-1.0);
    }

    //
    // Tests for priceAsString()

    // priceAsString should include dollar sign and two decimals
    @Test
    public void testPriceAsString_formatsNumber() {
        BookInStock book = new BookInStock("11111", 20.0);
        assertEquals("$20.00", book.priceAsString());
    }

    // priceAsString should include trailing zero when needed
    @Test
    public void testPriceAsString_formatsDecimalValue() {
        BookInStock book = new BookInStock("11111", 33.8);
        assertEquals("$33.80", book.priceAsString());
    }

}
