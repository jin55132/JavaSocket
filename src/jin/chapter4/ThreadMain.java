package jin.chapter4;
import java.io.*;
import java.net.*;
public class ThreadMain {
	public static void main(String[] args) throws Exception {
		String dispatcherName;
		String protocolName;
		int port;
		
		
		if(args.length != 3)
			//throw new IllegalArgumentException("Params : <DEST> <PORT>");
		{
			
			port = 8080;
			dispatcherName = "Pool";
			protocolName = "Echo";
		}
		else {
			
			protocolName =args[1];
			port = Integer.parseInt(args[0]);
			dispatcherName = args[2];
		}
		
		ServerSocket servSock = new ServerSocket(port);
		Logger logger  = new ConsoleLogger();
		
		
		Dispatcher dispatcher = (Dispatcher) Class.forName("jin.chapter4." + dispatcherName + "Dispatcher").newInstance();
		
	
		
		
		//Dispatcher dispatcher = (Dispatcher) ThreadMain.class.getClassLoader().loadClass(dispatcherName + "Dispatcher").newInstance();
		ProtocolFactory protoFactory = (ProtocolFactory) Class.forName("jin.chapter4." + protocolName + "ProtocolFactory").newInstance();
		
		dispatcher.startDispatching(servSock, logger, protoFactory);
		
	}
}
