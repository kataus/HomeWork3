package ru.otus.homework03.domain;

public class TestResult {
    private String ask;
    private String rigthAnswer;
    private boolean answerResult;

    public TestResult(String question, String rigthAnswer, boolean answerResult) {
        this.ask = question;
        this.rigthAnswer = rigthAnswer;
        this.answerResult = answerResult;
    }

    public String getAsk() {
        return ask;
    }

    public String getRigthAnswer() {
        return rigthAnswer;
    }

    public Boolean isAnswerResult() {
        return answerResult;
    }
}
