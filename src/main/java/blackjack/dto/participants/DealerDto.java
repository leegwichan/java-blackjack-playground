package blackjack.dto.participants;

import blackjack.domain.participants.Dealer;
import blackjack.dto.card.CardsDto;
import java.util.Objects;

public class DealerDto {

    private final int point;
    private final CardsDto cards;

    private DealerDto(int point, CardsDto cards) {
        this.point = point;
        this.cards = Objects.requireNonNull(cards);
    }

    public static DealerDto from(Dealer dealer) {
        return new DealerDto(dealer.getPoint(), CardsDto.of(dealer.getCards()));
    }

    public int getPoint() {
        return point;
    }

    public CardsDto getCards() {
        return cards;
    }
}
