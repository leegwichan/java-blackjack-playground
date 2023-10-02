package blackjack.dto.status;

import static java.util.stream.Collectors.toList;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.dto.participants.DealerDto;
import blackjack.dto.participants.PlayerCardDto;
import java.util.List;
import java.util.Objects;

public class StatusDto {

    private final DealerDto dealer;
    private final List<PlayerCardDto> players;

    private StatusDto(DealerDto dealer, List<PlayerCardDto> players) {
        this.dealer = Objects.requireNonNull(dealer);
        Objects.requireNonNull(players);
        this.players = List.copyOf(players);
    }

    public static StatusDto of(Dealer dealer, List<Player> players) {
        List<PlayerCardDto> playerCardDtos = players.stream().map(PlayerCardDto::from).collect(toList());
        return new StatusDto(DealerDto.from(dealer), playerCardDtos);
    }

    public static StatusDto of(DealerDto dealerDto, List<PlayerCardDto> playerCardDtos) {
        return new StatusDto(dealerDto, playerCardDtos);
    }

    public DealerDto getDealer() {
        return dealer;
    }

    public List<PlayerCardDto> getPlayers() {
        return List.copyOf(players);
    }
}
