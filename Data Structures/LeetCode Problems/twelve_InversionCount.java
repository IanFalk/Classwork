/**
 * Ian Falk
 * https://afteracademy.com/problems/inversion-count-in-an-array
 */
public class twelve_InversionCount {
    public static void main(String args[]) {
        int[] arr = new int[]{4,1,3,2};
        System.out.println("Number of inversions: "+numOfInversions(arr));
        arr = new int[]{4,3,2,1};
        System.out.println("Number of inversions: "+numOfInversions(arr));
        arr = new int[]{1,2,3,4};
        System.out.println("Number of inversions: "+numOfInversions(arr));
    }

    public static int numOfInversions(int[] arr) {
        int count=0;
        for(int i=0; i<arr.length-1; i++) {
            //If i so greater than i+1, swap the elements
            if(arr[i] > arr[i+1]) {
                int temp = arr[i];
                arr[i]=arr[i+1];
                arr[i+1]=temp;
                count++;
            }
        }
        if(count==0) {
            return count;
        }
        count = count + numOfInversions(arr);
        return count;
    }
}
