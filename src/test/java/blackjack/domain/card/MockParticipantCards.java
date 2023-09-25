package blackjack.domain.card;

import java.util.List;
import java.util.Objects;

public class MockParticipantCards extends ParticipantCards {

    private static final int UPPER_LIMIT_POINT = 21;

    private int point;

    public MockParticipantCards(int point) {
        super(List.of(new Card(CardShape.DIAMOND, CardLetter.ACE)));
        this.point = point;
    }

    @Override
    public int calculatePoint() {
        return point;
    }

    @Override
    public boolean isBust() {
        return point > UPPER_LIMIT_POINT;
    }

    @Override
    public void add(Card card) {
        if (isBust()) {
            throw new IllegalStateException();
        }

        point += card.getNumber();
    }
}
