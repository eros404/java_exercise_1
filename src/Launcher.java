import java.util.*;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("|---| Welcome |---|");
        var commands = new ArrayList<Command>();
        commands.add(new Quit());
        commands.add(new Fibo());
        commands.add(new Freq());

        final var scanner = new java.util.Scanner(System.in);
        String input;
        Boolean continueExecution = true;
        do {
            input = scanner.nextLine();
            for (Command command : commands) {
                if (command.name().equals(input)) {
                    continueExecution = command.run(scanner);
                    break;
                } else if (command.equals(commands.get(commands.size() - 1))) {
                    System.out.println("Unknown command");
                }
            }
        } while (continueExecution);
    }
}
