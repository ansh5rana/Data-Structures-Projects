package restaurant;

public class MenuNode{
    private Dish dish;
    private MenuNode next; 

    
    public MenuNode (Dish dish, MenuNode next) {
        this.dish = dish;
        this.next = next;
    }
  
    public Dish getDish() { return dish; }
    public void setDish(Dish dish) { this.dish = dish; }

    public MenuNode getNextMenuNode() { return next; }
    public void setNextMenuNode(MenuNode next) { this.next = next; }
}