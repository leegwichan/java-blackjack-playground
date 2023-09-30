package blackjack.dto.status;

import static java.util.stream.Collectors.toList;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;
import blackjack.dto.participants.DealerDto;
import blackjack.dto.participants.PlayerDto;
import java.util.List;
import java.util.Objects;

public class StatusDto {

    private final DealerDto dealer;
    private final List<PlayerDto> players;

    private StatusDto(DealerDto dealer, List<PlayerDto> players) {
        this.dealer = Objects.requireNonNull(dealer);
        Objects.requireNonNull(players);
        this.players = List.copyOf(players);
    }

    public static StatusDto of(Dealer dealer, List<Player> players) {
        List<PlayerDto> playerDtos = players.stream().map(PlayerDto::from).collect(toList());
        return new StatusDto(DealerDto.from(dealer), playerDtos);
    }

    public DealerDto getDealer() {
        return dealer;
    }

    public List<PlayerDto> getPlayers() {
        return List.copyOf(players);
    }
}
