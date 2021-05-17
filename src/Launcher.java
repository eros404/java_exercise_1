public class Launcher {
    public static void main(String[] args) {
        System.out.println("|---| Welcome |---|");
        final var scanner = new java.util.Scanner(System.in);
        var input = scanner.nextLine();
        do {
            if ("fibo".equals(input)) {
                System.out.println("Veuillez entrer un nombre.");
                var number = scanner.nextInt();
                scanner.nextLine();
                System.out.println(fibo(number));
            }
            else {
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
}
