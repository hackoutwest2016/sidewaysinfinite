import java.net.SocketException;
import java.nio.charset.Charset;

import com.illposed.osc.OSCPortIn;
public class CajonOSCInPort {
	
	private OSCPortIn receiver;
	//TODO Set correct port
	private int port = 42;
	//TODO Set charset if required - if required then update constructor to include
	private Charset charset;
	//TODO Set addressSelector
	private String addressSelector;

	public CajonOSCInPort(NewMessageListener MainFileThing) {
		super();
		try {
			receiver = new OSCPortIn(port);
			receiver.addListener(addressSelector, new CajonListener(MainFileThing));
			receiver.run();
			receiver.startListening();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
