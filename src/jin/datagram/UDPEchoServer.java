package jin.datagram;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPEchoServer {

	private static final int ECHOMAX = 25;
	
	public static void main(String[] args) throws IOException
	{
		if(args.length != 1)
			throw new IllegalArgumentException("Parameter(s) : <Port>");
	
		int servPort = Integer.parseInt(args[0]);
		
	
		DatagramSocket socket = new DatagramSocket(servPort);
		DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);
		
		for(;;)
		{
			socket.receive(packet);
			System.out.println("handling client at" + packet.getAddress().getHostAddress() + " on port " + packet.getPort());
			socket.send(packet);
			packet.setLength(ECHOMAX);
			
		}
		
	}
}
