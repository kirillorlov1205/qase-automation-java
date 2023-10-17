package model;

import utils.TestDataGenerator;

public class Step {

    private String stepAction = TestDataGenerator.generateRandomString(1, 10);
    private String data = TestDataGenerator.generateRandomString(1, 10);
    private String expectedResult = TestDataGenerator.generateRandomString(1, 10);

    public Step() {
    }

    public Step(String stepAction, String data, String expectedResult) {
        this.stepAction = stepAction;
        this.data = data;
        this.expectedResult = expectedResult;
    }

    public String getStepAction() {
        return stepAction;
    }

    public String getData() {
        return data;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    @Override
    public String toString() {
        return "Step{" +
                "stepAction='" + stepAction + '\'' +
                ", data='" + data + '\'' +
                ", expectedResult='" + expectedResult + '\'' +
                '}';
    }
}
