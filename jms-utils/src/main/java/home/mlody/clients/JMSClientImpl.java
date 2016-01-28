package home.mlody.clients;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JMSClientImpl {
	private ConnectionFactory factory;
	private Connection connection;
	private Session session;
	private Queue queue;
	private MessageProducer producer;

	public void sendMessagesToQueue(String content, int number) throws JMSException {
		Message message = session.createTextMessage(content);
		System.out.println("Messages sending...");
		for (int i = 0; i < number; i++) {
			message.setIntProperty("MSG_NUMBER", i);			
			producer.send(message);
		}
		System.out.println("Messages sent");
	}

	public void initializeJmsConnection() throws JMSException,
			InterruptedException, NamingException {
		factory = (ConnectionFactory) new InitialContext().lookup("java:/JmsXA");
		connection = factory.createConnection();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) new InitialContext().lookup("java:jboss/exported/jms/queue/test");
		producer = session.createProducer(queue);
	}

	public void deintializeJmsConnection() throws JMSException {
		producer.close();
		session.close();
		connection.close();
	}
}
