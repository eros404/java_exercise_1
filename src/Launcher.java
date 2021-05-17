import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("|---| Welcome |---|");
        final var scanner = new java.util.Scanner(System.in);
        var input = scanner.nextLine();
        do {
            if ("fibo".equals(input)) {
                System.out.println("Veuillez entrer un nombre.");
                try {
                    var number = scanner.nextInt();
                    System.out.println(fibo(number));
                } catch (InputMismatchException e) {
                    System.out.println("Vous n'avez pas renseigné de nombre et une erreur a eu lieu.");
                }
                scanner.nextLine();
            } else if ("freq".equals(input)) {
                System.out.println("Veuillez entrer un chemin de fichier.");
                input = scanner.nextLine();
                try {
                    var path = Paths.get(input);
                    var content = java.nio.file.Files.readString(path);
                    System.out.println(freq(content));
                } catch (Exception e) {
                    System.out.println("Unreadable file: " + e.getClass().getName() + " " + e.getMessage());
                }
            } else {
                System.out.println("Unknown command");
            }
            input = scanner.nextLine();
        } while (!"quit".equals(input));
    }

    public static int fibo(int number) {
        if (number < 2) {
            return number;
        }
        return fibo(number - 1) + fibo(number - 2);
    }

    public static String freq(String input) {
        var firstTreatment = input.replaceAll("[^\\w\\s]", " ").toLowerCase(Locale.ROOT).split(" ");
        var mapping = Arrays.stream(firstTreatment).filter(s -> !s.isBlank())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        var comparatorCountReversed = Map.Entry.<String, Long>comparingByValue()
                .reversed();

        return mapping.entrySet().stream().sorted(comparatorCountReversed).limit(3)
                .map(Map.Entry::getKey).collect(Collectors.joining(" "));
    }
}
