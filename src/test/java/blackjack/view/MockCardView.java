package blackjack.view;

import blackjack.dto.card.CardsDto;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MockCardView extends CardView {

    public static final String CARD_TEXT = "CARD";
    public static final String CARD_DELIMITER = ",";

    static String createMockView(int size) {
        return IntStream.range(0, size)
                .mapToObj(index -> CARD_TEXT)
                .collect(Collectors.joining(CARD_DELIMITER));
    }

    @Override
    String toCardsView(CardsDto dto) {
        int size = dto.getCardDtos().size();
        return createMockView(size);
    }

    @Override
    String toFirstCardView(CardsDto dto) {
        return CARD_TEXT;
    }


}
