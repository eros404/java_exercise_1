import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Freq implements Command{
    @Override
    public String name() {
        return "freq";
    }

    @Override
    public Boolean run(Scanner scanner) {
        System.out.println("Veuillez entrer un chemin de fichier.");
        var input = scanner.nextLine();
        try {
            var path = Paths.get(input);
            var content = java.nio.file.Files.readString(path);
            System.out.println(getFreq(content));
        } catch (Exception e) {
            System.out.println("Unreadable file: " + e.getClass().getName() + " " + e.getMessage());
        }
        return true;
    }

    public String getFreq(String input) {
        var firstTreatment = input.replaceAll("[^\\w\\s]", " ").toLowerCase(Locale.ROOT).split(" ");
        var mapping = Arrays.stream(firstTreatment).filter(s -> !s.isBlank())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        var comparatorCountReversed = Map.Entry.<String, Long>comparingByValue()
                .reversed();

        return mapping.entrySet().stream().sorted(comparatorCountReversed).limit(3)
                .map(Map.Entry::getKey).collect(Collectors.joining(" "));
    }
}
