package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.dto.participants.DealerCardDto;
import blackjack.dto.participants.PlayerCardDto;
import blackjack.dto.status.StatusDto;
import blackjack.view.printer.Printer;
import java.util.List;
import java.util.Objects;

public class OutputView {

    private static final String INITIAL_STATE_FORMAT = "%n딜러와 %s에게 2장의 카드를 나누었습니다.%n";
    private static final String DEALER_CARD_FORMAT = "딜러 카드: %s%n";
    private static final String PLAYER_CARD_FORMAT = "%s 카드: %s%n";
    private static final String DEALER_CARD_WITH_POINT_FORMAT = "%n딜러 카드: %s - 결과 : %d%n";
    private static final String PLAYER_CARD_WITH_POINT_FORMAT = "%s 카드: %s - 결과 : %d%n";

    private static final String PLAYER_NAME_DELIMITER = ", ";

    private final CardView cardView;
    private final Printer printer;

    public OutputView(CardView cardView, Printer printer) {
        this.cardView = Objects.requireNonNull(cardView);
        this.printer = Objects.requireNonNull(printer);
    }

    public void printInitialParticipantsCards(StatusDto status) {
        printInitialState(status.getPlayers());
        printDealerFirstCard(status.getDealer());
        status.getPlayers().forEach(this::printPlayerCards);
    }

    public void printFinalResult(StatusDto status) {
        printDealerCardsWithPoint(status.getDealer());
        status.getPlayers().forEach(this::printPlayerCardsWithPoint);
    }

    public void printFinalProfit() {

    }

    private void printInitialState(List<PlayerCardDto> playerCardDtos) {
        String playerNames = getPlayerNames(playerCardDtos);
        printer.print(String.format(INITIAL_STATE_FORMAT, playerNames));
    }

    private void printDealerFirstCard(DealerCardDto dealer) {
        String cardText = cardView.toFirstCardView(dealer.getCards());
        printer.print(String.format(DEALER_CARD_FORMAT, cardText));
    }

    private void printDealerCardsWithPoint(DealerCardDto dealer) {
        String cardText = cardView.toCardsView(dealer.getCards());
        printer.print(String.format(DEALER_CARD_WITH_POINT_FORMAT, cardText, dealer.getPoint()));
    }

    public void printPlayerCards(PlayerCardDto player) {
        String cardText = cardView.toCardsView(player.getCards());
        printer.print(String.format(PLAYER_CARD_FORMAT, player.getName(), cardText));
    }

    private void printPlayerCardsWithPoint(PlayerCardDto player) {
        String cardText = cardView.toCardsView(player.getCards());
        printer.print(String.format(PLAYER_CARD_WITH_POINT_FORMAT, player.getName(), cardText, player.getPoint()));
    }

    private String getPlayerNames(List<PlayerCardDto> playerCardDtos) {
        return playerCardDtos.stream()
                .map(PlayerCardDto::getName)
                .collect(joining(PLAYER_NAME_DELIMITER));
    }
}
