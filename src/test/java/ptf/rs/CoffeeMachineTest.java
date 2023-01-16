package ptf.rs;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoffeeMachineTest {
    @Test(expected = IllegalArgumentException.class)
    public void buyCoffeeWithExistingOption() {
        CoffeeMachine cm = new CoffeeMachine();
        cm.options.clear();
        cm.buy("test");
    }

    @Test(expected = IllegalStateException.class)
    public void buyCoffeeWithLessMoney() {
        CoffeeMachine cm = new CoffeeMachine();
        Coffee coffee = new Coffee("naziv", 1, 2, 3, 5.6d);
        cm.options.put("test", coffee);
        cm.setMoney(3.8d);
        cm.buy("test");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addMoneyNegativeValue() {
        CoffeeMachine cm = new CoffeeMachine();
        cm.addMoney(-4.2d);
    }

    @Test
    public void checkRefillStatus() {
        CoffeeMachine cm = new CoffeeMachine();
        cm.refill();
        assertEquals(10000, cm.getWaterAmmount());
        assertEquals(10000, cm.getCoffeeAmmount());
        assertEquals(10000, cm.getWaterAmmount());
    }

    @Test
    public void checkValidCoffeeBuy() {
        CoffeeMachine cm = new CoffeeMachine();
        Coffee coffee = new Coffee("naziv", 1, 2, 3, 5.6d);
        cm.options.put("test", coffee);
        cm.setMoney(0);
        cm.refill();
        cm.addMoney(5.6d);
        cm.buy("test");
        assertEquals(cm.getMoney(), coffee.price(), 1e-3);
    }
}