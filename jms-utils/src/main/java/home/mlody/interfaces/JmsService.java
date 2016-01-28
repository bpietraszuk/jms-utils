package home.mlody.interfaces;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface JmsService {
	@WebMethod
	public void sendMessagesToQueue(String content, int number) throws Exception;

	@WebMethod
	public String getDummyStringAfterTime(Long seconds) throws Exception;
}
