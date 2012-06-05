package jin.chapter4;

import java.net.*;
import java.io.*;

public class PoolDispatcher implements Dispatcher{
	static final String NUMTHREADS = "8";
	static final String THREADPROP = "Threads";
	private int a;
	private int numThreads;
	
	public PoolDispatcher() {
		numThreads = Integer.parseInt(System.getProperty(THREADPROP, NUMTHREADS));
	}

	@Override
	public void startDispatching(final ServerSocket servSock,final Logger logger,
			final ProtocolFactory protoFactory) {
			
		// 이놈이 스레드 만들고 실행함... 각각 스레드는 무한 루프의 대기상태(accept())임...
		for(int i = 0; i < (numThreads - 1) ; i ++)
		{
			Thread thread = new Thread() {
				private void dispatchLoop(ServerSocket servSock, Logger logger, ProtocolFactory protoFactory) {
					for(;; ) {
						try {
							Socket clntSock = servSock.accept();
							Runnable protocol = protoFactory.crateProtocol(clntSock, logger);
							protocol.run();
						} catch (IOException e)
						{
							logger.writeEntry("Exception = " + e.getMessage());
						}
					}
					
				}
				
				public void run () {
					dispatchLoop(servSock, logger, protoFactory);
					
				}
			};
			thread.start();
			logger.writeEntry("Created and started thread = " + thread.getName());
		}
		logger.writeEntry("Iterative server starting in main thread" + Thread.currentThread().getName());
		
		//dispatchLoop(servSock, logger, protoFactory); // this is for main thread .. not necessary
		
		
	}
	

}
