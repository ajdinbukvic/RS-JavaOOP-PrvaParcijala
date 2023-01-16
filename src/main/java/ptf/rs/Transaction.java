package ptf.rs;

import java.util.Objects;

public record Transaction(Coffee boughtProduct, double price) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Double.compare(that.price, price) == 0 && Objects.equals(boughtProduct, that.boughtProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boughtProduct, price);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "boughtProduct=" + boughtProduct +
                ", price=" + price +
                '}';
    }
}
