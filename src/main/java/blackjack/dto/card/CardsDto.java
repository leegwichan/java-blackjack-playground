package blackjack.dto.card;

import static java.util.stream.Collectors.toList;

import blackjack.domain.card.ParticipantCards;
import java.util.List;
import java.util.Objects;

public class CardsDto {

    private final List<CardDto> cardDtos;

    private CardsDto(List<CardDto> cardDtos) {
        this.cardDtos = Objects.requireNonNull(cardDtos);
    }

    public static CardsDto of(ParticipantCards cards) {
        List<CardDto> cardDtos = cards.getCards().stream()
                .map(CardDto::of)
                .collect(toList());

        return new CardsDto(cardDtos);
    }

    public static CardsDto of(List<CardDto> cardDtos) {
        return new CardsDto(List.copyOf(cardDtos));
    }

    public List<CardDto> getCardDtos() {
        return List.copyOf(cardDtos);
    }
}
