package restaurant;


public class TransactionData {
    
    private String  type;    
    private String  item;    
    private int     amount;  
    private double  profit; 
    private boolean success; 

    
    public TransactionData (){
        type = null;
        item = null;
        amount = 0;
        profit = 0;
        success = false;
    }
    
   
    public TransactionData (String type, String item, int amount, double profit, boolean success){
        this.type = type;
        this.item = item;
        this.amount = amount;
        this.profit = profit;
        this.success = success;
    }

    
    public String getType() { return type;}
    public String getItem() { return item;}
    public int getAmount() { return amount;}
    public Double getProfit() { return profit;}
    public boolean getSuccess() { return success;}

    public void setType(String typeInput) {type = typeInput;}
    public void setItem(String itemInput) {item = itemInput;}
    public void setProfit(double profitInput) {profit = profitInput;}
    public void setSuccess(boolean successInput) {success = successInput;}
}
