public class Launcher {
    public static void main(String[] args) {
        System.out.println("|---| Welcome |---|");
        final var scanner = new java.util.Scanner(System.in);
        final String input = scanner.nextLine();
        if (!"quit".equals(input)) {
            System.out.println("Unknown command");
        }
    }
}
