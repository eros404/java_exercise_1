import java.util.InputMismatchException;
import java.util.Scanner;

public class Fibo implements Command{
    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public Boolean run(Scanner scanner) {
        System.out.println("Veuillez entrer un nombre.");
        try {
            var number = scanner.nextInt();
            System.out.println(computeFibo(number));
        } catch (InputMismatchException e) {
            System.out.println("Vous n'avez pas renseigné de nombre et une erreur a eu lieu.");
        }
        scanner.nextLine();
        return true;
    }

    public int computeFibo(int number) {
        if (number < 2) {
            return number;
        }
        return computeFibo(number - 1) + computeFibo(number - 2);
    }
}
