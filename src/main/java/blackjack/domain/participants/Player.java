package blackjack.domain.participants;

import blackjack.domain.card.ParticipantCards;

public final class Player extends Participant {

    private final String name;

    public Player(String name, ParticipantCards cards) {
        super(cards);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
