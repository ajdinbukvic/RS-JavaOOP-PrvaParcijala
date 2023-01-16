package ptf.rs;

import java.util.Objects;

public record Coffee(String name, int requiredMilk, int requiredCoffee, int requiredWater, double price) {
    public Coffee(String name, int requiredMilk, int requiredCoffee, int requiredWater, double price) {
        this.name = name;
        this.requiredMilk = requiredMilk;
        this.requiredCoffee = requiredCoffee;
        this.requiredWater = requiredWater;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coffee coffee)) return false;
        return Objects.equals(name, coffee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "name='" + name + '\'' +
                ", requiredMilk=" + requiredMilk +
                ", requiredCoffee=" + requiredCoffee +
                ", requiredWater=" + requiredWater +
                ", price=" + price +
                '}';
    }
}
