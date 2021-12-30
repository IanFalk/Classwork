import java.util.Arrays;
/**
 * Ian Falk
 * https://afteracademy.com/problems/median-of-two-sorted-array-of-same-size
 */
public class eleven_MedianOfTwoArrays {
    public static void main(String args[]) {
        int arr1[] = new int[]{1,14,15,24,35};
        int arr2[] = new int[]{2,13,19,32,47};
        System.out.println("Input: arr1[]= "+ Arrays.toString(arr1)+", arr2[]= "+Arrays.toString(arr2)+"\nOutput: "+findMedian(mergeArrays(arr1,arr2)));
        arr1 = new int[]{1,3,5,11,17};
        arr2= new int[]{9,10,11,13,14};
        System.out.println("Input: arr1[]= "+ Arrays.toString(arr1)+", arr2[]= "+Arrays.toString(arr2)+"\nOutput: "+findMedian(mergeArrays(arr1,arr2)));
        arr1 = new int[]{1,3,5,7,9,11};
        arr2= new int[]{2,4,6,8};
        System.out.println("Input: arr1[]= "+ Arrays.toString(arr1)+", arr2[]= "+Arrays.toString(arr2)+"\nOutput: "+findMedian(mergeArrays(arr1,arr2)));
    }

    //Code copied from my question 10 answer
    public static int[] mergeArrays(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];

        int i=0,j=0,k=0;

        while (i<arr1.length && j<arr2.length) {
            if (arr1[i] < arr2[j]) {
                merged[k] = arr1[i];
                i++;
            } else {
                merged[k] = arr2[j];
                j++;
            }
            k++;
        }

        while (i < arr1.length) {
            merged[k] = arr1[i];
            i++;
            k++;
        }

        while (j < arr2.length) {
            merged[k] = arr2[j];
            j++;
            k++;
        }

        return merged;
    }

    public static double findMedian(int[] arr) {
        double median=0;
        if(arr.length%2==0) {
            median = (arr[arr.length/2]+arr[(arr.length/2)-1])/2.0;
        } else {
            median = arr[arr.length/2];
        }
        return median;
    }
}
