package DrinkRewards;
/*
 *   Ian Falk
 */

public class goldCustomer extends Customer {
    private String firstName, lastName, guestID;
    private float amountSpent;
    private int discount;

    goldCustomer() {
        firstName = "";
        lastName = "";
        guestID = "";
        amountSpent=0;
        discount=0;
    }

    goldCustomer(String guestID, String fName, String lName, float amount, int discount) {
        setGuestID(guestID);
        setFirstName(fName);
        setLastName(lName);
        setAmountSpent(amount);
        setDiscount(discount);
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = 100-discount;
    }
    
}
