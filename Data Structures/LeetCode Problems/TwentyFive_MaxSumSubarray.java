import java.util.Arrays;

/**
 * Ian Falk
 * https://afteracademy.com/problems/maximum-subarray-sum
 */
public class TwentyFive_MaxSumSubarray {
    public static void main(String args[]) {
        int[] arr = new int[]{-5,8,9,-6,10,-15,3};
        System.out.println("Input: "+ Arrays.toString(arr)+", Output: "+maxSum(arr));
        arr = new int[]{-4,-7,-1,5,-2};
        System.out.println("Input: "+ Arrays.toString(arr)+", Output: "+maxSum(arr));
        arr = new int[]{1,6,2,4};
        System.out.println("Input: "+ Arrays.toString(arr)+", Output: "+maxSum(arr));
        arr = new int[]{-1,-2,-3,-4};
        System.out.println("Input: "+ Arrays.toString(arr)+", Output: "+maxSum(arr));
    }

    //Modified algorithm from #15
    //Assumes smallest subarray can contain only 1 item
    public static int maxSum(int[] arr) {
        int max=0;
        for(int i=0; i<arr.length; i++) {
            int sum=0;
            for(int j=i; j<arr.length; j++) {
                sum += arr[j];
                if(sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }
}
