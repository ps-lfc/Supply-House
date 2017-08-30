import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Parser {
	public int ProductId;
	public int Quantity;
	//Class mapkey contains objects of primary key, i.e. SUPPLIER_ID and PRODUCT_ID.
	PrimaryKey mapkey1=new PrimaryKey();
	//Database used is hashmap of the type MapKey,String. 
	//Mapkey stores primary key and string contains quantity associated with it. 
	public Map<PrimaryKey,String> SUPPLIER_PRODUCT = new HashMap<PrimaryKey,String>() ;
	public String[] lineReadArray;
	public Map<PrimaryKey,String> getMap(){
		return SUPPLIER_PRODUCT;
	}
	public void ParseLine(String lineRead, String nameofFile, int lineNumber, String ext){
		//check file type and split accordingly.
		if(ext.contentEquals("csv")){
			lineReadArray = lineRead.split(",");
		}
		else if(ext.contentEquals("txt")){
			lineReadArray = lineRead.split("\t");
		}
		//Line 1 of each file is reserved for headings
		if(lineNumber==1){
			for(int i=0;i<lineReadArray.length;i++){
				if(lineReadArray[i].equalsIgnoreCase("product")){
					ProductId = i; 
				}
				if(lineReadArray[i].equalsIgnoreCase("quantity") || lineReadArray[i].equalsIgnoreCase("inventory")){
					Quantity = i;
				}
			}
		}
		//Store relevant data in the Map.
		else{
			mapkey1.setSUPPLIER_ID(nameofFile);
			mapkey1.setPRODUCT_ID(lineReadArray[ProductId]);
			SUPPLIER_PRODUCT.put(mapkey1, lineReadArray[Quantity]);
		}
		//Display data in map after all the files have been processed
		for (Entry<PrimaryKey, String> entry : SUPPLIER_PRODUCT.entrySet()) {
			System.out.println("Supplier: " + entry.getKey().getSUPPLIER_ID());
			System.out.println("Product: " + entry.getKey().getPRODUCT_ID()); 
			System.out.println("Quantity: " + entry.getValue());
			System.out.println("---------------------------------------");
		}
	}
}