package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardLetterTest {

    @Test
    @DisplayName("ACE가 아니라고 판단할 수 있다")
    void isAceTest_whenIsAce_returnTrue() {
        CardLetter letter = CardLetter.ACE;

        assertThat(letter.isAce()).isTrue();
    }

    @ParameterizedTest
    @CsvSource({"TWO", "KING"})
    void isAceTest_whenISNotAce_returnFalse(CardLetter letter) {
        assertThat(letter.isAce()).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"KING", "QUEEN", "JACK"})
    @DisplayName("10 초과의 카드는 숫자를 10으로 반환한다")
    void getNumberTest_whenOver10(CardLetter letter) {
        int expected = 10;

        int actual = letter.getNumber();

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"ACE,1", "TWO,2", "TEN,10"})
    @DisplayName("숫자 카드는 해당 숫자를 반환한다")
    void getNumberTest_whenNumberCard(CardLetter letter, int expected) {
        int actual = letter.getNumber();

        assertThat(actual).isEqualTo(expected);
    }
}