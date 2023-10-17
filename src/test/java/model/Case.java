package model;

import utils.TestDataGenerator;

import java.util.List;

public class Case {

    private String title = TestDataGenerator.generateRandomString(1, 10);
    private List<Step> steps = List.of(new Step());

    public Case(String title) {
        this.title = title;
    }

    public Case() {
    }

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
