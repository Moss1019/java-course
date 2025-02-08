package com.mossonthetree;

public class Question {
    public final String text;

    public final String[] options;

    public final int answer;

    public Question(String text, String[] options, int answer) {
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public String getAnswer() {
        return options[answer];
    }

    public boolean isCorrect(int answerIndex) {
        return answerIndex == answer;
    }
}
