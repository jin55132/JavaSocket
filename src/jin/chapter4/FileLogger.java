package jin.chapter4;

import java.io.*;
import java.util.*;

public class FileLogger {
	
	PrintWriter out;

	public FileLogger (String filename) throws IOException {
		out = new PrintWriter(new FileWriter(filename), true);
	}
	
	public synchronized void writeEntry(Collection entry)
	{
		for(Iterator line = entry.iterator(); line.hasNext();)
		{
			out.println(line.next());
			
		}
		out.println();
	}
	
	public synchronized void writeEntry(String entry)
	{
		out.println(entry);
		out.println();
		
	}
}
