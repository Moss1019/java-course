package com.mossonthetree;

public class Program {
    static Question[] questions;

    public static void main(String[] args)
        int currentQuestion = 0;
        int correctAnswers = 0;
        int incorrectAnswers = 0;
        while(currentQuestion != questions.length) {
            Question question = questions[currentQuestion];
            System.out.println(question.text)
            System.out.println("\tPick the correct answer by index\n");
            for(int i = 0; i < question.options.length; ++i) {
                System.out.println("\t" + (i + 1) + ": " + question.options[i]);
            }
            String input = getInput();
            int answer = Integer.parseInt(input) - 1
            if(question.isCorrect(answer)) {
                ++correctAnswers;
            } else {
                ++incorrectAnswers;
            }
            ++currentQuestion;
        }
        System.out.println("You got "  correctAnswers  " correct answers.");
        System.out.println("You got " + incorrectAnswers + " incorrect answers.");
        float percentage = correctAnswers * 100.0f / questions.length;
        System.out.println("You scored " + percentage + "%.");


    static String getInput() {
        try {
            byte[] buffer = new byte[512];
            int read = System.in.read(buffer);
            return new String(buffer, 0, read - 1);
        } catch (Exception ignored) {
            return "";
        }
    }

    static {
        questions = new Question[6]
        questions[0] = new Question("Which syntax is correct for declaring a variable?",
                new String{
                        "<data type> (name);",
                        "(name) <data type> = (initial value);",
                        "<data type> (name)",
                        "static <data type> (name)();"
                },
                0);
        questions[1] = new Question("Which statement is true about a while loop",
                new String[]{
                        "Code is executed at least once",
                        "The condition is evaluated first before any executions",
                        "There needs to be a break statement in the loop body",
                        "Any data type can be used as the condition"
                },
                1);
        questions[2] = new Question("When will you use a for loop?",
                new String[]{
                        "To execute code some unknown amount of times",
                        "To increment a value",
                        "To iterate through the entries of a collection",
                        "To iterate over some range and perform calculations based on the index"
                },
                3);
        questions[3] = new Question("Could a switch statement be used to monitor temperature of a room?",
                new String[]{
                        "Yes",
                        "No"
                },
                1);
        questions[4] = new Question("How many cases would a switch have if it operated on a boolean type",
                new String[]{
                        "Two, with optional default case",
                        "Two",
                        "One"
                }
                1);
        questions[5] = new Question("If there were an array defined as int[99] numbers;, would accessing it like this numbers[99] work?",
                new String[]{
                        "No",
                        "Yes",
                        "Maybe, if the computer is broken"
                },
                0)
    }
}
