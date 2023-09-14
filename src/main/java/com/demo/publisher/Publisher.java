package com.demo.publisher;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Publisher {

	public static void main(String[] args) {
		
		ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin",
				"tcp://localhost:61616");
		
		try {
			Connection connection = factory.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("demo");
			
			TextMessage textMessage = session.createTextMessage("First Message");
			
			MessageProducer producer = session.createProducer(destination);
			producer.send(textMessage);
			
			System.out.println("Message Published");
			
			session.close();
			connection.close();
			
		} catch(JMSException e) {
			e.printStackTrace();
		}
		
	}

}
