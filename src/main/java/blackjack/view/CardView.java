package blackjack.view;

import blackjack.domain.card.CardLetter;
import blackjack.domain.card.CardShape;
import blackjack.dto.CardDto;
import blackjack.dto.CardsDto;
import java.util.Map;
import java.util.stream.Collectors;

class CardView {

    private static final String CARD_DELIMITER = ", ";

    private static final Map<CardShape, String> SHAPE_TO_VIEW
            = Map.of(CardShape.SPADE, "스페이드", CardShape.HEART, "하트",
                    CardShape.DIAMOND, "다이아몬드", CardShape.CLOVER, "클로버");
    private static final Map<CardLetter, String> LETTER_TO_VIEW = Map.ofEntries(
            Map.entry(CardLetter.ACE, "A"), Map.entry(CardLetter.TWO, "2"), Map.entry(CardLetter.THREE, "3"),
            Map.entry(CardLetter.FOUR, "4"), Map.entry(CardLetter.FIVE, "5"), Map.entry(CardLetter.SIX, "6"),
            Map.entry(CardLetter.SEVEN, "7"), Map.entry(CardLetter.EIGHT, "8"), Map.entry(CardLetter.NINE, "9"),
            Map.entry(CardLetter.TEN, "10"), Map.entry(CardLetter.JACK, "J"), Map.entry(CardLetter.QUEEN, "Q"),
            Map.entry(CardLetter.KING, "K"));

    String toCardsView(CardsDto dto) {
        return dto.getCardDtos().stream()
                .map(this::toCardView)
                .collect(Collectors.joining(CARD_DELIMITER));
    }

    private String toCardView(CardDto dto) {
        String letterView = LETTER_TO_VIEW.get(dto.getLetter());
        String shapeView = SHAPE_TO_VIEW.get(dto.getShape());

        return letterView.concat(shapeView);
    }
}
