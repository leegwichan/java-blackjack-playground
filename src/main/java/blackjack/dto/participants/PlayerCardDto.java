package blackjack.dto.participants;

import blackjack.domain.participants.Player;
import blackjack.dto.card.CardsDto;
import java.util.Objects;

public class PlayerCardDto {

    private final String name;
    private final int point;
    private final CardsDto cards;

    private PlayerCardDto(String name, int point, CardsDto cards) {
        this.name = Objects.requireNonNull(name);
        this.point = point;
        this.cards = Objects.requireNonNull(cards);
    }

    public static PlayerCardDto from(Player player) {
        return new PlayerCardDto(player.getName(), player.getPoint(), CardsDto.of(player.getCards()));
    }

    public static PlayerCardDto of(String name, int point, CardsDto cards) {
        return new PlayerCardDto(name, point, cards);
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public CardsDto getCards() {
        return cards;
    }
}
