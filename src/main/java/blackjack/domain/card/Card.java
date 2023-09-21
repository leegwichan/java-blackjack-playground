package blackjack.domain.card;

import java.util.Objects;

public class Card {

    private final CardShape shape;
    private final CardLetter letter;

    public Card(CardShape shape, CardLetter letter) {
        this.shape = Objects.requireNonNull(shape);
        this.letter = Objects.requireNonNull(letter);
    }

    public int getNumber() {
        return letter.getNumber();
    }

    public boolean isAce() {
        return letter.isAce();
    }

    public CardLetter getLetter() {
        return letter;
    }

    public CardShape getShape() {
        return shape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return this.letter == card.letter && this.shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, letter);
    }
}
