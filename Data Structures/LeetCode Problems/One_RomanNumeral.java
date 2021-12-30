/**
 * Ian Falk
 * https://afteracademy.com/problems/roman-to-integer
 */
public class One_RomanNumeral {

    public static void main(String args[]) {
        String numeral;
        numeral = "IX";
        System.out.println("Input: "+numeral + ". Output: "+romanToDecimal(numeral));
        numeral = "IV";
        System.out.println("Input: "+numeral + ". Output: "+romanToDecimal(numeral));
        numeral = "XVII";
        System.out.println("Input: "+numeral + ". Output: "+romanToDecimal(numeral));
        numeral = "XC";
        System.out.println("Input: "+numeral + ". Output: "+romanToDecimal(numeral));
        numeral = "VII";
        System.out.println("Input: "+numeral + ". Output: "+romanToDecimal(numeral));
    }

    public static int value(char numeral) {
        switch(numeral) {
            default:
                return -1;
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return  10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
    }

    public static int romanToDecimal(String roman) {
        int result=0;

        for(int i=0; i<roman.length(); i++) {
            int one = value(roman.charAt(i));

            //Ensure this value isnt the end of the string
            if(i+1 < roman.length()) {
                int two = value(roman.charAt(i+1));

                //If the current symbol is greater than the next one, means we don't have one of the six special cases.
                //Can simply add the value of the current numeral to the result.
                if(one >= two) {
                    result = result + one;
                }
                //The current symbol would be less than the next one, which means one of our special cases exists.
                //Means we add to the result the value of the second numeral minus the first.
                else {
                    result = result + (two-one);
                    i++;
                    //Move i forward again as we have evaluated i and i+1 already.
                }
            } else {
                result = result + one;
            }
        }
        return result;
    }
}
