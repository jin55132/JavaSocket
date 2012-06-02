package jin.chap3;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;

public class ItemQuoteDecoderBin implements ItemQuoteDecoder, ItemQuoteBinConst {

	private String encoding;
	
	public ItemQuoteDecoderBin()
	{
		encoding = DEFAULT_ENCODING;
	}
	
	public ItemQuoteDecoderBin(String encoding)
	{
		this.encoding = encoding;
	}
	
	@Override
	public ItemQuote decode(InputStream wire) throws IOException {

		//boolean discounted, inStock;
		DataInputStream src = new DataInputStream(wire);
		
		long itemNumber = src.readLong();
		int quantity = src.readInt();
		int unitPrice = src.readInt();
		byte flags = src.readByte();
		int stringLength = src.read(); // return an unsigned byte as an int;
		if(stringLength == -1)
			throw new EOFException();
		
		byte[] stringBuf = new byte[stringLength];
		src.readFully(stringBuf);
		String itemDesc = new String(stringBuf, encoding);
		return new ItemQuote(itemNumber, itemDesc, quantity, unitPrice, 
				((flags & DISCOUNT_FLAG) == DISCOUNT_FLAG),
				((flags & IN_STOCK_FLAG) == IN_STOCK_FLAG));
		
	
	}
	
	public ItemQuote decode (DatagramPacket p) throws IOException {
		ByteArrayInputStream payload = new ByteArrayInputStream (p.getData(), p.getOffset(), p.getLength());
		return decode(payload);
		
	}

}
