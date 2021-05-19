import java.util.*;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("|---| Welcome |---|");
        var commands = new ArrayList<Command>();
        commands.add(new Quit());
        commands.add(new Fibo());
        commands.add(new Freq());
        commands.add(new Predict());

        final var scanner = new java.util.Scanner(System.in);

        Boolean continueExecution = true;
        do {
            final var input = scanner.nextLine();
            var command = commands.stream().filter(x -> x.name().equals(input)).findFirst().orElse(null);
            if (command != null) {
                continueExecution = command.run(scanner);
            }
            else {
                System.out.println("Unknown command");
            }
        } while (continueExecution);
    }
}
