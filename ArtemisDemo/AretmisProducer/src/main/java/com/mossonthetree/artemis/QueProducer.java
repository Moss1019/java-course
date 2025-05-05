package com.mossonthetree.artemis;

/*
    Connection

    Session

    Destination

    MessageProducer
 */

import jakarta.jms.Connection;
import jakarta.jms.Destination;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

public class QueProducer implements AutoCloseable {
    private final String queueName;

    private Connection connection;

    private Session session;

    private Destination queue;

    private MessageProducer producer;

    private boolean inError;

    private String error;

    public QueProducer(String queueName, String artemisHost) {
        this.queueName = queueName;
        var connectionFactory = new ActiveMQConnectionFactory(artemisHost);
        try {
            connection = connectionFactory.createConnection();
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
            queue = session.createQueue(this.queueName);
        } catch (Exception ex) {
            inError = true;
            error = ex.getMessage();
            close();
            return;
        }
        try {
            producer = session.createProducer(queue);
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

    public void produce(String message) {
        try {
            var m = session.createTextMessage(message);
            producer.send(m);
        } catch (Exception ex) {
            inError = true;
            error = ex.getMessage();
        }
    }

    @Override
    public void close() {
        try {
            producer.close();
        } catch (Exception ignored) {

        }
        try {
            session.close();
        } catch (Exception ignored) {

        }
        try {
            connection.close();
        } catch (Exception ignored) {

        }
    }
}
