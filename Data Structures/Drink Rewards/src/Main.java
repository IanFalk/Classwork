package DrinkRewards;
/*
 *   Ian Falk
 */

import java.io.*;
import java.util.*;

public class Main {
    private static Customer[] customers, preferredCustomers;
    public static void main(String[] args) throws IOException {
        //Creates an array for regular customers
        Scanner scan = new Scanner(new File("customers.dat"));
        customers = new Customer[0];
        while(scan.hasNext())
        {
            Customer guest = new Customer(scan.next(), scan.next(), scan.next(), scan.nextFloat());
            customers = resize(customers, guest);
        }
        scan.close();

        //Attempts to read a file for preferred customers, if it doesn't exist it will just skip to taking orders
        try {
            scan = new Scanner(new File("preferred.dat"));
            preferredCustomers = new Customer[0];
            String decider = "";
            while(scan.hasNext()) {
                Customer guest = new Customer(scan.next(), scan.next(), scan.next(), scan.nextFloat());
                decider = scan.next();
                //Decider is used to determine if the person is a gold or platinum customer
                if(decider.indexOf("%") > 0) {
                    decider = decider.substring(0,decider.length()-1);
                    Customer goldGuest = new goldCustomer(guest.getGuestID(), guest.getFirstName(), guest.getLastName(), guest.getAmountSpent(), Integer.parseInt(decider));
                    preferredCustomers = resize(preferredCustomers, goldGuest);
                }
                else {
                    Customer platinumGuest = new platinumCustomer(guest.getGuestID(), guest.getFirstName(), guest.getLastName(), guest.getAmountSpent(), Integer.parseInt(decider));
                    preferredCustomers = resize(preferredCustomers, platinumGuest);
                }
            }
            scan.close();

        } catch (FileNotFoundException e) {
            //This will only happen if no preferred customers exist, and will move on to take orders
        }

        getOrders(scan);

    }

    public static Customer[] resize(Customer[] input, Customer guest)
    {
        //If the preferred customers array hasnt been created yet then it is now
        if(preferredCustomers == null)
        {
            preferredCustomers = new Customer[0];
        }
        //Copies the array and extends the length by 1
        Customer[] updated = Arrays.copyOf(input, input.length+1);
        updated[updated.length-1] = guest;
        return updated;
    }

    public static void getOrders(Scanner scan) throws IOException {
        String guestID = "";
        String type = "";
        char size = ' ';
        float cpi = 0;
        int num=0;
        scan = new Scanner(new File("orders.dat"));

        while (scan.hasNextLine()) {
            //This code segment is in a try-catch block so that if there is any invalid input it will skip past it
            try {
                guestID = scan.next();

                for (Customer custom : customers) {
                    if (guestID.equals(custom.getGuestID())) {
                        size = scan.next().charAt(0);

                        if (size == 'S' || size == 'M' || size == 'L') {
                            type = scan.next();

                            if (type.equals("soda") || type.equals("tea") || type.equals("punch")) {
                                cpi = scan.nextFloat();
                                num = scan.nextInt();
                            }
                        }

                    }
                }
                for (Customer custom : preferredCustomers) {
                    if (guestID.equals(custom.getGuestID())) {
                        size = scan.next().charAt(0);

                        if (size == 'S' || size == 'M' || size == 'L') {
                            type = scan.next();

                            if (type.equals("soda") || type.equals("tea") || type.equals("punch")) {
                                cpi = scan.nextFloat();
                                num = scan.nextInt();
                            }
                        }

                    }
                }
            } catch (InputMismatchException e) {
                scan.nextLine();
            }
            for(Customer custom: customers)
            {
                if(custom.getGuestID().equals(guestID)) {
                    //If the customers total will be above $50, promote them
                    if (custom.getAmountSpent() + calculateCost(size, type, cpi, num) >= 50) {
                        promote(custom,0);
                    } else {
                        custom.setAmountSpent(custom.getAmountSpent() + calculateCost(size, type, cpi, num));
                    }
                }
            }
            //If the customer is already a preferred customer this will apply their discount or bonus bucks
            for(Customer custom : preferredCustomers) {
                if(custom.getGuestID().equals(guestID)) {
                    if(custom instanceof goldCustomer) {
                        discount(custom, calculateCost(size, type, cpi, num));
                    }
                    if(custom instanceof platinumCustomer) {
                        bonusBucks(custom, calculateCost(size, type, cpi, num));
                    }
                }
            }
        }
        writeFile();
    }


    //Surface area of a cylinder (not including faces) A = 2*pi*r*h
    public static float calculateCost(char size, String type, float cpi, int num)
    {
        /*
            Soda costs $0.20 per oz.
            Tea costs $0.12 per oz.
            Fruit Punch costs $0.15 per oz.
         */
        float total=0,personalizationPrice=0,drinkPrice=0,ounces=0,ppo=0,d=0,h=0;

        if(size == 'S') {
            ounces = 12;
            d = 4;
            h = 4.5f;
        } else if(size == 'M') {
            ounces = 20;
            d = 4.5f;
            h = 5.75f;
        } else if(size == 'L') {
            ounces = 32;
            d=5.5f;
            h=7;
        }

        if(type == "Soda") {
            ppo = 0.2f;
        } else if(type == "Tea") {
            ppo = 0.12f;
        } else if(type == "Fruit punch") {
            ppo = 0.15f;
        }

        drinkPrice = ounces * ppo;
        personalizationPrice = (float)(2*Math.PI*(d/2)*h)*cpi;
        total = num*(drinkPrice+personalizationPrice);
        return total;
    }

    public static void discount(Customer custom, float amount) {
        //This variable is used to store how much was spent BEFORE a discount is applied
        float actualAmountSpent = amount;
        //This variable is the amount AFTER the discount is applied
        float discountedAmount = actualAmountSpent*((float)((goldCustomer)custom).getDiscount()/100);

        if(custom.getAmountSpent()+discountedAmount < 100)
        {
            actualAmountSpent=discountedAmount;
            custom.setAmountSpent(actualAmountSpent+custom.getAmountSpent());
        }
        //If the amount spent is above 100, but still below 150 then it will set their discount to the next tier (10%)
        else if(custom.getAmountSpent() + discountedAmount >= 100 && custom.getAmountSpent() + discountedAmount <150)
        {
            ((goldCustomer)custom).setDiscount(10);
            actualAmountSpent = actualAmountSpent*((float)((goldCustomer)custom).getDiscount()/100);
            custom.setAmountSpent(custom.getAmountSpent() + actualAmountSpent);

        }
        //If the amount is above 150 but still below 200 then it will set their discount to the next tier (15%)
        else if(custom.getAmountSpent()+discountedAmount >= 150 && custom.getAmountSpent() + discountedAmount <200)
        {
            ((goldCustomer)custom).setDiscount(15);
            actualAmountSpent = actualAmountSpent*((float)((goldCustomer)custom).getDiscount()/100);
            custom.setAmountSpent(custom.getAmountSpent() + actualAmountSpent);
        } else { //Should only happen if the amount >= 200
            ((goldCustomer)custom).setDiscount(15);
            actualAmountSpent = actualAmountSpent*((float)((goldCustomer)custom).getDiscount()/100);
        }
        //This will run after their discount is updated/applied, and if their amount spent is then above $200 it will promote them to platinum status
        if(custom.getAmountSpent() + actualAmountSpent >= 200)
        {
            promote(custom,actualAmountSpent);
        }
    }

    public static void bonusBucks(Customer custom, float amount) {
        int bonusBucks = ((platinumCustomer)(custom)).getBonusBucks();
        //This will subtract as many bonus bucks as possible without making the total go negative
        while(amount > 0 && ((platinumCustomer)custom).getBonusBucks() > 0)
        {
            amount = amount -1;
            bonusBucks--;
            ((platinumCustomer)custom).setBonusBucks(bonusBucks);
        }

        //This will award a bonus buck for every $5 spent over $200
        for(int i=0; i<=amount; i++)
        {
            if((int)custom.getAmountSpent()+i > 200) {
                if ((int) (custom.getAmountSpent() + i) % 5 == 0) {
                    bonusBucks++;
                }
            }
        }

        custom.setAmountSpent(amount+custom.getAmountSpent());
        ((platinumCustomer)custom).setBonusBucks(bonusBucks);
    }
    public static void promote(Customer custom, float amountSpent) {
        //If the customer is already a gold customer, it recreates it as a platinum customer
        if(custom instanceof goldCustomer)
        {
            Customer newCustomer = new platinumCustomer(custom.getGuestID(), custom.getFirstName(), custom.getLastName(), custom.getAmountSpent(), 0);
            for(int i=0; i<preferredCustomers.length;i++)
            {
                if(preferredCustomers[i].getGuestID().equals(custom.getGuestID())) {
                    preferredCustomers[i] = newCustomer;
                    bonusBucks(preferredCustomers[i], amountSpent);
                }
            }
        }
        //Otherwise when it is a regular customer, it sets the index in the old array to null so that it can be easily removed by the desize() method.
        //Also expands the preferred customer array with the guest info
        else { //Sets customer to null to be removed later, and adds the customer to the preferredCustomers array
            Customer newCustomer = new goldCustomer(custom.getGuestID(), custom.getFirstName(), custom.getLastName(), custom.getAmountSpent(), 5);
            for(int i=0; i<customers.length; i++)
            {
                if(customers[i].getGuestID().equals(custom.getGuestID())) {
                    customers[i] = null;
                    preferredCustomers = resize(preferredCustomers, newCustomer);
                    customers = desize(customers);
                }
            }

        }
    }

    //Looks through an array for an object to be 'null' and if so it recreates the array with length-1, and retains the same order as before.
    public static Customer[] desize(Customer[] input)
    {
        Customer[] updated = new Customer[input.length-1];
        int counter = 0;

        for(int j=0; j<updated.length; j++) {
            if (input[j] == null) {
                counter = 1;
            }
            updated[j] = input[j + counter];
        }
        return updated;
    }

    //Writes everything back to the file
    public static void writeFile() throws IOException {
        FileWriter writer = new FileWriter("customers.dat");
        for (Customer custom : customers) {
            writer.write(custom.getGuestID() + " ");
            writer.write(custom.getFirstName() + " ");
            writer.write(custom.getLastName() + " ");
            writer.write(Float.toString(custom.getAmountSpent())+'\n');
        }
        writer.close();

        writer = new FileWriter("preferred.dat");
        for (Customer custom : preferredCustomers) {
            writer.write(custom.getGuestID() + " ");
            writer.write(custom.getFirstName() + " ");
            writer.write(custom.getLastName() + " ");
            writer.write(custom.getAmountSpent() + " ");
            if(custom instanceof goldCustomer) {
                writer.write(((goldCustomer) custom).getDiscount()+"%\n");
            } else if(custom instanceof platinumCustomer) {
                writer.write(((platinumCustomer) custom).getBonusBucks() + "\n");
            }
        }

        writer.close();

    }

}
