package jin;

import java.net.*;
import java.io.*;

public class TCPEchoClient
{
	public static void main(String args[]) throws IOException
	{
		String server = args[0];
		
		byte[] byteBuffer = args[1].getBytes();
		int servPort = (args.length == 3) ? Integer.parseInt(args[2]) : 7;
		
		Socket socket = new Socket(server, servPort);
		System.out.println("Connecting to server ");

		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		
		//in.re
		//out.write(byteBuffer);
		
		
		
		
	}
		
	
	
}

