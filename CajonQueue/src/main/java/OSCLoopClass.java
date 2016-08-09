import java.util.ArrayList;

public class OSCLoopClass implements NewMessageListener{
	
	private CajonOSCInPort inPort = new CajonOSCInPort(this);

	public OSCLoopClass() {
		super();
	}

	public void loopetyLoop(){
		while(true);
	}
		
	public void onNewMessage(ArrayList<MessageObject> l) {
		for (MessageObject messageObject : l) {
			System.out.println(messageObject.getMessageContent().toString());
		}
	}

}
