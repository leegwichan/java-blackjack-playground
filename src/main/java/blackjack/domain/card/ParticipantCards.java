package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class ParticipantCards {

    private final List<Card> cards;

    public ParticipantCards(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new IllegalArgumentException("최소 한 장의 카드는 가지고 있어야 합니다");
        }

        this.cards = new ArrayList<>(cards);
    }
}
