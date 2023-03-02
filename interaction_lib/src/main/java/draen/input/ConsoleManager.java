package draen.input;

import java.util.Scanner;

public class ConsoleManager implements IOManager {
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public void println(String str) {
        System.out.println(str);
    }

    public void print(String str) {
        System.out.print(str);
    }

    @Override
    public void displayError(Exception e) {
        displayError(e.getMessage());
   }
    @Override
    public void displayError(String str) {
        System.out.println("Error: " + str);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }


}
