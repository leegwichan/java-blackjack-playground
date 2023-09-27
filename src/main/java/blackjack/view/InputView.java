package blackjack.view;

import static java.util.stream.Collectors.toList;

import blackjack.view.printer.Printer;
import blackjack.view.reader.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class InputView {

    private static final String PLAYER_NAMES_REQUEST = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    private static final String CARD_ADDING_REQUEST_FORMAT = "%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n";

    private static final String NAME_DELIMITER = ",";
    private static final Map<String, Boolean> MESSAGE_TO_BOOLEAN = Map.of("y", true, "n", false);

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

    public boolean isAddingCard(String playerName) {
        printer.print(String.format(CARD_ADDING_REQUEST_FORMAT, playerName));
        String message = reader.read();

        if (!MESSAGE_TO_BOOLEAN.containsKey(message)) {
            throw new IllegalArgumentException("허용된 문자만 입력할 수 있습니다");
        }
        return MESSAGE_TO_BOOLEAN.get(message);
    }
}
