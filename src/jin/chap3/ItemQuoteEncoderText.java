package jin.chap3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ItemQuoteEncoderText implements ItemQuoteEncoder,
		ItemQuoteTextConst {

	private String encoding;
	
	public ItemQuoteEncoderText() {
		encoding = DEFAULT_ENCODING;
	}
	
	public ItemQuoteEncoderText(String encoding) {
		this.encoding = encoding;
	}
	
	@Override
	public byte[] encode(ItemQuote item) throws Exception {
	
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		OutputStreamWriter out = new OutputStreamWriter(buf, encoding);
		
		out.write(item.itemNumber + " ");
		
		if(item.itemDescription.indexOf('\n') != -1)
			throw new IOException("Invalid description (contains new line)");
		
		out.write(item.itemDescription + "\n" + item.quantity + " " + item.unitPrice + " ");
		
		if(item.discounted)
			out.write('d');
		if(item.inStock)
			out.write('s');
		out.write('\n');
		out.flush();
		
		if(buf.size() > MAX_WIRE_LENGTH)
			throw new IOException ("Encoded length too long");
		
		return buf.toByteArray();
		
	}
	

}
