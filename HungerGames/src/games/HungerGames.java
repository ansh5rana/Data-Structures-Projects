package games;
import java.security.Key;
import java.util.ArrayList;
import org.w3c.dom.Node;

public class HungerGames {

    private ArrayList<District> districts;  // all districts in Panem.
    private TreeNode            game;       // root of the BST. The BST contains districts that are still in the game.

    public HungerGames() {
        districts = new ArrayList<>();
        game = null;
        StdRandom.setSeed(2023);
    }

    public void setupPanem(String filename) { 
        StdIn.setFile(filename);  
        setupDistricts(filename); 
        setupPeople(filename);
    }
    
    public void setupDistricts (String filename) {
        int counter = 0;
        int numOfDistrict = 0;

        if(StdIn.isEmpty())
        {
            return;
        }
        while(StdIn.hasNextLine())
        {
            if(counter == numOfDistrict+1)
            {
                return;
            }
            if(counter ==0)
            {
                numOfDistrict = StdIn.readInt();
            }
            else if(counter <= numOfDistrict)
            {
                districts.add(new District(StdIn.readInt()));

            }
            counter++;
        }
    }

    
    public void setupPeople (String filename) {
        int numOfPlayer = StdIn.readInt();

        for(int j=0; j<numOfPlayer; j++)
        {
            String firstName = StdIn.readString();
            String lastName = StdIn.readString();
            int birthMonth = StdIn.readInt();
            int age = StdIn.readInt();
            int districtId = StdIn.readInt();
            int effectiveness = StdIn.readInt();
            Person person = new Person(birthMonth, firstName, lastName, age, districtId, effectiveness);
            if(age >=12 && age<18){
                person.setTessera(true);
            }

            for(int i=0; i<districts.size(); i++)
            {
                if(districts.get(i).getDistrictID() == person.getDistrictID())
                {
                    if(birthMonth%2 ==0)
                    {
                        districts.get(i).addEvenPerson(person);
                    }
                    else
                    {
                        districts.get(i).addOddPerson(person);
                    }
                }
            }
        }
    }

    
    public void addDistrictToGame(TreeNode root, District newDistrict) {
        game = insert(root, newDistrict);
        districts.remove(newDistrict);
    }

    public TreeNode insert(TreeNode root, District district)
    {
        if (root == null) {
            root = new TreeNode(district, null, null);
            return root;
        }
        else if (district.getDistrictID() < root.getDistrict().getDistrictID())
        {
            root.setLeft(insert(root.getLeft(), district));
        }
        else if (district.getDistrictID() > root.getDistrict().getDistrictID())
        {
            root.setRight(insert(root.getRight(), district));
        }
        return root;
    }

    
    public District findDistrict(int id) {
        TreeNode root = game;
        while(root!=null)
        {
            if(root.getDistrict().getDistrictID()==id)
            {
                return root.getDistrict();
            }
            else if(id>root.getDistrict().getDistrictID())
            {
                root=root.getRight();
            }
            else{
                root=root.getLeft();
            }
        }
        return null;
    }

    
    public DuelPair selectDuelers() {
        TreeNode node = game;
        DuelPair pair = new DuelPair();

        preOrder(node, pair);
        if(pair.getPerson1() == null)
        {
            EvenpreOrdernoOdd(node, pair);
            if(pair.getPerson2() == null)
            {
                oddAdult(node, pair);
                evenAdult(node, pair);
            }
            else{
                oddAdultWEven(node, pair);
            }
        }
        else{
            EvenpreOrder(node, pair);
            if(pair.getPerson2() == null)
            {
                evenAdult(node, pair);
            }
        }

        return pair;
    }

    public void preOrder(TreeNode node, DuelPair pair){
        if (node == null)
        {
            return;
        }

        for(int i=0; i<node.getDistrict().getOddPopulation().size(); i++)
        {
            if(node.getDistrict().getOddPopulation().get(i).getTessera() == true)
            {
                Person p = node.getDistrict().getOddPopulation().get(i);
                node.getDistrict().getOddPopulation().remove(p);
                pair.setPerson1(p);
                return;
            }
        }
        preOrder(node.getLeft(), pair);

        preOrder(node.getRight(), pair);
    }

    public void EvenpreOrdernoOdd(TreeNode node, DuelPair pair){
        if (node == null)
        {
            return;
        }
    
        for(int i=0; i<node.getDistrict().getEvenPopulation().size(); i++)
        {
            if(node.getDistrict().getEvenPopulation().get(i).getTessera() == true)
            {
                pair.setPerson2(node.getDistrict().getEvenPopulation().get(i));
                node.getDistrict().getEvenPopulation().remove(pair.getPerson2());
                return;   
            }
        }
        EvenpreOrdernoOdd(node.getLeft(), pair);

        EvenpreOrdernoOdd(node.getRight(), pair);
    }
    
    public void EvenpreOrder(TreeNode node, DuelPair pair){
        if (node == null)
        {
            return;
        }
    
        for(int i=0; i<node.getDistrict().getEvenPopulation().size(); i++)
        {
            if(node.getDistrict().getEvenPopulation().get(i).getTessera() == true && pair.getPerson1().getDistrictID() != node.getDistrict().getDistrictID())
            {
                Person p = node.getDistrict().getEvenPopulation().get(i);
                node.getDistrict().getEvenPopulation().remove(p);
                pair.setPerson2(p);
                return;
            }
        }
       EvenpreOrder(node.getLeft(), pair);
       EvenpreOrder(node.getRight(), pair);
    }

    public void oddAdult(TreeNode node, DuelPair pair)
    {
        if (node == null)
        {
            return;
        }

        if(node.getDistrict().getOddPopulation().size() > 0)
        {
            Person p = node.getDistrict().getOddPopulation().get(StdRandom.uniform(node.getDistrict().getOddPopulation().size()));
            node.getDistrict().getOddPopulation().remove(p);
            pair.setPerson1(p);
            return;
        }
        oddAdult(node.getLeft(), pair);
        oddAdult(node.getRight(), pair);
    }
    public void oddAdultWEven(TreeNode node, DuelPair pair)
    {
        if (node == null)
        {
            return;
        }

        if(node.getDistrict().getDistrictID() != pair.getPerson2().getDistrictID())
        {
            if(node.getDistrict().getOddPopulation().size() > 0)
            {
                int x = node.getDistrict().getOddPopulation().size();
                Person p = node.getDistrict().getOddPopulation().get(StdRandom.uniform(x));
                node.getDistrict().getOddPopulation().remove(p);
                pair.setPerson1(p);
                return;
            }
        }
    
        oddAdultWEven(node.getLeft(), pair);
        oddAdultWEven(node.getRight(), pair);
    }

    public void evenAdult(TreeNode node, DuelPair pair)
    {
        if (node == null)
        {
            return;
        }

        if(node.getDistrict().getDistrictID() != pair.getPerson1().getDistrictID())
        {
            if(node.getDistrict().getEvenPopulation().size() > 0)
            {
                Person p = node.getDistrict().getEvenPopulation().get(StdRandom.uniform(node.getDistrict().getEvenPopulation().size()));
                node.getDistrict().getEvenPopulation().remove(p);
                pair.setPerson2(p);
                return;
            } 
        }
        evenAdult(node.getLeft(), pair);
        evenAdult(node.getRight(), pair);
    }
        
    
    public void eliminateDistrict(int id) {
        game = deleteNode(game, id);
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
        {
            return null;
        }

        if (root.getDistrict().getDistrictID() > key) {
            root.setLeft(deleteNode(root.getLeft(), key));
            return root;
        }
        else if(root.getDistrict().getDistrictID() < key) {
            root.setRight(deleteNode(root.getRight(), key));
            return root;
        }
 
        if (root.getLeft() == null) {
            TreeNode temp = root.getRight();
            return temp;
        }
        else if (root.getRight() == null) {
            TreeNode temp = root.getLeft();
            return temp;
        }
        else {
            TreeNode succParent = root;
            TreeNode succ = root.getRight();

            while (succ.getLeft() != null) {
                succParent = succ;
                succ = succ.getLeft();
            }
            if (succParent != root)
                succParent.setLeft(succ.getRight());
            else
                succParent.setRight(succ.getRight());
 
            root.setDistrict(succ.getDistrict());
            return root;
        }
    }

    public void eliminateDueler(DuelPair pair) {
        if(pair.getPerson1() == null && pair.getPerson2()!=null)
        {
            returnPerson(pair.getPerson2());
            return;
        }
        if(pair.getPerson2() == null && pair.getPerson1()!=null)
        {
            returnPerson(pair.getPerson1());
            return;
        }
        Person winner = pair.getPerson1().duel(pair.getPerson2());
        returnPerson(winner);
        Person loser = null;
        if(pair.getPerson1() == winner)
        {
            loser = pair.getPerson2();
        }
        else if(pair.getPerson2() == winner)
        {
            loser = pair.getPerson1();
        }
        int loserID = loser.getDistrictID();
        District loserDistrict = findDistrict(loserID);
        if(loserDistrict.getOddPopulation().size()==0 || loserDistrict.getEvenPopulation().size() == 0)
        {
            eliminateDistrict(loserID);
        }
    }
    public void returnPerson(Person person){
        int id = person.getDistrictID();
        int oe;
        if(person.getBirthMonth() %2==0)
            oe = 0;
        else
            oe = 1;
        
        District district = findDistrict(id);
        if(oe==0)
        {
            district.getEvenPopulation().add(person);
        }
        else if (oe==1)
        {
            district.getOddPopulation().add(person);
        }
    }

    public ArrayList<District> getDistricts() {
        return this.districts;
    }

    public TreeNode getRoot() {
        return game;
    }
}