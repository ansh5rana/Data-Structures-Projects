package restaurant;

public class Ingredient{

    private    int ID;   
    private String name;
    private    int stockLevel; 
    private double cost;        

   
    public Ingredient(int ID, String name, int amount, double price) {
        this.ID = ID;
        this.name = name;
        stockLevel = amount;
        cost = price;
    }

    public String toString () {
        return "[" + name + "," + ID + "]";
    }


    public int getID() { return ID; }
    public void setID(int newID) { ID = newID; }

    public String getName() { return name; }
    public void setName(String newName) { name = newName; }

    public int getStockLevel() { return stockLevel; }
    public void setStockLevel(int newAmount) { stockLevel = newAmount; }
    public void updateStockLevel(int addAmount) { stockLevel += addAmount; }

    public double getCost() { return cost; }
    public void setCost(int newPrice) { cost = newPrice; }
}