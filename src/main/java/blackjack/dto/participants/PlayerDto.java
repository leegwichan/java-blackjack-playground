package blackjack.dto.participants;

import blackjack.domain.participants.Player;
import blackjack.dto.card.CardsDto;
import java.util.Objects;

public class PlayerDto {

    private final String name;
    private final int betAmount;
    private final int point;
    private final CardsDto cards;

    private PlayerDto(String name, int betAmount, int point, CardsDto cards) {
        this.name = Objects.requireNonNull(name);
        this.betAmount = betAmount;
        this.point = point;
        this.cards = Objects.requireNonNull(cards);
    }

    public static PlayerDto from(Player player) {
        return new PlayerDto(player.getName(), player.getBetAmount(),
                player.getPoint(), CardsDto.of(player.getCards()));
    }

    public static PlayerDto of(String name, int betAmount, int point, CardsDto cards) {
        return new PlayerDto(name, betAmount, point, cards);
    }

    public String getName() {
        return name;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public int getPoint() {
        return point;
    }

    public CardsDto getCards() {
        return cards;
    }
}
