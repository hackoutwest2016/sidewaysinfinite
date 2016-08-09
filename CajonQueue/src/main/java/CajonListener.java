import java.util.ArrayList;
import java.util.Date;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;;


public class CajonListener implements OSCListener{

	private ArrayList<NewMessageListener> listeners = new ArrayList<NewMessageListener>();
	private ArrayList<MessageObject> receivedMessages = new ArrayList<MessageObject>();
	private int messageNumber = 0;
	
	public CajonListener(NewMessageListener listener) {
		super();
		this.listeners.add(listener);
	}

	public void acceptMessage(Date time, OSCMessage OSCContent) {
		receivedMessages.clear();
		receivedMessages.add(new MessageObject(messageNumber, time, OSCContent));
		updateListeners();
	}
	
	public void updateListeners(){
		for (NewMessageListener newMessageListener : listeners) {
			newMessageListener.onNewMessage(receivedMessages);
		}
	}
}
