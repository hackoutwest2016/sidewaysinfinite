import java.util.ArrayList;
import java.util.List;

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
			List<Object> temp = messageObject.getMessageContent().getArguments();
			for (Object object : temp) {
				System.out.println(object.toString());
			}
		}
	}

}
