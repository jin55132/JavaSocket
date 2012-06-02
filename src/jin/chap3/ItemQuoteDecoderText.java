package jin.chap3;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;

public class ItemQuoteDecoderText implements ItemQuoteTextConst,
		ItemQuoteDecoder {

	private String encoding;
	
	public ItemQuoteDecoderText()
	{
		encoding = DEFAULT_ENCODING;
	}
	public ItemQuoteDecoderText(String encoding)
	{
		this.encoding = encoding;
	}
	
	
	@Override
	public ItemQuote decode(InputStream wire) throws IOException {
	
		String itemNo, description, quant, price, flags;
		byte[] space = " ".getBytes(encoding);
		byte[] newline = "\n".getBytes(encoding);
		itemNo = new String(Framer.nextToken(wire, space), encoding);
		description = new String(Framer.nextToken(wire, newline), encoding);
		quant = new String(Framer.nextToken(wire, space), encoding);
		price = new String(Framer.nextToken(wire, space), encoding);
		flags = new String(Framer.nextToken(wire, newline), encoding);
		return new ItemQuote(Long.parseLong(itemNo), description, Integer.parseInt(quant)
				, Integer.parseInt(price), (flags.indexOf('d') != -1), flags.indexOf('s') != -1);
			
	}
	
	public ItemQuote decode (DatagramPacket p) throws IOException {
		ByteArrayInputStream payload = new ByteArrayInputStream (p.getData(), p.getOffset(), p.getLength());
		return decode(payload);
		
	}

}
