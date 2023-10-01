package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

abstract class Participant {

    private final ParticipantCards cards;

    Participant(ParticipantCards cards) {
        this.cards = cards;
    }

    public void add(Card card) {
        cards.add(card);
    }

    public final boolean isBust() {
        return cards.isBust();
    }

    public final int getPoint() {
        return cards.calculatePoint();
    }

    public final ParticipantCards getCards() {
        return cards;
    }
}
