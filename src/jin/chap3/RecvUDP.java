package jin.chap3;
import java.io.*;
import java.net.*;

public class RecvUDP implements ItemQuoteTextConst {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		int port;
		if(args.length != 1)
			port = 8080;
			//throw new IllegalArgumentException("Parameter(s) : <Port>");
		else
			port = Integer.parseInt(args[0]);
		
		DatagramSocket sock = new DatagramSocket(port);
		ItemQuoteDecoderBin decoder = new ItemQuoteDecoderBin();
		
		DatagramPacket packet = new DatagramPacket(new byte[MAX_WIRE_LENGTH], MAX_WIRE_LENGTH);
		
		sock.receive(packet);
		ItemQuote quote = decoder.decode(packet);
		System.out.println(quote);
		
		sock.close();
		

		

	}

}
