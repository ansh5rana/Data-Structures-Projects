package games;

import java.util.ArrayList;

public class District {
    private ArrayList<Person> oddPopulation;
    private ArrayList<Person> evenPopulation;
    private int districtID;

    public District(int id) {
        oddPopulation = new ArrayList<>();
        evenPopulation = new ArrayList<>();
        districtID = id;

    }

    public void addOddPerson(Person person) {
        oddPopulation.add(person);
    }

    public void addEvenPerson(Person person) {
        evenPopulation.add(person);
    }

    public ArrayList<Person> getOddPopulation() {
        return oddPopulation;
    }

    public ArrayList<Person> getEvenPopulation() {
        return evenPopulation;
    }
    
    public void setOddPopulation(ArrayList<Person> op) {
        oddPopulation = op;
    }

    public void setEvenPopulation(ArrayList<Person> ep) {
        evenPopulation = ep;
    }

    public int size() {
        return oddPopulation.size() + evenPopulation.size();
    }

    public int getDistrictID() {
        return districtID;
    }

    public String toString() {
        return "ID: " + districtID + ", Odd Population: " + oddPopulation + ", "
                + "Even Population: " + evenPopulation;
    }
}
