package ptf.rs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final CoffeeMachine cm = new CoffeeMachine();

    public static void ucitajOpcije() {
        try (Stream<String> lines = Files.lines(Paths.get("opcije.txt"))) {
            List<Coffee> loadedItems = lines.skip(1).filter(line -> !line.isBlank())
                    .map(line -> line.split(";"))
                    .filter(line -> line.length == 5)
                    .map(element -> {
                        try {
                            return new Coffee(element[0], Integer.parseInt(element[1]), Integer.parseInt(element[2]), Integer.parseInt(element[3]), Double.parseDouble(element[4]));
                        } catch (Exception ignored) {
                            return null;
                        }
                    }).filter(Objects::nonNull)
                    .toList();
            //System.out.println(loadedItems);
            loadedItems.forEach(item -> cm.options.put(item.name(), item));




        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static int meni() {
        System.out.println("1. Unos novca u aparat");
        System.out.println("2. Vracanje novca iz aparata");
        System.out.println("3. Unos nove opcije");
        System.out.println("4. Kupi kafu");
        System.out.println("5. Ispis ukupnih vrijednosti");
        System.out.println("6. Kraj");
        while(true) {
            try {
                System.out.print("Izbor: ");
                int izbor = scanner.nextInt();
                scanner.nextLine();
                return izbor;
            } catch(Exception ignored) {
                scanner.nextLine();
            }
        }


    }
    public static void main(String[] args) {
        ucitajOpcije();
        boolean meniAktivan = true;
        while(meniAktivan) {
            switch (meni()) {
                case 1 -> {
                    System.out.print("Unesite kolicinu novca: ");
                    try {
                        cm.addMoney(scanner.nextDouble());
                    } catch(Exception e) {
                        //System.out.println(e.getMessage());
                    }
                }
                case 2 -> System.out.println("Aparat je vratio " + String.format("%.2f", cm.refund()) + " KM.");
                case 3 -> {
                    System.out.println("Unos nove opcije");
                    System.out.println("Unesite naziv: ");
                    String naziv = scanner.nextLine();
                    System.out.println("Unesite kolicinu mlijeka: ");
                    int kolicinaMlijeka = scanner.nextInt();
                    System.out.println("Unesite kolicinu vode: ");
                    int kolicinaVode = scanner.nextInt();
                    System.out.println("Unesite kolicinu kafe: ");
                    int kolicinaKafe = scanner.nextInt();
                    System.out.println("Unesite cijenu: ");
                    double cijena = scanner.nextDouble();
                    Coffee coffee = new Coffee(naziv, kolicinaMlijeka, kolicinaKafe, kolicinaVode, cijena);
                    cm.options.put(coffee.name(), coffee);
                    System.out.println("Uspjesno ste dodali novu opciju!");
                }
                case 4 -> {
                    cm.options.keySet().forEach(System.out::println);
                    String odabraniNaziv = scanner.nextLine();
                    try {
                        cm.buy(odabraniNaziv);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Uspjesno ste kupili " + odabraniNaziv + ". Uzivajte!");
                }
                case 5 -> {
                    cm.getTransactions().stream().collect(Collectors.groupingBy(t -> t.boughtProduct().name(), Collectors.summingDouble(Transaction::price)))
                            .entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).forEach(e -> System.out.println(e.getKey() + " - " + e.getValue()));
                }
                case 6 -> meniAktivan = false;
                default -> System.out.println("Pogresan izbor!");

            }
        }
    }
}