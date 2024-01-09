package restaurant;

public class People{
    private int    numberInParty; 
    private String nameOfParty;  
    private int    tableIndex;   

    public People (int peopleInParty, String nameOfParty, int tableIndex){
        this.numberInParty = peopleInParty;
        this.nameOfParty = nameOfParty;
        this.tableIndex = tableIndex;
    }

    public int getNumberInParty(){ return numberInParty;}

    public String getNameOfParty(){ return nameOfParty;}

    public void setNumberInParty(int numberInPartyInput){ numberInParty = numberInPartyInput;}

    public void setNameOfParty (String nameOfPartyInput){ nameOfParty = nameOfPartyInput;}

    public int getTableIndex(){ return tableIndex;}

    public void setTableIndex(int tableIndexInput){ tableIndex = tableIndexInput;}

}