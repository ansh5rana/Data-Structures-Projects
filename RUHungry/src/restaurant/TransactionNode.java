package restaurant;


public class TransactionNode{
    private TransactionData data; 
    private TransactionNode next; 

    
    public TransactionNode (){
        data = null;
        next = null;
    }


    public TransactionNode(TransactionData data, TransactionNode next){
        this.data = data;
        this.next = next;
    }
    
    public TransactionData getData() { return data;}
    public TransactionNode getNext() { return next;}

    public void setData(TransactionData dataInput) {data = dataInput;}
    public void setNext(TransactionNode nextInput) {next = nextInput;}
}