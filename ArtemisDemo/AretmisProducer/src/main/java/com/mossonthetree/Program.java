package com.mossonthetree;

import com.mossonthetree.artemis.QueProducer;

public class Program {
    public static void main(String[] args) {
        var producer = new QueProducer("test-messages", "tcp://localhost:61616");
        var isRunning = true;

        while(isRunning) {
            var input = getInput();
            switch(input) {
                case "-q" -> {
                    isRunning = false;
                }
                case "-m" -> {
                    var message = getInput();
                    producer.produce(message);
                }
            }
            if(producer.isInError()) {
                System.out.println(producer.getError());
            }
        }

        producer.close();
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
