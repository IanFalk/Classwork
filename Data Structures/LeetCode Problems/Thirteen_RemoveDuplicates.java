import java.util.Arrays;

/**
 * Ian Falk
 * https://afteracademy.com/problems/remove-duplicates-from-sorted-array
 */
public class Thirteen_RemoveDuplicates {
    public static void main(String args[]) {
        int[] arr = new int[]{-1,0,1,1,2,9,9};
        System.out.println("Input arr[] = "+Arrays.toString(arr)+", Output: "+removeDuplicates(arr));
        arr = new int[]{-10,1,3,6,6,6,6,10};
        System.out.println("Input arr[] = "+Arrays.toString(arr)+", Output: "+removeDuplicates(arr));
        arr = new int[]{10,20,30,33,45};
        System.out.println("Input arr[] = "+Arrays.toString(arr)+", Output: "+removeDuplicates(arr));
        arr = new int[]{1,2,2,3,3,3,4,4,4,4,5,5,5,5,5,6,6,6,6,6,6};
        System.out.println("Input arr[] = "+Arrays.toString(arr)+", Output: "+removeDuplicates(arr));
    }

    public static int removeDuplicates(int[] arr) {
        int j=0;
        int newLength=0;
        for(int i=0; i<arr.length-1; i++) {
            if(arr[i] != arr[i+1]) {
                arr[j++]=arr[i];
                newLength++;
            }
        }
        arr[j++]=arr[arr.length-1];
        newLength++;

        int[] result = new int[newLength];
        for(int k=0; k<newLength; k++) {
            result[k] = arr[k];
        }
        return result.length;
    }
}
