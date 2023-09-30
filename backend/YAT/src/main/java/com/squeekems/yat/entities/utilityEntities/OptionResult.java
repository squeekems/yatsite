package com.squeekems.yat.entities.utilityEntities;

import java.util.Objects;

public class OptionResult {

    private Long optionId;

    private String optionLabel;

    private Long resultId;

    private String resultPrompt;

    public OptionResult() {
    }

    public OptionResult(Long optionId, String optionLabel, Long resultId, String resultPrompt) {
        this.optionId = optionId;
        this.optionLabel = optionLabel;
        this.resultId = resultId;
        this.resultPrompt = resultPrompt;
    }

    @Override
    public String toString() {
        return "OptionResult{" +
                "optionId=" + optionId +
                ", optionLabel='" + optionLabel + '\'' +
                ", resultId=" + resultId +
                ", resultPrompt='" + resultPrompt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionResult that = (OptionResult) o;
        return Objects.equals(optionId, that.optionId) && Objects.equals(optionLabel, that.optionLabel) && Objects.equals(resultId, that.resultId) && Objects.equals(resultPrompt, that.resultPrompt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId, optionLabel, resultId, resultPrompt);
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public String getOptionLabel() {
        return optionLabel;
    }

    public void setOptionLabel(String optionLabel) {
        this.optionLabel = optionLabel;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public String getResultPrompt() {
        return resultPrompt;
    }

    public void setResultPrompt(String resultPrompt) {
        this.resultPrompt = resultPrompt;
    }
}
