package blackjack.domain.deck;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardShape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckFactory {

    private DeckFactory() {}

    public static final Deck createRandomDeck() {
        List<Card> cards = new ArrayList<>();

        for (CardLetter letter : CardLetter.values()) {
            for (CardShape shape : CardShape.values()) {
                cards.add(new Card(shape, letter));
            }
        }

        Collections.shuffle(cards);
        return new Deck(cards);
    }
}
