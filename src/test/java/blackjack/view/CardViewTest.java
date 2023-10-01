package blackjack.view;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardShape;
import blackjack.dto.card.CardDto;
import blackjack.dto.card.CardsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

class CardViewTest {

    @ParameterizedTest
    @CsvSource({"DIAMOND,ACE,A다이아몬드", "CLOVER,EIGHT,8클로버", "SPADE,QUEEN,Q스페이드"})
    @DisplayName("카드 1장을 출력 형식으로 변환할 수 있다")
    void toCardsViewTest_whenCardsSizeIs1(CardShape shape, CardLetter letter, String expected) {
        CardsDto cards = CardsDto.of(List.of(CardDto.of(shape, letter)));
        CardView cardView = new CardView();

        String actual = cardView.toCardsView(cards);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드 2장을 출력 형식으로 변환할 수 있다")
    void toCardsViewTest_whenCardsSizeIs2() {
        CardDto card1 = CardDto.of(CardShape.SPADE, CardLetter.KING);
        CardDto card2 = CardDto.of(CardShape.HEART, CardLetter.SIX);
        CardsDto cards = CardsDto.of(List.of(card1, card2));
        CardView cardView = new CardView();
        String expected = "K스페이드, 6하트";

        String actual = cardView.toCardsView(cards);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드 3장을 출력 형식으로 변환할 수 있다")
    void toCardsViewTest_whenCardsSizeIs3() {
        CardDto card1 = CardDto.of(CardShape.SPADE, CardLetter.KING);
        CardDto card2 = CardDto.of(CardShape.HEART, CardLetter.SIX);
        CardDto card3 = CardDto.of(CardShape.HEART, CardLetter.NINE);
        CardsDto cards = CardsDto.of(List.of(card1, card2, card3));
        CardView cardView = new CardView();
        String expected = "K스페이드, 6하트, 9하트";

        String actual = cardView.toCardsView(cards);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("맨 앞장의 카드만 출력할 수 있다")
    void toFirstCardViewTest() {
        CardDto card1 = CardDto.of(CardShape.SPADE, CardLetter.KING);
        CardDto card2 = CardDto.of(CardShape.HEART, CardLetter.SIX);
        CardsDto cards = CardsDto.of(List.of(card1, card2));
        CardView cardView = new CardView();
        String expected = "K스페이드";

        String actual = cardView.toFirstCardView(cards);

        assertThat(actual).isEqualTo(expected);
    }
}

