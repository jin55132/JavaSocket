package jin.chap3;
import java.io.*;
import java.net.*;

public class RecvTCP {

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
		
		ServerSocket servSock = new ServerSocket(port);
		Socket clntSock = servSock.accept();
		
		ItemQuoteDecoder decoder = new ItemQuoteDecoderText();
		ItemQuote quote = decoder.decode(clntSock.getInputStream());
		System.out.println("Received Text-Encoded Quote");
		System.out.println(quote);
		
		ItemQuoteEncoder encoder = new ItemQuoteEncoderBin();
		quote.unitPrice += 100;
		System.out.println("Sending... (binary) ...");
		clntSock.getOutputStream().write(encoder.encode(quote));
		
		clntSock.close();
		servSock.close();
		

	}

}
