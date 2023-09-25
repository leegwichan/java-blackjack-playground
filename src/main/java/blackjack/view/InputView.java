package blackjack.view;

import blackjack.view.printer.Printer;
import blackjack.view.reader.Reader;
import java.util.Objects;

public class InputView {

    private final Reader reader;
    private final Printer printer;

    public InputView(Reader reader, Printer printer) {
        this.reader = Objects.requireNonNull(reader);
        this.printer = Objects.requireNonNull(printer);
    }
}
