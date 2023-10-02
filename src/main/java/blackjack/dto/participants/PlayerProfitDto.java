package blackjack.dto.participants;

public class PlayerProfitDto {

    private final String name;
    private final int profit;

    private PlayerProfitDto(String name, int profit) {
        this.name = name;
        this.profit = profit;
    }

    public static PlayerProfitDto of(String name, int profit) {
        return new PlayerProfitDto(name, profit);
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
