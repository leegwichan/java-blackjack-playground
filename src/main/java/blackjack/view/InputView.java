package blackjack.view;

import static java.util.stream.Collectors.toList;

import blackjack.view.printer.Printer;
import blackjack.view.reader.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class InputView {

    private static final String PLAYER_NAMES_REQUEST = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String NAME_DELIMITER = ",";

    private final Reader reader;
    private final Printer printer;

    public InputView(Reader reader, Printer printer) {
        this.reader = Objects.requireNonNull(reader);
        this.printer = Objects.requireNonNull(printer);
    }

    public List<String> inputPlayerNames() {
        printer.print(PLAYER_NAMES_REQUEST);
        String names = reader.read();
        return divideNames(names);
    }

    private List<String> divideNames(String names) {
        return Arrays.stream(names.split(NAME_DELIMITER))
                .map(String::trim)
                .collect(toList());
    }
}
