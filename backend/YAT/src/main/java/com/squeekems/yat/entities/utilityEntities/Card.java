package com.squeekems.yat.entities.utilityEntities;

import com.squeekems.yat.entities.Event;

import java.util.List;
import java.util.Objects;

public class Card {

    private CardEvent cardEvent;

    private List<OptionResult> optionResult;

    public Card() {
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardEvent=" + cardEvent +
                ", optionResult=" + optionResult +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardEvent, card.cardEvent) && Objects.equals(optionResult, card.optionResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardEvent, optionResult);
    }

    public CardEvent getCardEvent() {
        return cardEvent;
    }

    public void setCardEvent(CardEvent cardEvent) {
        this.cardEvent = cardEvent;
    }

    public List<OptionResult> getOptionResult() {
        return optionResult;
    }

    public void setOptionResult(List<OptionResult> optionResult) {
        this.optionResult = optionResult;
    }
}

