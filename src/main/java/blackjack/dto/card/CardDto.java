package blackjack.dto.card;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardShape;
import java.util.Objects;

public class CardDto {

    private final CardShape shape;
    private final CardLetter letter;

    private CardDto(CardShape shape, CardLetter letter) {
        this.shape = Objects.requireNonNull(shape);
        this.letter = Objects.requireNonNull(letter);
    }

    public static CardDto of(Card card) {
        return new CardDto(card.getShape(), card.getLetter());
    }

    public static CardDto of(CardShape shape, CardLetter letter) {
        return new CardDto(shape, letter);
    }

    public CardShape getShape() {
        return shape;
    }

    public CardLetter getLetter() {
        return letter;
    }
}
