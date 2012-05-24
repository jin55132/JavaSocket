package jin.File;

import java.io.*;


public class MyFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Hello h = new Hello();
		try
		{
			FileOutputStream fos = new FileOutputStream("obj.txt");
			
			
			ByteArrayOutputStream bb = new ByteArrayOutputStream();
			
			PrintStream ps = new PrintStream(bb);
			
			//BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream out = new ObjectOutputStream(ps);
			
			out.writeObject(h);
			
			byte[] a = bb.toByteArray();
			
			for(int i =0 ;i < a.length; ++i)
			{
				System.out.print((char)a[i]);
			}
			
			out.close();
			
			
			
			
			FileOutputStream ooo = new FileOutputStream("obj1.txt");
			char aa = 'A';
			ooo.write(aa);
			ooo.close();
			
			FileWriter fw = new FileWriter("asdf.txt");
			fw.write('Áø');
			fw.close();
			
			
			
			
			
			
//			FileInputStream fis = new FileInputStream("obj.txt");
//			
//			int av = fis.available();
//			
//			int i = 0;
//			while( (i = fis.read()) != -1)
//			{
//				System.out.format("%x", i);
//			}
			
			
			
			
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
			
		}
		
		
		
		
	}

}


class Hello implements Serializable{
	
	public int a;
	public int b;
	
}
