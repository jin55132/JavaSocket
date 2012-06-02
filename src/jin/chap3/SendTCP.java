package jin.chap3;

import java.io.*;
import java.net.*;

public class SendTCP {

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
		Socket sock = new Socket(destAddr, port);
		
		ItemQuote quote = new ItemQuote(123456789012345L, "5mm SUper widgets", 1000, 12999, true, false);
		ItemQuoteEncoder coder = new ItemQuoteEncoderText();
		byte[] codedQuote = coder.encode(quote);
		
		System.out.println("Sending Text-Encoded Quote (" + codedQuote.length + " bytes):");
		System.out.println(quote);
		sock.getOutputStream().write(codedQuote);
		
		
		// receive binary -encoded quote
		ItemQuoteDecoder decoder = new ItemQuoteDecoderBin();
		ItemQuote receiveQuote = decoder.decode(sock.getInputStream());
		System.out.println("Received Binary-Encode Quote:");
		System.out.println(receiveQuote);
		
			
		
	}

}
