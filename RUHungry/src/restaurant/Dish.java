package restaurant;


public class Dish {
    private String category; 
    private String dishName; 
    private int[] stockID; 
    private double price; 
    private double profit; 

    public Dish (String category, String dishName, int[] stockID) {
        this.category = category;
        this.dishName = dishName;
        this.stockID = stockID;
    }

    public String getCategory() { return category; }

    public String getDishName() { return dishName; }
    
    public int[] getStockID() { return stockID; }

    public double getPriceOfDish() { return price; }
    public void setPriceOfDish(double price) { this.price = price; }

    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }
}
