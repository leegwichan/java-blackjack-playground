package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.ParticipantCards;

public final class Dealer extends Participant {

    private static final int DRAWABLE_UPPER_LIMIT_POINT = 16;
    private static final String ADDING_CARD_EXCEPTION_FORMAT = "딜러는 포인트가 %d 초과 시 카드를 추가할 수 없습니다";

    public Dealer(ParticipantCards cards) {
        super(cards);
    }

    public boolean isCardAddable() {
        return super.getPoint() <= DRAWABLE_UPPER_LIMIT_POINT;
    }

    @Override
    public void add(Card card) {
        validatePoints();
        super.add(card);
    }

    private void validatePoints() {
        if (!isCardAddable()) {
            String message = String.format(ADDING_CARD_EXCEPTION_FORMAT, DRAWABLE_UPPER_LIMIT_POINT);
            throw new IllegalStateException(message);
        }
    }
}
