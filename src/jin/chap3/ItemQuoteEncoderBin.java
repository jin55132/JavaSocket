package jin.chap3;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ItemQuoteEncoderBin implements ItemQuoteBinConst, ItemQuoteEncoder {

	private String encoding;

	public ItemQuoteEncoderBin(){
		encoding = DEFAULT_ENCODING;
	}
	
	public ItemQuoteEncoderBin(String encoding){
		this.encoding = encoding;
	}
	
	
	
	@Override
	public byte[] encode(ItemQuote item) throws IOException {
	
		ByteArrayOutputStream buf = new ByteArrayOutputStream ();
		DataOutputStream out = new DataOutputStream(buf);
		out.writeLong(item.itemNumber);
		out.writeInt(item.quantity);
		out.writeInt(item.unitPrice);
		
		byte flags = 0;
		if(item.discounted)
			flags |= DISCOUNT_FLAG;
		if(item.inStock)
			flags |= IN_STOCK_FLAG;
		
		out.write(flags);
		byte[] encodedDesc = item.itemDescription.getBytes(encoding);
		if(encodedDesc.length > MAX_DESC_LEN)
			throw new IOException("Item Desc exceeds encoded length limit");
		out.writeByte(encodedDesc.length);
		out.write(encodedDesc);
		out.flush();
		return buf.toByteArray();
		
	}
	
}
