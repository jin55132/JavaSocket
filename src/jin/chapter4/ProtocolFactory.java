package jin.chapter4;

import java.net.Socket;

public interface ProtocolFactory {

		public Runnable crateProtocol (Socket clntSock, Logger logger);
		
}
