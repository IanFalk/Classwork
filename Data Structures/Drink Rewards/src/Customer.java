package DrinkRewards;

/*
 *   Ian Falk
 */

public class Customer {

    private String firstName,lastName,guestID;
    private float amountSpent;
    Customer()
    {
        firstName = "";
        lastName = "";
        guestID = "";
        amountSpent = 0;
    }

    Customer(String guestID, String fName, String lName, float amount)
    {
        setGuestID(guestID);
        setFirstName(fName);
        setLastName(lName);
        setAmountSpent(amount);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fName) {
        firstName = fName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lName) {
        lastName=lName;
    }

    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public float getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(float amount) {
            amountSpent=amount;
    }

}
