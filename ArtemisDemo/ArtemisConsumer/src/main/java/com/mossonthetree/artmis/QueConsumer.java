package com.mossonthetree.artmis;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.function.Consumer;

public class QueConsumer implements AutoCloseable {
    private final String queueName;

    private final Consumer<String> messageHandler;

    private final Thread worker;

    private Connection connection;

    private Session session;

    private Destination queue;

    private MessageConsumer consumer;

    private boolean isRunning;

    private boolean inError;

    private String error;

    public QueConsumer(String queueName, String artemisHost, Consumer<String> messageHandler) {
        this.queueName = queueName;
        this.messageHandler = messageHandler;
        worker = new Thread(this::doWork);
        var connectionFactory = new ActiveMQConnectionFactory(artemisHost);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
        } catch (Exception ex) {
            inError = true;
            error = ex.getMessage();
            return;
        }
        try {
            session = connection.createSession();
        } catch (Exception ex) {
            inError = true;
            error = ex.getMessage();
            close();
            return;
        }
        try {
            queue = session.createQueue(queueName);
        } catch (Exception ex) {
            inError = true;
            error = ex.getMessage();
            close();
            return;
        }
        try {
             consumer = session.createConsumer(queue);
        } catch (Exception ex) {
            inError = true;
            error = ex.getMessage();
            close();
        }
    }

    public boolean isInError() {
        return inError;
    }

    public String getError() {
        return error;
    }

    public void start() {
        isRunning = true;
        worker.start();
    }

    public void stop() {
        isRunning = false;
    }

    private void doWork() {
        while(isRunning) {
            try {
                var message = (TextMessage)consumer.receive();
                messageHandler.accept(message.getText());
            } catch (Exception ex) {
                inError = true;
                error = ex.getMessage();
            }
        }
    }

    @Override
    public void close() {
        try {
            consumer.close();
        } catch (Exception ignored) {

        }
        try {
            session.close();
        } catch (Exception ignored) {

        }
        try {
            connection.stop();
            connection.close();
        } catch (Exception ignored) {

        }
        try {
            worker.join();
        } catch (Exception ignored) {

        }
    }
}
