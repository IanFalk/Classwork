package DrinkRewards;
/*
 *   Ian Falk
 */

public class platinumCustomer extends Customer {
    private String firstName, lastName, guestID;
    private float amountSpent;
    private int bonusBucks;

    platinumCustomer() {
        firstName = "";
        lastName = "";
        guestID = "";
        amountSpent = 0;
        bonusBucks = 0;
    }

    platinumCustomer(String guestID, String fName, String lName, float amount, int bonusBucks) {
        setGuestID(guestID);
        setFirstName(fName);
        setLastName(lName);
        setAmountSpent(amount);
        setBonusBucks(bonusBucks);
    }

    public int getBonusBucks() {
        return bonusBucks;
    }

    public void setBonusBucks(int bonusBucks) {
        this.bonusBucks = bonusBucks;
    }

}
