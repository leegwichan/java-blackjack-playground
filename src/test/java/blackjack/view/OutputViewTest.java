package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardShape;
import blackjack.dto.card.CardDto;
import blackjack.dto.card.CardsDto;
import blackjack.dto.participants.DealerDto;
import blackjack.dto.participants.PlayerDto;
import blackjack.dto.status.StatusDto;
import blackjack.view.printer.MockPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class OutputViewTest {

    private static final String NEW_LINE = System.getProperty("line.separator");

    private final CardDto DIAMOND_3 = CardDto.of(CardShape.DIAMOND, CardLetter.THREE);
    private final CardDto DIAMOND_4 = CardDto.of(CardShape.DIAMOND, CardLetter.FOUR);
    private final CardDto HEART_2 = CardDto.of(CardShape.HEART, CardLetter.TWO);
    private final CardDto SPADE_8 = CardDto.of(CardShape.SPADE, CardLetter.EIGHT);
    private final CardDto CLOVER_7 = CardDto.of(CardShape.CLOVER, CardLetter.SEVEN);
    private final CardDto SPADE_K = CardDto.of(CardShape.SPADE, CardLetter.KING);

    private final DealerDto DEALER_DTO = DealerDto.of(19, CardsDto.of(List.of(CLOVER_7, SPADE_K)));
    private final PlayerDto POBI_DTO = PlayerDto.of("pobi", 1000, 15,
            CardsDto.of(List.of(DIAMOND_3, DIAMOND_4)));
    private final PlayerDto JASON_DTO = PlayerDto.of("jason", 2000, 20,
            CardsDto.of(List.of(HEART_2, SPADE_8)));

    private MockPrinter printer;
    private MockCardView mockCardView;
    private OutputView outputView;

    @Test
    @DisplayName("초기 딜러와 플레이어들의 카드 상황을 출력할 수 있다")
    void printInitialParticipantsCardsTest() {
        StatusDto statusDto = StatusDto.of(DEALER_DTO, List.of(POBI_DTO, JASON_DTO));
        String expectedPrintedMessage = NEW_LINE + "딜러와 pobi, jason에게 2장의 카드를 나누었습니다." + NEW_LINE
                + "딜러 카드: " + MockCardView.createMockView(1) + NEW_LINE
                + "pobi 카드: " + MockCardView.createMockView(2) + NEW_LINE
                + "jason 카드: " + MockCardView.createMockView(2) + NEW_LINE;

        outputView.printInitialParticipantsCards(statusDto);

        assertThat(printer.getPrintedMessage()).isEqualTo(expectedPrintedMessage);
    }

    @Test
    @DisplayName("각 플레이어의 카드 상황을 출력할 수 있다")
    void printPlayerCardsTest() {
        String expectedPrintedMessage = "pobi 카드: " + MockCardView.createMockView(2) + NEW_LINE;

        outputView.printPlayerCards(POBI_DTO);

        assertThat(printer.getPrintedMessage()).isEqualTo(expectedPrintedMessage);
    }

    @Test
    @DisplayName("초기 딜러와 플레이어들의 카드 상황과 점수를 출력할 수 있다")
    void printFinalResultTest() {
        StatusDto statusDto = StatusDto.of(DEALER_DTO, List.of(POBI_DTO, JASON_DTO));
        String expectedPrintedMessage = NEW_LINE
                + "딜러 카드: " + MockCardView.createMockView(2) + " - 결과 : 19" + NEW_LINE
                + "pobi 카드: " + MockCardView.createMockView(2) + " - 결과 : 15" + NEW_LINE
                + "jason 카드: " + MockCardView.createMockView(2) + " - 결과 : 20" + NEW_LINE;

        outputView.printFinalResult(statusDto);

        assertThat(printer.getPrintedMessage()).isEqualTo(expectedPrintedMessage);
    }

    @BeforeEach
    void initializeTestObject() {
        printer = new MockPrinter();
        mockCardView = new MockCardView();
        outputView = new OutputView(mockCardView, printer);
    }
}
