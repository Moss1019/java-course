package com.mossonthetree;

import com.mossonthetree.artmis.QueConsumer;

public class Program {
    public static void main(String[] args) {
        var consumer = new QueConsumer("test-messages", "tcp://localhost:61616", Program::handleMessage);
        consumer.start();
        var isRunning = true;

        while(isRunning) {
            var input = getInput();
            switch(input) {
                case "-q" -> {
                    isRunning = false;
                }
                case "-m" -> {
                    var message = getInput();
                    System.out.println(message);
                }
            }
            if(consumer.isInError()) {
                System.out.println(consumer.getError());
            }
        }

        consumer.stop();
        consumer.close();
        System.exit(0);
    }

    private static void handleMessage(String message) {
        System.out.println("We received a message");
        System.out.println(message);
    }

    private static String getInput() {
        try {
            var buffer = new byte[1024];
            var read = System.in.read(buffer);
            return new String(buffer, 0, read - 1);
        } catch (Exception ignored) {
            return "";
        }
    }
}
