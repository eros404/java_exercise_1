public class Launcher {
    public static void main(String[] args) {
        System.out.println("|---| Welcome |---|");
        final var scanner = new java.util.Scanner(System.in);
        var input = scanner.nextLine();
        do {
            System.out.println("Unknown command");
            input = scanner.nextLine();
        } while (!"quit".equals(input));
    }
}
