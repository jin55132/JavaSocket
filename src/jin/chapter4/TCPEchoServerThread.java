package jin.chapter4;

import java.net.*;
import java.io.*;

public class TCPEchoServerThread {

	public static void main(String args[]) throws IOException
	{
		
		int port;
		if(args.length != 1)
			port = 8080;
			//throw new IllegalArgumentException("Parameter(s) : <Port>");
		else
			port = Integer.parseInt(args[0]);
		
		ServerSocket servSock = new ServerSocket(port);
		
		Logger logger = new ConsoleLogger();
		
		for(;;)
		{
			try{
				Socket clntSock = servSock.accept();
				EchoProtocol protocol = new EchoProtocol(clntSock,logger);
				Thread thread = new Thread(protocol);
				thread.start();
				logger.writeEntry("Created and started Thread" + thread.getName());
				
			} catch (IOException e) {
				logger.writeEntry("Exception = " + e.getMessage());
			}
		}
		
	}
}
