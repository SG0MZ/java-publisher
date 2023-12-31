package com.demo.publisher;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

public class RealTimeExample {
	
	public static void main(String[] args) {
		
		ConnectionFactory factory = new ActiveMQConnectionFactory("admin","admin",
				"tcp://localhost:61616");
		
		try {
			Connection connection = factory.createConnection();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("demo");
			
			JSONObject json = new JSONObject();
			json.put("from_date", "01-Jan-2019");
			json.put("to_date", "31-Dec-2019");
			json.put("email", "xyz@gmail.com");
			json.put("query", "select * from data");
			
			TextMessage textMessage = session.createTextMessage(json.toString());
			
			MessageProducer producer = session.createProducer(destination);
			producer.send(textMessage);
			
			session.close();
			connection.close();
		} catch(JMSException e) {
			e.printStackTrace();
		}
		
	}
}
