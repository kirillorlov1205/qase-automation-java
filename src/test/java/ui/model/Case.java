package ui.model;

import lombok.Builder;

import java.util.List;

@Builder
public class Case {
    private String title;
    private List<Step> steps;

    public String getTitle() {
        return title;
    }

    public Step getStepByIndex(int index) {
        return steps.get(index);
    }

    @Override
    public String toString() {
        return "Case{" +
                "title='" + title + '\'' +
                ", steps=" + steps +
                '}';
    }
}
