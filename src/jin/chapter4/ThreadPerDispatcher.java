package jin.chapter4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadPerDispatcher implements Dispatcher {

	@Override
	public void startDispatching(ServerSocket servSock, Logger logger,
			ProtocolFactory protoFactory) {
		
		for(;;)
		{
			try{
				Socket clntSock = servSock.accept();
				EchoProtocol protocol = new EchoProtocol(clntSock,logger);
				Thread thread = new Thread(protocol);
				thread.start();
				logger.writeEntry("Created and started Thread" + thread.getName());
			} catch (IOException e)
			{
				logger.writeEntry("Exception = " + e.getMessage());
			}
		}
	}
	
	

}
