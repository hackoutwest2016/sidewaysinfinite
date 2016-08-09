import java.util.ArrayList;

public class UhmMainThing implements NewMessageListener{

	CajonOSCInPort inPort;
	
	public void main(String args[]){
		inPort = new CajonOSCInPort(this);
		while(true);
	}
	
	
	
	public void onNewMessage(ArrayList<MessageObject> l) {
		for (MessageObject messageObject : l) {
			System.out.println(messageObject.getMessageContent().toString());
		}
	}

}
