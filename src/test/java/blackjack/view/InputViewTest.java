package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.view.printer.MockPrinter;
import blackjack.view.reader.MockReader;
import blackjack.view.reader.Reader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.List;

class InputViewTest {

    @Nested
    @DisplayName("플레이어 이름 입력 테스트")
    class PlayerNamesTest {

        @Test
        @DisplayName("플레이어 이름 입력 요청 메세지가 출력된다")
        void inputPlayerNamesTest_assertThatRequestMessagePrinted() {
            MockPrinter printer = new MockPrinter();
            InputView inputView = new InputView(new MockReader(), printer);
            String expectedPrintedMessage = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

            inputView.inputPlayerNames();

            assertThat(printer.getPrintedMessage()).isEqualTo(expectedPrintedMessage);
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
}
