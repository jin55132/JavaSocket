package jin.chap3;

public class ItemQuote {

	public long itemNumber;
	public String itemDescription;
	public int quantity;
	public int unitPrice;
	public boolean discounted;
	public boolean inStock;
	
	public ItemQuote(long itemNumber, String itemDescription, int quantity, int unitPrice
			, boolean discounted, boolean inStock)
	{
		this.itemNumber = itemNumber;
		this.itemDescription = itemDescription;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discounted = discounted;
		this.inStock = inStock;
		
	}
	
	public String toString() {
		final String EOLN = java.lang.System.getProperty("line.separator");
		String value = "Item#=" + itemNumber + EOLN +
				"Description=" + itemDescription + EOLN + 
				"Quantity=" + quantity + EOLN +
				"Price(each)=" + unitPrice + EOLN +
				"Total=" + (quantity * unitPrice);
		
		if(discounted)
			value +=" (discounted)";
		if(inStock)
			value += EOLN + "In Stock" + EOLN;
		else
			value += EOLN + "Out of Stock" + EOLN;
		
		return value;
		}
}
