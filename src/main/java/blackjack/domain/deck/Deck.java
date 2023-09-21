package blackjack.domain.deck;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deck {

    private static final int INITIAL_CARDS_COUNT = 52;

    private final Queue<Card> cards;

    Deck(List<Card> cards) {
        LinkedList<Card> copiedCards = new LinkedList<>(cards);
        validateCards(copiedCards);
        this.cards = copiedCards;
    }

    private void validateCards(List<Card> cards){
        validateSize(cards);
        validateUniqueness(cards);
    }

    private void validateSize(List<Card> cards) {
        if (cards.size() != INITIAL_CARDS_COUNT) {
            String errorMessage = String.format("초기 덱은 %d장이어야 합니다", INITIAL_CARDS_COUNT);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateUniqueness(List<Card> cards) {
        int countOfUniqueCard = (int) cards.stream().distinct().count();
        if (countOfUniqueCard != INITIAL_CARDS_COUNT) {
            throw new IllegalArgumentException("초기 덱은 중복 카드가 없어야 합니다");
        }
    }
}
