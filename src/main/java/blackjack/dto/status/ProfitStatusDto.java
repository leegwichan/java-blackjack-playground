package blackjack.dto.status;

import blackjack.dto.participants.PlayerProfitDto;
import java.util.List;

public class ProfitStatusDto {

    private final List<PlayerProfitDto> playersProfit;
    private final int dealerProfit;

    private ProfitStatusDto(List<PlayerProfitDto> playersProfit, int dealerProfit) {
        this.playersProfit = List.copyOf(playersProfit);
        this.dealerProfit = dealerProfit;
    }

    public static ProfitStatusDto of(List<PlayerProfitDto> playersProfit, int dealerProfit) {
        return new ProfitStatusDto(playersProfit, dealerProfit);
    }

    public int getDealerProfit() {
        return dealerProfit;
    }

    public List<PlayerProfitDto> getPlayersProfit() {
        return List.copyOf(playersProfit);
    }
}
