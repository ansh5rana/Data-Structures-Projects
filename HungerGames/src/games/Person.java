package games;

public class Person implements Comparable<Person> {
    private String  firstName;
    private String  lastName;
    private int     birthMonth;
    private int     age;
    private int     districtID;      // the district the person belongs to
    private boolean tessera;         // if true this person has volunteered to participage in the games
    private int     effectiveness;   // the higher the value the higher the chances of winning a duel

    
    public Person(int birthMonth, String firstName, String lastName, int age, int districtID, int effectiveness)
            throws IllegalArgumentException {
        if (birthMonth <= 0 ||birthMonth > 12)
            throw new IllegalArgumentException(
                    "Birth month must be a valid month. \n"
                            + "Got instead: " + birthMonth);
        this.birthMonth = birthMonth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.districtID = districtID;
        this.effectiveness = effectiveness;
    }

    
    public int getBirthMonth() {
        return birthMonth;
    }

    
    public String getFirstName() {
        return firstName;
    }

    
    public String getLastName() {
        return lastName;
    }

    
    public int getAge() {
        return age;
    }

    
    public int getDistrictID() {
        return districtID;
    }

    
    public int getEffectiveness() {
        return effectiveness;
    }

    
    public void setTessera(boolean tessera) {
        this.tessera = tessera;
    }

    
    public boolean getTessera() {
        return tessera;
    }

    
    public void grow() {
        age++;
    }


    
    public String toString() {
        return firstName + " " + lastName;
    }

    
    public Person duel(Person person2) {

        double luckPerson1 = StdRandom.uniform(0, 2.0);
        double luckPerson2 = StdRandom.uniform(0, 2.0);

        double person1Effectiveness = luckPerson1 * this.effectiveness;
        double person2Effectiveness = luckPerson2 * person2.getEffectiveness();

        if ( person1Effectiveness > person2Effectiveness ) {
            return this;
        } else if ( person1Effectiveness < person2Effectiveness ) {
            return person2;
        } else {
            
            double chance = StdRandom.uniform(0, 1.0);
            return ( chance < 0.50) ? this : person2;
        }
    }

    public int compareTo(Person person2) {
        Integer person1ID = districtID;
        Integer person2ID = person2.getDistrictID();

        return (person1ID.compareTo(person2ID) != 0 ? person1ID.compareTo(person2ID)
                : toString().compareTo(person2.toString()));
    }

}
