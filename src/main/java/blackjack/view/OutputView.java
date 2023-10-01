package blackjack.view;

import static java.util.stream.Collectors.joining;

import blackjack.dto.participants.DealerDto;
import blackjack.dto.participants.PlayerDto;
import blackjack.dto.status.StatusDto;
import blackjack.view.printer.Printer;
import java.util.List;
import java.util.Objects;

public class OutputView {

    private static final String INITIAL_STATE_FORMAT = "%n딜러와 %s에게 2장의 카드를 나누었습니다.%n";
    private static final String DEALER_CARD_FORMAT = "딜러 카드: %s%n";
    private static final String PLAYER_CARD_FORMAT = "%s 카드: %s%n";

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

    public void printFinalResult() {

    }

    public void printFinalProfit() {

    }

    private void printInitialState(List<PlayerDto> playerDtos) {
        String playerNames = getPlayerNames(playerDtos);
        printer.print(String.format(INITIAL_STATE_FORMAT, playerNames));
    }

    private void printDealerFirstCard(DealerDto dealer) {
        String cardText = cardView.toFirstCardView(dealer.getCards());
        printer.print(String.format(DEALER_CARD_FORMAT, cardText));
    }

    public void printPlayerCards(PlayerDto player) {
        String cardText = cardView.toCardsView(player.getCards());
        printer.print(String.format(PLAYER_CARD_FORMAT, player.getName(), cardText));
    }

    private String getPlayerNames(List<PlayerDto> playerDtos) {
        return playerDtos.stream()
                .map(PlayerDto::getName)
                .collect(joining(PLAYER_NAME_DELIMITER));
    }
}