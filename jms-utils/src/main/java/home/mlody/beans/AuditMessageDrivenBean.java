package home.mlody.beans;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import home.mlody.utils.FileUtils;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/test")})
public class AuditMessageDrivenBean implements MessageListener {
	@Resource
    private MessageDrivenContext mdc;
	
	public AuditMessageDrivenBean() {
    }
	
	@Override
	public void onMessage(Message message) {
		try {			
			String fileContent = ((TextMessage)message).getText();
			FileUtils.writeToFile(fileContent+" "+message.getIntProperty("MSG_NUMBER")+"\n");			
		} catch (Exception e) {
			System.out.println("An error occured: ");
			e.printStackTrace();
		}
	}

}
