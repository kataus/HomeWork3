package ru.otus.homework03.domain;

import java.util.ArrayList;

public class Question {
    // вопрос
    private String ask;
    // список ответов
    private ArrayList<String> answers;
    // правильный ответ
    private int correctAnswer;
    // ответ студента
    private int studentAnswer;

    public Question(String ask, ArrayList<String> answers, int correctAnswer) {
        this.ask = ask;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getAsk() {
        return ask;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setStudentAnswer(int studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public boolean getAnswerResult() {
        return (this.correctAnswer == this.studentAnswer);
    }
}
