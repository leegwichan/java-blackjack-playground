package blackjack.domain.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.ArrayList;
import java.util.List;

class DeckTest {

    private static final int DEFAULT_SIZE = 52;

    @Nested
    @DisplayName("생성 테스트")
    class CreationTest {

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

    @Nested
    @DisplayName("카드 뽑기 테스트")
    class CardDrawTest {

        @ParameterizedTest
        @CsvSource({"0", "-1", "-100"})
        @DisplayName("카드를 뽑는 수가 0이하일 경우 예외를 던진다")
        void drawTest_whenCountIsUnder0_throwException(int count) {
            Deck deck = new Deck(orderedFullCards());

            assertThatThrownBy(() -> deck.draw(count))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("카드는 한 장 이상 뽑아야 합니다");
        }

        @Test
        @DisplayName("카드가 덱에 다 비어있을 경우, 예외를 던진다")
        void drawTest_whenDeckIsEmpty_throwException() {
            Deck deck = new Deck(orderedFullCards());
            deck.draw(DEFAULT_SIZE);

            assertThatThrownBy(deck::draw)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("더 이상 뽑을 카드가 없습니다");
        }

        @ParameterizedTest
        @CsvSource({"1", "10", "52"})
        @DisplayName("카드를 N장 뽑을 수 있다")
        void drawTest(int count) {
            Deck deck = new Deck(orderedFullCards());

            List<Card> actual = deck.draw(count);

            assertThat(actual).hasSize(count);
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