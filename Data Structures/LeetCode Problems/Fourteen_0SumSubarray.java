import java.util.Arrays;

/**
 * Ian Falk
 * https://afteracademy.com/problems/largest-subarray-with-0-sum
 */
public class Fourteen_0SumSubarray {
    public static void main(String args[]) {
        int[] arr = new int[]{15,-2,0,-8,3,7,10,23};
        System.out.println("Input: "+ Arrays.toString(arr)+", Output: "+subarray(arr));
        arr = new int[]{1,2,3};
        System.out.println("Input: "+ Arrays.toString(arr)+", Output: "+subarray(arr));
        arr = new int[]{10,2,3,0,-15,2,-2};
        System.out.println("Input: "+ Arrays.toString(arr)+", Output: "+subarray(arr));
        arr = new int[]{1,1,1,1,1,-1,-1,-1,-1,-1};
        System.out.println("Input: "+ Arrays.toString(arr)+", Output: "+subarray(arr));
    }

    public static int subarray(int[] arr) {
        int length=0;
        for(int i=0; i<arr.length; i++) {
            int sum=0;
            for(int j=i; j<arr.length; j++) {
                sum += arr[j];
                if (sum == 0) {
                    if (j - i > length) {
                        length = j - i+1;
                    }
                }
            }
        }
        return length;
    }
}
