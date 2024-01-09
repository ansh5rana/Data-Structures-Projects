package restaurant;

public class StockNode {
    private Ingredient item; 
    private StockNode  next; 

  
    public StockNode(Ingredient item, StockNode next) {
        this.item = item;
        this.next = next;
    }

    
    public Ingredient getIngredient() { return item; }
    public void setIngredient(Ingredient item) { this.item = item; }
    
    public StockNode getNextStockNode() { return next; }
    public void setNextStockNode(StockNode next) { this.next = next; }
}
