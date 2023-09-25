package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class ParticipantCardsTest {

    @Nested
    @DisplayName("객체 생성 테스트")
    class CreationTest {

        @Test
        @DisplayName("객체 생성 시 초기에 한 장 이상의 카드가 있어야 한다")
        void creationTest_whenCardsSizeIsZero_throwException() {
            List<Card> cards = new ArrayList<>();

            assertThatThrownByMakingHandCards(cards);
            assertThatThrownByMakingHandCards(null);
        }

        void assertThatThrownByMakingHandCards(List<Card> cards) {
            assertThatThrownBy(() -> new ParticipantCards(cards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("최소 한 장의 카드는 가지고 있어야 합니다");
        }
    }

    @Nested
    @DisplayName("카드의 숫자 합계를 계산할 수 있다")
    class PointTest {

        @Test
        @DisplayName("ACE가 없을 경우, 각 카드 숫자 합계를 반환한다")
        void calculatePointTest_whenAceNotExist() {
            List<Card> cards = List.of(new Card(CardShape.CLOVER, CardLetter.FIVE),
                    new Card(CardShape.DIAMOND, CardLetter.TEN), new Card(CardShape.HEART, CardLetter.EIGHT));
            ParticipantCards participantCards = new ParticipantCards(cards);
            int expected = 5 + 10 + 8;

            int actual = participantCards.calculatePoint();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("ACE가 있으며 합계가 11을 초과하지 않는 경우, ACE는 11로 사용될 수 있다")
        void calculatePointTest_whenAceExistAndUnder11() {
            List<Card> cards = List.of(new Card(CardShape.CLOVER, CardLetter.FIVE),
                    new Card(CardShape.DIAMOND, CardLetter.TEN), new Card(CardShape.HEART, CardLetter.ACE));
            ParticipantCards participantCards = new ParticipantCards(cards);
            int expected = 5 + 10 + 1;

            int actual = participantCards.calculatePoint();

            assertThat(actual).isEqualTo(expected);
        }

        @Test
        @DisplayName("ACE가 있으며 합계가 11을 초과하는 경우, ACE는 1로 사용한다")
        void calculatePointTest_whenAceExistAndOver11() {
            List<Card> cards = List.of(new Card(CardShape.CLOVER, CardLetter.FIVE),
                    new Card(CardShape.HEART, CardLetter.ACE));
            ParticipantCards participantCards = new ParticipantCards(cards);
            int expected = 5 + 11;

            int actual = participantCards.calculatePoint();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("해당 카드 합계가 버스트 되었는지 알 수 있다")
    class BustTest {

        @Test
        @DisplayName("점수가 21을 초과하면 버스트 된다")
        void isBustTest_whenPointOver21_returnTrue() {
            List<Card> cards = List.of(new Card(CardShape.CLOVER, CardLetter.TEN),
                    new Card(CardShape.HEART, CardLetter.FIVE), new Card(CardShape.HEART, CardLetter.SEVEN));
            ParticipantCards participantCards = new ParticipantCards(cards);

            boolean actual = participantCards.isBust();

            assertThat(actual).isTrue();
        }

        @Test
        @DisplayName("점수가 21이면 버스트가 아니다")
        void isBustTest_whenPointIs21_returnFalse() {
            List<Card> cards = List.of(new Card(CardShape.CLOVER, CardLetter.TEN),
                    new Card(CardShape.HEART, CardLetter.FIVE), new Card(CardShape.HEART, CardLetter.SIX));
            ParticipantCards participantCards = new ParticipantCards(cards);

            boolean actual = participantCards.isBust();

            assertThat(actual).isFalse();
        }

        @Test
        @DisplayName("점수가 21 미만이면 버스트가 아니다")
        void isBustTest_whenPointUnder21_returnFalse() {
            List<Card> cards = List.of(new Card(CardShape.CLOVER, CardLetter.TEN),
                    new Card(CardShape.HEART, CardLetter.TEN));
            ParticipantCards participantCards = new ParticipantCards(cards);

            boolean actual = participantCards.isBust();

            assertThat(actual).isFalse();
        }
    }
}
