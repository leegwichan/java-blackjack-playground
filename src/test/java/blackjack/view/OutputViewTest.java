package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardShape;
import blackjack.dto.card.CardDto;
import blackjack.dto.card.CardsDto;
import blackjack.dto.participants.DealerCardDto;
import blackjack.dto.participants.PlayerCardDto;
import blackjack.dto.participants.PlayerProfitDto;
import blackjack.dto.status.CardStatusDto;
import blackjack.dto.status.ProfitStatusDto;
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

    private final DealerCardDto DEALER_DTO = DealerCardDto.of(19, CardsDto.of(List.of(CLOVER_7, SPADE_K)));
    private final PlayerCardDto POBI_DTO = PlayerCardDto.of("pobi", 15, CardsDto.of(List.of(DIAMOND_3, DIAMOND_4)));
    private final PlayerCardDto JASON_DTO = PlayerCardDto.of("jason", 20, CardsDto.of(List.of(HEART_2, SPADE_8)));

    private MockPrinter printer;
    private MockCardView mockCardView;
    private OutputView outputView;

    @Test
    @DisplayName("초기 딜러와 플레이어들의 카드 상황을 출력할 수 있다")
    void printInitialParticipantsCardsTest() {
        CardStatusDto cardStatusDto = CardStatusDto.of(DEALER_DTO, List.of(POBI_DTO, JASON_DTO));
        String expectedPrintedMessage = NEW_LINE + "딜러와 pobi, jason에게 2장의 카드를 나누었습니다." + NEW_LINE
                + "딜러 카드: " + MockCardView.createMockView(1) + NEW_LINE
                + "pobi 카드: " + MockCardView.createMockView(2) + NEW_LINE
                + "jason 카드: " + MockCardView.createMockView(2) + NEW_LINE;

        outputView.printInitialParticipantsCards(cardStatusDto);

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
        CardStatusDto cardStatusDto = CardStatusDto.of(DEALER_DTO, List.of(POBI_DTO, JASON_DTO));
        String expectedPrintedMessage = NEW_LINE
                + "딜러 카드: " + MockCardView.createMockView(2) + " - 결과 : 19" + NEW_LINE
                + "pobi 카드: " + MockCardView.createMockView(2) + " - 결과 : 15" + NEW_LINE
                + "jason 카드: " + MockCardView.createMockView(2) + " - 결과 : 20" + NEW_LINE;

        outputView.printFinalResult(cardStatusDto);

        assertThat(printer.getPrintedMessage()).isEqualTo(expectedPrintedMessage);
    }

    @Test
    @DisplayName("딜러, 플레이어들의 최종 수익을 출력할 수 있다")
    void printFinalProfitTest() {
        PlayerProfitDto pobiProfit = PlayerProfitDto.of("pobi", 1500);
        PlayerProfitDto jasonProfit = PlayerProfitDto.of("jason", -2500);
        int dealerProfit = 1000;
        ProfitStatusDto profitStatus = ProfitStatusDto.of(List.of(pobiProfit, jasonProfit), dealerProfit);
        String expectedPrintedMessage = NEW_LINE + "## 최종 수익" + NEW_LINE
                + "딜러 : 1000" + NEW_LINE
                + "pobi : 1500" + NEW_LINE
                + "jason : -2500" + NEW_LINE;

        outputView.printFinalProfit(profitStatus);

        assertThat(printer.getPrintedMessage()).isEqualTo(expectedPrintedMessage);
    }

    @BeforeEach
    void initializeTestObject() {
        printer = new MockPrinter();
        mockCardView = new MockCardView();
        outputView = new OutputView(mockCardView, printer);
    }
}
