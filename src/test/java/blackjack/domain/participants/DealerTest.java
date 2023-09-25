package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.MockParticipantCards;
import blackjack.domain.card.ParticipantCards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Nested
    @DisplayName("딜러가 카드를 뽑을 수 있는지 확인할 수 있다")
    class CheckAddingCardTest {

        @Test
        @DisplayName("현재 포인트가 16보다 넘으면 카드를 추가할 수 없다")
        void isAddableTest_whenPointOver16_throwException() {
            ParticipantCards cards = new MockParticipantCards(17);
            Dealer dealer = new Dealer(cards);

            boolean actual = dealer.isCardAddable();

            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("현재 포인트가 16 이하이면 카드를 추가할 수 있다")
        void isAddableTest_whenPointUnder16() {
            ParticipantCards cards = new MockParticipantCards(16);
            Dealer dealer = new Dealer(cards);

            boolean actual = dealer.isCardAddable();

            assertThat(actual).isTrue();
        }
    }

    @Test
    @DisplayName("포인트가 16 초과인 경우 카드를 추가하면 예외를 던진다")
    void addTest_whenPointOver16_throwException() {
        ParticipantCards cards = new MockParticipantCards(17);
        Dealer dealer = new Dealer(cards);
        Card newCard = new Card(CardShape.HEART, CardLetter.ACE);

        assertThatThrownBy(() -> dealer.add(newCard))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러는 포인트가 16 초과 시 카드를 추가할 수 없습니다");
    }
}