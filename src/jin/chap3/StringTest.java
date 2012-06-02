package jin.chap3;

import java.io.*;
import java.io.IOException;

public class StringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		//ItemQuote qt = new ItemQuote();

		FileOutputStream fos = new FileOutputStream("obj.txt");
		
		DataOutputStream out = new DataOutputStream(fos);
		
		//out.writeInt(29413);
		//out.writeBoolean(false);
		//out.writeChar('');
		out.writeDouble(2134L);
		
		byte [] buffer = "Hello$World".getBytes();
		
		InputStream is = new ByteArrayInputStream(buffer);
		
		
		
		String s1 = new String(Framer.nextToken(is, "$".getBytes()));
		String s2 = new String(Framer.nextToken(is, "$".getBytes()));
		
		
		
		
		
		
//		System.out.println(me.);
		
	}

}
