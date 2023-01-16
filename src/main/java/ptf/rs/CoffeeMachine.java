package ptf.rs;

import java.util.ArrayList;
import java.util.List;

public class CoffeeMachine extends VendingMachine<Coffee> implements CoinOperated {
    private double money = 0;
    private int milkAmmount = 0;
    private int coffeeAmmount = 0;
    private int waterAmmount = 0;
    private final List<Transaction> transactions = new ArrayList<>();

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getMilkAmmount() {
        return milkAmmount;
    }

    public void setMilkAmmount(int milkAmmount) {
        this.milkAmmount = milkAmmount;
    }

    public int getCoffeeAmmount() {
        return coffeeAmmount;
    }

    public void setCoffeeAmmount(int coffeeAmmount) {
        this.coffeeAmmount = coffeeAmmount;
    }

    public int getWaterAmmount() {
        return waterAmmount;
    }

    public void setWaterAmmount(int waterAmmount) {
        this.waterAmmount = waterAmmount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public Coffee buy(String option) {
        if(!options.containsKey(option)) throw new IllegalArgumentException("Opcija " + option + " nije dostupna");
        Coffee coffee = options.get(option);
        if(getMoney() < coffee.price()) throw new IllegalStateException("Niste ubacili dovoljno novca");
        if(getCoffeeAmmount() < coffee.requiredCoffee() || getMilkAmmount() < coffee.requiredMilk() || getWaterAmmount() < coffee.requiredMilk()) throw new IllegalStateException("Potrebno je napuniti aparat");
        transactions.add(new Transaction(coffee, coffee.price()));
        milkAmmount -= coffee.requiredMilk();
        coffeeAmmount -= coffee.requiredCoffee();
        waterAmmount -= coffee.requiredCoffee();
        //money += coffee.price();
        return coffee;
    }

    @Override
    public void refill() {
        setCoffeeAmmount(10000);
        setMilkAmmount(10000);
        setWaterAmmount(10000);
    }

    @Override
    public void addMoney(double value) {
        if(value <= 0) throw new IllegalArgumentException("Vrijednost novca mora biti pozitivna");
        money += value;
    }

    @Override
    public double refund() {
        return money;
    }
}
