import java.util.Arrays;

/**
 * Ian Falk
 * https://afteracademy.com/problems/coin-change-problem
 */
public class Five_CoinChangeProblem {
    public static void main(String args[]) {
        int[] arr = {1,2,3,4};
        int amount = 11;
        System.out.println("Input Array: "+ Arrays.toString(arr) + " Input Amount: "+amount+" Output: "+combination(arr,amount));
        arr = new int[]{3,4};
        amount = 5;
        System.out.println("Input Array: "+ Arrays.toString(arr) + " Input Amount: "+amount+" Output: "+combination(arr,amount));
        arr = new int[]{1,5,10,25};
        amount = 100;
        System.out.println("Input Array: "+ Arrays.toString(arr) + " Input Amount: "+amount+" Output: "+combination(arr,amount));
    }

    public static int combination(int[] arr, int amount) {
        int i=arr.length-1;
        int count=0;
        while(amount > 0 && i>=0) {
            if(arr[i] > amount) {
                i--;
            } else {
                amount = amount -arr[i];
                count++;
            }
        }

        if(amount >0) {
            return -1;
        }

        return count;
    }
}
