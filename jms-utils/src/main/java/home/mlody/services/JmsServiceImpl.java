package home.mlody.services;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jws.WebService;

import home.mlody.clients.JMSClientImpl;
import home.mlody.interfaces.JmsService;

@Stateless
@WebService(endpointInterface = "home.mlody.interfaces.JmsService")
public class JmsServiceImpl implements JmsService {

	private JMSClientImpl jmsClient;

	private JMSClientImpl getJmsClient() throws Exception {
		if (jmsClient == null) {
			jmsClient = new JMSClientImpl();
			jmsClient.initializeJmsConnection();
		}
		return jmsClient;
	}

	public void closeResources() throws JMSException {
		System.out.println("Closing connection...");
		jmsClient.deintializeJmsConnection();
	}

	@Override
	@TransactionAttribute(value=TransactionAttributeType.NOT_SUPPORTED)
	public void sendMessagesToQueue(String content, int number) throws Exception {
		getJmsClient().sendMessagesToQueue(content, number);
		
	}

	@Override
	@TransactionAttribute(value=TransactionAttributeType.NOT_SUPPORTED)
	public String getDummyStringAfterTime(Long seconds) throws Exception {
		Thread.sleep(seconds * 1000);
		return "dummyString";
	}
}