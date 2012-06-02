package jin.chap3;

import java.net.*;
import java.io.*;

public class SendUDP {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String dest;
		int port;
		
		if(args.length != 2)
			//throw new IllegalArgumentException("Params : <DEST> <PORT>");
		{
			dest = "localhost";
			port = 8080;
		}
		else {
			
			dest = args[0];
			port = Integer.parseInt(args[1]);
		}
			
		InetAddress destAddr = InetAddress.getByName(dest);
		int destPort = port;
		
		ItemQuote quote = new ItemQuote(123456789012345L, "5mm SUper widgets", 1000, 12999, true, false);
		
		DatagramSocket sock = new DatagramSocket();
		
		ItemQuoteEncoder encoder = new ItemQuoteEncoderBin();
		byte[] codedQuote = encoder.encode(quote);
		DatagramPacket message = new DatagramPacket(codedQuote, codedQuote.length, destAddr, destPort);
		
		sock.send(message);
		sock.close();
		
	}

}
