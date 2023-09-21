package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class HandCardsTest {

    @Nested
    @DisplayName("객체 생성 테스트")
    class CreationTest {

        @Test
        @DisplayName("HandCards 생성 시 초기에 한 장 이상의 카드가 있어야 한다")
        void creationTest_whenCardsSizeIsZero_throwException() {
            List<Card> cards = new ArrayList<>();

            assertThatThrownByMakingHandCards(cards);
            assertThatThrownByMakingHandCards(null);
        }

        void assertThatThrownByMakingHandCards(List<Card> cards) {
            assertThatThrownBy(() -> new HandCards(cards))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("최소 한 장의 카드는 가지고 있어야 합니다");
        }
    }
}
