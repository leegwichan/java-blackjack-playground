package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardTest {

    @Nested
    @DisplayName("ACE 판별 테스트")
    class AceTest {
        
        @ParameterizedTest
        @CsvSource({"HEART, DIAMOND, CLOVER"})
        @DisplayName("ACE 카드를 ACE가 맞다고 구분할 수 있다")
        void isAceTest_whenCardIsAce_returnTrue(CardShape shape) {
            Card card = new Card(shape, CardLetter.ACE);

            assertThat(card.isAce()).isTrue();
        }

        @ParameterizedTest
        @CsvSource({"TWO, KING"})
        @DisplayName("ACE 카드가 아닐 때 ACE가 아니라고 구분할 수 있다")
        void isAceTest_whenCardIsNotAce_returnFalse(CardLetter letter) {
            CardShape shape = CardShape.SPADE;
            Card card = new Card(shape, letter);

            assertThat(card.isAce()).isFalse();
        }
    }

    @Nested
    @DisplayName("카드 별 숫자 테스트")
    class NumberTest {

        @ParameterizedTest
        @CsvSource({"JACK", "QUEEN", "KING"})
        @DisplayName("문자가 10보다 큰 카드는 10을 반환한다")
        void getNumberTest_whenCardIsOver10_return10(CardLetter letter) {
            Card card = new Card(CardShape.CLOVER, letter);
            int expected = 10;

            int actual = card.getNumber();

            assertThat(actual).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvSource({"ACE,1", "TWO,2", "TEN,10"})
        @DisplayName("문자가 10이하의 카드는 해당 숫자를 반환한다")
        void getNumberTest_whenCardIsUnder10(CardLetter letter, int expected) {
            Card card = new Card(CardShape.CLOVER, letter);

            int actual = card.getNumber();

            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("동치성 판별 테스트")
    class EqualTest {

        @ParameterizedTest
        @CsvSource({"DIAMOND, ACE", "SPADE, FIVE"})
        @DisplayName("카드의 문양과 문자가 같을 경우, 두 카드가 같다고 판단한다")
        void testEquals_whenSameProperties_returnTrue(CardShape shape, CardLetter letter) {
            Card card1 = new Card(shape, letter);
            Card card2 = new Card(shape, letter);

            assertThat(card1).isEqualTo(card2);
        }

        @Test
        @DisplayName("카드의 문양이 다를 경우, 두 카드가 다르다고 판단한다")
        void testEquals_whenDifferentShape_returnFalse() {
            Card card1 = new Card(CardShape.SPADE, CardLetter.FIVE);
            Card card2 = new Card(CardShape.CLOVER, CardLetter.FIVE);

            assertThat(card1).isNotEqualTo(card2);
        }

        @Test
        @DisplayName("카드의 문자가 다를 경우, 두 카드가 다르다고 판단한다")
        void testEquals_whenDifferentLetter_returnFalse() {
            Card card1 = new Card(CardShape.SPADE, CardLetter.FIVE);
            Card card2 = new Card(CardShape.CLOVER, CardLetter.FIVE);

            assertThat(card1).isNotEqualTo(card2);
        }
    }

    @Nested
    @DisplayName("해시코드 테스트")
    class HashcodeTest {

        @ParameterizedTest
        @CsvSource({"DIAMOND, ACE", "SPADE, FIVE"})
        @DisplayName("같은 두 객체는 같은 해시코드를 갖는다")
        void testHashCode_whenTwoCardIsSame_returnSameHashcode(CardShape shape, CardLetter letter) {
            Card card1 = new Card(shape, letter);
            Card card2 = new Card(shape, letter);

            assertThat(card1).hasSameHashCodeAs(card2);
        }
    }
}