import java.util.Date;

import com.illposed.osc.OSCMessage;

public class MessageObject {
	private int messageNumber;
	private Date timeReceived;
	private OSCMessage messageContent;
	
	public MessageObject(int messageNumber, Date timeReceived, OSCMessage messageContent) {
		super();
		this.messageNumber = messageNumber;
		this.timeReceived = timeReceived;
		this.messageContent = messageContent;
	}
	
	
	public int getMessageNumber() {
		return messageNumber;
	}
	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}
	public Date getTimeReceived() {
		return timeReceived;
	}
	public void setTimeReceived(Date timeReceived) {
		this.timeReceived = timeReceived;
	}
	public OSCMessage getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(OSCMessage messageContent) {
		this.messageContent = messageContent;
	}
	
}
