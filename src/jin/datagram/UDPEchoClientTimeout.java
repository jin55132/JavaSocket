package jin.datagram;

import java.io.*;
import java.net.*;

public class UDPEchoClientTimeout {
	private static final int TIMEOUT=3000;
	private static final int MAXTRIES=5;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		if((args.length < 2) || (args.length > 3))
		{
			throw new IllegalArgumentException("Parameter(s): <Server> <Woord> [<port>]");		
		}
		
		InetAddress serverAddress = InetAddress.getByName(args[0]);
	
		byte[] bytesToSend = args[1].getBytes();
		int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;
		
		DatagramSocket socket = new DatagramSocket();
	
		// TImeout 후 Exceptino 발생
		socket.setSoTimeout(TIMEOUT);
		
	
		// 패킷 생성
		DatagramPacket sendPacket = new DatagramPacket(bytesToSend, bytesToSend.length, serverAddress, servPort);
		DatagramPacket receivePacket = new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);
		
		int tries = 0;
		
		boolean receivedResponse = false;
		
		do {
			socket.send(sendPacket); 
			
			try {
				socket.receive(receivePacket);
				if(!receivePacket.getAddress().equals(serverAddress))
					throw new IOException("Received Packet from an unknown source");
				
				receivedResponse = true;
				
			}
			catch(InterruptedIOException e)
			{
				tries +=1;
				System.out.println("Time out" + (MAXTRIES-tries) + "more tries...");
				
			}
		} while((!receivedResponse) && (tries < MAXTRIES));
		
		if(receivedResponse)
			System.out.println("Received: " + new String(receivePacket.getData()));
		else
			System.out.println("o response -- give up");
		
		socket.close();
		
		
		
	}

}
