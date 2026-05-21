public class BookInStock {
    private String isbn;
    private double price;

    // Book object
    // isdn string
    // price double
    // sets, updates
    public BookInStock(String isbn, double price) {
        if (isbn.equals("")) {
            throw new IllegalArgumentException("isbn cannot be empty");
        }
        if (price <= 0.0) {
            throw new IllegalArgumentException("price must be greater than zero");
        }
        this.isbn = isbn;
        this.price = price;
    }

    // returns string
    // returns isbn of referenced object
    // getter
    public String getIsbn() {
        return isbn;
    }

    // no return
    // isdn string
    // updates
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // returns double
    // returns price of referenced object
    // getter
    public double getPrice() {
        return price;
    }

    // no return
    // price double
    // updates
    public void setPrice(double price) {
        if (price <= 0.0) {
            throw new IllegalArgumentException("price must be greater than zero");
        }
        this.price = price;
    }

    // returns string
    // price double to string conversion
    // returns converted price reference
    public String priceAsString() {
        return String.format("$%.2f", price);
    }
}
