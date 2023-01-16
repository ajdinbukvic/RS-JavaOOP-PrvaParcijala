package ptf.rs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class VendingMachine<T> {
    protected final Map<String, T> options = new HashMap<>();

    Collection<String> getAvailableProducts() {
        if(options.isEmpty()) throw new IllegalArgumentException("Nema unesenih opcija");
        return options.keySet().stream().toList();
    }
    void addOption (String name, T option) {
        if(options.containsKey(name)) {
            System.out.println("Opcija s tim nazivom vec postoji. Stari podaci su zamijenjeni novim.");
            options.put(name, option);
        }
    }

    public abstract T buy (String option);

    public abstract void refill();
}
