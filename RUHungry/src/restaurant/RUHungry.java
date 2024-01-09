package restaurant;

public class RUHungry {
    
    private String[] categoryVar;
    private MenuNode[] menuVar;   
    
    private StockNode[] stockVar;
    private int stockVarSize;

    private TransactionNode transactionVar; 

    
    private Queue<People> leftQueueVar;  

    private People[] tables;  
    private int[][]  tablesInfo;    

    public RUHungry () {
        categoryVar    = null;
        menuVar        = null;
        stockVar       = null;
        stockVarSize   = 0;
        transactionVar = null;
        leftQueueVar   = null;
        tablesInfo     = null;
        tables         = null;
    }

    public MenuNode[] getMenu() { return menuVar; }
    public String[] getCategoryArray() { return categoryVar;}
    public StockNode[] getStockVar() { return stockVar; } 
    public TransactionNode getFrontTransactionNode() { return transactionVar; } 
    public TransactionNode resetFrontNode() {return transactionVar = null;} // method to reset the transactions for a new day
    public Queue<People> getLeftQueueVar() { return leftQueueVar; } 
    public int[][] getTablesInfo() { return tablesInfo; }

    public void menu(String inputFile) {

        StdIn.setFile(inputFile);
        int numCats = StdIn.readInt();
        categoryVar = new String[numCats];
        menuVar = new MenuNode[numCats];
        for(int i=0; i<numCats; i++)
        {
            categoryVar[i] = StdIn.readString();
            int numDishes = StdIn.readInt();
            for(int k=0; k<numDishes; k++)
            {
                StdIn.readLine();
                String dishName = StdIn.readLine();
                int numIDS = StdIn.readInt();
                int[] ids = new int[numIDS];
                for(int j=0; j<numIDS; j++)
                {
                    ids[j] = StdIn.readInt();
                }
                Dish dish = new Dish(categoryVar[i], dishName, ids);
                MenuNode node = new MenuNode(dish, menuVar[i]);
                menuVar[i] = node;
            }
        }
    }


    public MenuNode findDish ( String dishName ) {

        MenuNode menuNode = null;

        // Search all categories since we don't know which category dishName is at
        for ( int category = 0; category < menuVar.length; category++ ) {

            MenuNode ptr = menuVar[category]; // set ptr at the front (first menuNode)
            
            while ( ptr != null ) { // while loop that searches the LL of the category to find the itemOrdered
                if ( ptr.getDish().getDishName().equals(dishName) ) {
                    return ptr;
                } else{
                    ptr = ptr.getNextMenuNode();
                }
            }
        }
        return menuNode;
    }

    public int findCategoryIndex ( String category ) {
        int index = 0;
        for ( int i = 0; i < categoryVar.length; i++ ){
            if ( category.equalsIgnoreCase(categoryVar[i]) ) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void addStockNode ( StockNode newNode ) {
        int index = newNode.getIngredient().getID()%stockVarSize;
        if(stockVar == null)
        {
            stockVar = new StockNode[stockVarSize];
        }
        if(stockVar == null)
        {
            newNode.setNextStockNode(null);
            stockVar[index] = newNode;
            return;
        }
        newNode.setNextStockNode(stockVar[index]);
        stockVar[index] = newNode;
    }


    public void deleteStockNode ( String ingredientName ) {
        for(int i=0; i<stockVar.length; i++)
        {
            StockNode ptr = stockVar[i];
            StockNode prev  = null;
            while(ptr!=null)
            {
                if(ptr.getIngredient().getName().equals(ingredientName))
                {
                    if(prev==null)
                    {
                        stockVar[i] = ptr.getNextStockNode();
                        return;
                    }
                    prev.setNextStockNode(ptr.getNextStockNode());
                    return;
                }
                prev = ptr;
                ptr = ptr.getNextStockNode();
            }
        }
    }
   
    public StockNode findStockNode (int ingredientID) {
        int index = ingredientID%stockVarSize;
        StockNode ptr = stockVar[index];
        while (ptr!=null)
        {
            if(ptr.getIngredient().getID() == ingredientID)
            {
                return ptr;
            }
            ptr = ptr.getNextStockNode();
        }
        return null; // update the return value
    }

    public StockNode findStockNode (String ingredientName) {
        
        StockNode stockNode = null;
        
        for ( int index = 0; index < stockVar.length; index ++ ){
            
            StockNode ptr = stockVar[index];
            
            while ( ptr != null ){
                if ( ptr.getIngredient().getName().equalsIgnoreCase(ingredientName) ){
                    return ptr;
                } else {  
                    ptr = ptr.getNextStockNode();
                }
            }
        }
        return stockNode;
    }
    
    public void updateStock (String ingredientName, int ingredientID, int stockAmountToAdd) {
        if(ingredientName!=null)
        {
            StockNode ptr = findStockNode(ingredientName);
            if(ptr!=null)
            {
                ptr.getIngredient().setStockLevel(stockAmountToAdd+ptr.getIngredient().getStockLevel());
                return;
            }
            return;
        }
        if(ingredientID!=-1)
        {
            StockNode ptr = findStockNode(ingredientID);
            if(ptr!=null)
            {
                ptr.getIngredient().setStockLevel(stockAmountToAdd+ptr.getIngredient().getStockLevel());
                return;
            }
            return;
        }
    }

    public void updatePriceAndProfit() {
        for(int i=0; i<menuVar.length; i++)
        {
            MenuNode ptr = menuVar[i];
            while(ptr!=null)
            {
                Dish dish = ptr.getDish();
                int[] arr = dish.getStockID();
                double cost = 0;
                for(int j=0; j<arr.length; j++)
                {
                    StockNode node = findStockNode(arr[j]);
                    cost += node.getIngredient().getCost();
                }
                dish.setPriceOfDish(cost*1.2);
                dish.setProfit(dish.getPriceOfDish()-cost);
                ptr=ptr.getNextMenuNode();
            }
            
        }
    }

    
    public void createStockHashTable(String inputFile){
        StdIn.setFile(inputFile);
        stockVarSize = StdIn.readInt(); 
        while(StdIn.hasNextLine())
        {
            int ingredientID = StdIn.readInt();
            StdIn.readChar();
            String name = StdIn.readLine();
            double cost = StdIn.readDouble();
            int stock = StdIn.readInt();
            Ingredient ingredient = new Ingredient(ingredientID, name, stock, cost);
            StockNode node = new StockNode(ingredient, null);
            addStockNode(node);
        }
    }

   

    public void addTransactionNode ( TransactionData data )
    {
        TransactionNode node = new TransactionNode(data, null);
        if(transactionVar==null)
        {
            transactionVar = node;
            return;
        }

        TransactionNode ptr = transactionVar;
        TransactionNode prev = null;
        while(ptr!=null)
        {
            prev=ptr;
            ptr=ptr.getNext();
        }
        prev.setNext(node);
    }


    public boolean checkDishAvailability ( String dishName, int numberOfDishes ){
        MenuNode node = findDish(dishName);
        Dish dish = node.getDish();
        int[] arr = dish.getStockID();
        for(int i=0; i<arr.length; i++)
        {
            StockNode ptr = findStockNode(arr[i]);
            if(ptr.getIngredient().getStockLevel() < numberOfDishes)
            {
                return false;
                
            }
        }
        return true; // update the return value
    }

    public void order ( String dishName, int quantity ){
        boolean prepare = checkDishAvailability(dishName, quantity);
        if(prepare)
        {
            MenuNode node = findDish(dishName);
            Dish dish = node.getDish();
            double profit = dish.getProfit();
            TransactionData order = new TransactionData("order", dishName, quantity, profit*quantity, true);
            addTransactionNode(order);
            int[] arr = dish.getStockID();
            for(int i=0; i<arr.length; i++)
            {
                StockNode ptr = findStockNode(arr[i]);
                updateStock(ptr.getIngredient().getName(), arr[i], -quantity);
            }
            return;
        }
        else if(!prepare)
        {
            MenuNode node = findDish(dishName);
            Dish dish = node.getDish();
            TransactionData order = new TransactionData("order", dishName, quantity, 0.0, false);
            addTransactionNode(order);

            MenuNode original = node;
            String cat = dish.getCategory();
            MenuNode front = null;
            for(int i=0; i<menuVar.length; i++)
            {
                if(menuVar[i].getDish().getCategory().equals(cat))
                {
                    front = menuVar[i];
                }
            }
            //MenuNode prev = null;

            node = node.getNextMenuNode();
            if(node == null)
            {
                //prev = node;
                node= front;
            }
            while(node!=original)
            {
                
                Dish dish2 = node.getDish();
                if(checkDishAvailability(dish2.getDishName(), quantity))
                {
                    double profit = dish2.getProfit();
                    TransactionData order2 = new TransactionData("order", dish2.getDishName(), quantity, profit*quantity, true);
                    addTransactionNode(order2);
                    int[] arr = dish2.getStockID();
                    for(int i=0; i<arr.length; i++)
                    {
                        StockNode ptr = findStockNode(arr[i]);
                        updateStock(ptr.getIngredient().getName(), arr[i], -quantity);
                    }
                    return;
                }
                //prev = node;
                addTransactionNode(new TransactionData("order", node.getDish().getDishName(), quantity, 0.0, false));
                node=node.getNextMenuNode();
                if(node == null)
                {
                    node= front;
                }
            }
            //addTransactionNode(new TransactionData("order", prev.getDish().getDishName(), quantity, 0.0, false));
        }
    }

   
    public double profit () {

        TransactionNode ptr = transactionVar;
        double profit = 0;
        while(ptr!=null)
        {
            profit += ptr.getData().getProfit();
            ptr=ptr.getNext();
        }
        return profit; // update the return value
    }

   
    public void donation ( String ingredientName, int quantity ){
        double profit = profit();
        StockNode node = findStockNode(ingredientName);
        Ingredient ing = node.getIngredient();
        if(profit>50 && ing.getStockLevel()>=quantity)
        {
            addTransactionNode(new TransactionData("donation", ingredientName, quantity, 0.0, true));
            updateStock(ingredientName, -1, -quantity);
        }
        else
        {
            addTransactionNode(new TransactionData("donation", ingredientName, quantity, 0.0, false));
        }
    }

    
    public void restock ( String ingredientName, int quantity ){
        double profit = profit();
        StockNode node = findStockNode(ingredientName);
        Ingredient ing = node.getIngredient();
        double cost = ing.getCost()*quantity;
        if(profit>cost)
        {
            addTransactionNode(new TransactionData("restock", ingredientName, quantity, -cost, true));
            updateStock(ingredientName, -1, quantity);
        }
        else
        {
            addTransactionNode(new TransactionData("restock", ingredientName, quantity, 0.0, false));
        }
    }


    public void createTables ( String inputFile ) { 

        StdIn.setFile(inputFile);
        int numberOfTables = StdIn.readInt();
        tablesInfo = new int[2][numberOfTables];
        tables = new People[numberOfTables];
        
        for ( int t = 0; t < numberOfTables; t++ ) {
            tablesInfo[0][t] = StdIn.readInt() * StdIn.readInt();
        }
    }
}