package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParticipantCards {

    private static final int ACE_BONUS_POINT = 10;
    private static final int UPPER_LIMIT_POINT = 21;

    private final List<Card> cards;

    public ParticipantCards(List<Card> cards) {
        validateCardIsNotEmpty(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validateCardIsNotEmpty(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new IllegalArgumentException("최소 한 장의 카드는 가지고 있어야 합니다");
        }
    }

    public int calculatePoint() {
        int point = sumOfCardsNumber();
        if (isAceBonusCanAdded(point)) {
            point += ACE_BONUS_POINT;
        }

        return point;
    }

    private int sumOfCardsNumber() {
        return cards.stream().mapToInt(Card::getNumber).sum();
    }

    private boolean isAceBonusCanAdded(int point) {
        return isNotBustWhenAddAceBonusPoint(point) && isAceExist();
    }

    private boolean isNotBustWhenAddAceBonusPoint(int point) {
        return point + ACE_BONUS_POINT <= UPPER_LIMIT_POINT;
    }

    private boolean isAceExist() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return calculatePoint() > UPPER_LIMIT_POINT;
    }

    public void add(Card card) {
        Objects.requireNonNull(card);
        validateNotBust();

        cards.add(card);
    }

    private void validateNotBust() {
        if (isBust()) {
            throw new IllegalStateException("버스트 상태에서는 카드를 추가할 수 없습니다");
        }
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}
