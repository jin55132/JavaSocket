package jin.chapter4;

import java.net.Socket;

public class TimelimitEchoProtocolFactory implements ProtocolFactory{

	@Override
	public Runnable crateProtocol(Socket clntSock, Logger logger) {
		// TODO Auto-generated method stub
		return new TimelimitEchoProtocol(clntSock, logger);
	}
	

}
