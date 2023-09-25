package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public final class Dealer extends Participant {

    private static final int DRAWABLE_UPPER_LIMIT_POINT = 16;

    public Dealer(ParticipantCards cards) {
        super(cards);
    }

    public boolean isCardAddable() {
        return super.getPoint() <= DRAWABLE_UPPER_LIMIT_POINT;
    }
}
