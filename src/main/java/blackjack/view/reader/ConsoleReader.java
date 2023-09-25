package blackjack.view.reader;

import java.util.Scanner;

public class ConsoleReader implements Reader {

    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        return scanner.nextLine();
    }
}
