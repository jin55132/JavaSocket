package jin.tcp;

import java.net.*;
import java.io.*;

public class TCPEchoClient
{
	public static void main(String args[]) throws IOException
	{
		if((args.length < 2) || (args.length > 3))
		{
			throw new IllegalArgumentException("parameter(s) : <Server> < Word> [port]");
		}
		
		
		String server = args[0];
		
		byte[] byteBuffer = args[1].getBytes();
		int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;
		
		Socket socket = new Socket(server, servPort);
		System.out.println("Connecting to server ");

		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		
		out.write(byteBuffer);
		
		int totalBytesRcvd = 0;
		int bytesRcvd;
		
		while (totalBytesRcvd < byteBuffer.length)
		{
			if((bytesRcvd = in.read(byteBuffer, totalBytesRcvd, byteBuffer.length - totalBytesRcvd)) == -1)
				throw new SocketException("Connection close permaturely");
			totalBytesRcvd  += bytesRcvd;
			
		}
		
		
		System.out.println("Rcvd : " + new String (byteBuffer));
		socket.close();
		//out.write(byteBuffer);
		
		}
		
	
	
}

