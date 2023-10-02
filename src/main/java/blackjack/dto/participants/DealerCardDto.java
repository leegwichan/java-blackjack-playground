package blackjack.dto.participants;

import blackjack.domain.participants.Dealer;
import blackjack.dto.card.CardsDto;
import java.util.Objects;

public class DealerCardDto {

    private final int point;
    private final CardsDto cards;

    private DealerCardDto(int point, CardsDto cards) {
        this.point = point;
        this.cards = Objects.requireNonNull(cards);
    }

    public static DealerCardDto from(Dealer dealer) {
        return new DealerCardDto(dealer.getPoint(), CardsDto.of(dealer.getCards()));
    }

    public static DealerCardDto of(int point, CardsDto cards) {
        return new DealerCardDto(point, cards);
    }

    public int getPoint() {
        return point;
    }

    public CardsDto getCards() {
        return cards;
    }
}
