package com.squeekems.yat.entities.utilityEntities;

import java.util.Objects;

public class CardEvent {
    private Long eventId;

    private String prompt;

    private String dsPrompt;

    public CardEvent() {
    }

    @Override
    public String toString() {
        return "CardEvent{" +
                "eventId=" + eventId +
                ", prompt='" + prompt + '\'' +
                ", dsPrompt='" + dsPrompt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardEvent cardEvent = (CardEvent) o;
        return Objects.equals(eventId, cardEvent.eventId) && Objects.equals(prompt, cardEvent.prompt) && Objects.equals(dsPrompt, cardEvent.dsPrompt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, prompt, dsPrompt);
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getDsPrompt() {
        return dsPrompt;
    }

    public void setDsPrompt(String dsPrompt) {
        this.dsPrompt = dsPrompt;
    }
}
