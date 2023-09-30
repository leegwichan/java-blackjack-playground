package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.view.printer.MockPrinter;
import blackjack.view.reader.MockReader;
import blackjack.view.reader.Reader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

class InputViewTest {

    private static final String NEW_LINE = System.getProperty("line.separator");

    @Nested
    @DisplayName("플레이어 이름 입력 테스트")
    class PlayerNamesTest {

        private static final String DEFAULT_NAMES = "steve,pobi";

        @Test
        @DisplayName("플레이어 이름 입력 요청 메세지가 출력된다")
        void inputPlayerNamesTest_assertThatRequestMessagePrinted() {
            MockPrinter printer = new MockPrinter();
            InputView inputView = new InputView(new MockReader(DEFAULT_NAMES), printer);
            String expectedPrintedMessage = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

            inputView.inputPlayerNames();

            assertThat(printer.getPrintedMessage()).isEqualTo(expectedPrintedMessage);
        }


        @ParameterizedTest(name = "입력 메세지 : {0}")
        @CsvSource(value = {"''", "' '", "steve,,jason", " ,steve"}, delimiter = '_')
        @DisplayName("플레이어의 이름은 빈칸이 아니어야 한다")
        void inputPlayerNamesTest_whenNameIsBlank_throwException(String message) {
            InputView inputView = new InputView(new MockReader(message), new MockPrinter());

            assertThatThrownBy(() -> inputView.inputPlayerNames())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 빈칸이 아니어야 합니다");
        }

        @Test
        @DisplayName("플레이어 이름을 쉼표 기준으로 분리할 수 있다")
        void inputPlayerNamesTest_assertThatDividedByComa() {
            String inputMessage = "pobi,jason,steve,chan";
            Reader reader = new MockReader(inputMessage);
            InputView inputView = new InputView(reader, new MockPrinter());
            List<String> expected = List.of("pobi", "jason", "steve", "chan");

            List<String> actual = inputView.inputPlayerNames();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("플레이어 이름을 쉼표 기준으로 분리 후 앞뒤 빈칸을 제거한다")
        void inputPlayerNamesTest_assertThatNameNotHaveFrontOrBackBlank() {
            String inputMessage = " pobi , jason, steve  , chan   ";
            Reader reader = new MockReader(inputMessage);
            InputView inputView = new InputView(reader, new MockPrinter());
            List<String> expected = List.of("pobi", "jason", "steve", "chan");

            List<String> actual = inputView.inputPlayerNames();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("베팅 금액 입력 테스트")
    class BettingAmountTest {

        private static final String DEFAULT_INPUT = "1000";
        private static final String DEFAULT_NAME = "steve";

        @ParameterizedTest(name = "입력 메세지 : {0}")
        @CsvSource(value = {"''", "' '", "null"}, nullValues = "null")
        @DisplayName("사람 이름은 Blank가 아니어야 한다")
        void inputBettingAmountTest_whenNameIsBlank_throwException(String name) {
            InputView inputView = new InputView(new MockReader(DEFAULT_INPUT), new MockPrinter());

            assertThatThrownBy(() -> inputView.inputBettingAmount(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 빈칸이 아니어야 합니다");
        }

        @ParameterizedTest(name = "입력 메세지 : {0}")
        @CsvSource({"300가", "삼백", "0", "-1"})
        @DisplayName("베팅 금액은 양수로 입력하여야 한다")
        void inputBettingAmountTest_whenInputNotPositiveInteger_throwException(String message) {
            InputView inputView = new InputView(new MockReader(message), new MockPrinter());

            assertThatThrownBy(() -> inputView.inputBettingAmount(DEFAULT_NAME))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("양수를 입력해야 합니다");
        }

        @ParameterizedTest(name = "이름 : {0}")
        @CsvSource({"steve", "pobi", "jason"})
        @DisplayName("베팅 금액 입력 요청 메세지를 출력한다")
        void inputBettingAmountTest_assertThatPrintRequestMessage(String name) {
            MockPrinter printer = new MockPrinter();
            InputView inputView = new InputView(new MockReader(DEFAULT_INPUT), printer);
            String expectedPrintedMessage = NEW_LINE + name + "의 배팅 금액은?" + NEW_LINE;

            inputView.inputBettingAmount(name);

            assertThat(printer.getPrintedMessage()).isEqualTo(expectedPrintedMessage);
        }

        @ParameterizedTest(name = "입력 메세지 : {0}")
        @CsvSource({"1", "1000", "3456"})
        @DisplayName("입력받은 베팅 금액을 반환한다")
        void inputBettingAmountTest_whenInputPositiveInteger(String message) {
            InputView inputView = new InputView(new MockReader(message), new MockPrinter());
            int expected = Integer.parseInt(message);

            int actual = inputView.inputBettingAmount(DEFAULT_NAME);

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("카드 추가 여부 입력 테스트")
    class AddingCardTest {

        private static final String INPUT_YES = "y";
        private static final String INPUT_NO = "n";

        @Test
        @DisplayName("카드 추가 여부에 대한 입력 요청 메세지를 출력할 수 있다")
        void isAddingCardTest_assertThatPrintRequestMessage() {
            MockPrinter printer = new MockPrinter();
            InputView inputView = new InputView(new MockReader(INPUT_YES), printer);
            String name = "playerName";
            String expectedPrintedMessage = NEW_LINE + name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + NEW_LINE;

            inputView.isAddingCard(name);

            assertThat(printer.getPrintedMessage()).isEqualTo(expectedPrintedMessage);
        }

        @ParameterizedTest(name = "{0}으로 입력하면 {1}을 반환한다")
        @CsvSource({INPUT_YES + ",true", INPUT_NO + ",false"})
        @DisplayName("정해진 입력 메세지에 따라 카드 추가 여부를 판단한다")
        void isAddingCardTest_assertThatJudgeByMessage(String inputMessage, boolean expected) {
            Reader reader = new MockReader(inputMessage);
            InputView inputView = new InputView(reader, new MockPrinter());
            String name = "playerName";

            boolean actual = inputView.isAddingCard(name);

            assertThat(actual).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvSource({"Y", "N", "yes", "x"})
        @DisplayName("입력한 문자가 허용된 문자가 아닌 경우, 예외를 던진다")
        void isAddingCardTest_whenInputAnotherMessage_throwException(String inputMessage) {
            Reader reader = new MockReader(inputMessage);
            InputView inputView = new InputView(reader, new MockPrinter());
            String name = "playerName";

            assertThatThrownBy(() -> inputView.isAddingCard(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("허용된 문자만 입력할 수 있습니다");
        }
    }
}
