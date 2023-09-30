package blackjack.domain.participants;

import blackjack.domain.card.ParticipantCards;
import java.util.Objects;

public final class Player extends Participant {

    private final String name;
    private final int betAmount;

    public Player(String name, int betAmount, ParticipantCards cards) {
        super(cards);
        this.name = Objects.requireNonNull(name);

        validateBetAmount(betAmount);
        this.betAmount = betAmount;
    }

    private void validateBetAmount(int betAmount) {
        if (betAmount < 0) {
            throw new IllegalArgumentException("베팅 금액은 양수이어야 합니다");
        }
    }

    public String getName() {
        return name;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
