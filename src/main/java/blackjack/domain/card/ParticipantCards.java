package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class ParticipantCards {

    private static final int ACE_BONUS_POINT = 10;
    private static final int UPPER_LIMIT_POINT = 21;

    private final List<Card> cards;

    public ParticipantCards(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new IllegalArgumentException("최소 한 장의 카드는 가지고 있어야 합니다");
        }

        this.cards = new ArrayList<>(cards);
    }

    public int calculatePoint() {
        int point =  sumOfCardsNumber();
        if (point + ACE_BONUS_POINT <= UPPER_LIMIT_POINT && isAceExist()) {
            point += ACE_BONUS_POINT;
        }

        return point;
    }

    private int sumOfCardsNumber() {
        return cards.stream().mapToInt(Card::getNumber).sum();
    }

    private boolean isAceExist() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return calculatePoint() > UPPER_LIMIT_POINT;
    }
}
