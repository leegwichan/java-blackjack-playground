package blackjack.domain.deck;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class DeckTest {

    @Nested
    @DisplayName("생성 테스트")
    class CreationTest {

        private static final int DEFAULT_SIZE = 52;

        @Test
        @DisplayName("카드 장수가 52장이 아니면 예외를 던진다")
        void createTest_whenCardsSizeIsNotMatched_throwException() {
            List<Card> cards = orderedFullCards();
            cards.remove(0);

            assertThatThrownBy(() -> new Deck(cards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("초기 덱은 52장이어야 합니다");
        }

        @Test
        @DisplayName("중복된 카드가 있다면 예외를 던진다")
        void createTest_whenCardsIsOverlapped_throwException() {
            List<Card> cards = orderedFullCards();
            cards.remove(51);
            cards.add(cards.get(50));

            assertThatThrownBy(() -> new Deck(cards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("초기 덱은 중복 카드가 없어야 합니다");
        }
    }

    List<Card> orderedFullCards() {
        List<Card> cards = new ArrayList<>();
        for (CardShape shape : CardShape.values()) {
            for (CardLetter letter : CardLetter.values()) {
                cards.add(new Card(shape, letter));
            }
        }
        return cards;
    }
}