import java.util.*;
public class Inversions {

    private static long getNumberOfInversions(int[] a, int left, int right) {
        long numberOfInversions = 0;
        if (left >= right) {
            return numberOfInversions;
        }
        int mid = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, left, mid);
        numberOfInversions += getNumberOfInversions(a, mid + 1, right);
        int d = merge(a, left, mid, right);
        numberOfInversions+=d;
        //write your code here
        return numberOfInversions;
    }

    static void StressTestMergeSort() {
        int numOfTestCases = 1000;
        int i = 0;
        while (i < numOfTestCases) {
            Random rnd = new Random();
            int n = rnd.nextInt(1000000);
            int[] arr1 = new int[n];
            int[] arr2 = new int[n];
            for (int j = 0; j < n; j++) {
                arr1[j]=rnd.nextInt(10000);
                arr2[j]=arr1[j];
            }
            Arrays.sort(arr2);
            getNumberOfInversions(arr1, 0, n-1);
            for (int j = 0; j < n; j++) {
                if(arr1[j]!=arr2[j])
                {
                    System.out.println("Bad");
                    return;
                }
            }
            System.out.println("Good");
            i++;
            
        }
    }

    private static int merge(int[] arr, int left, int mid, int righ) {
        int i = left;
        int j = mid + 1;
        int k = 0;
        int count=0;
        int leftArr[] = new int[mid - left + 1];
        int rigthArr[] = new int[righ - mid];
        int[] mergeArr = new int[righ - left + 1];
        while (i <= mid && j <= righ) {
            if (arr[i] <= arr[j]) {
                mergeArr[k++] = arr[i++];
            } else {
                count +=(mid-i+1);
                mergeArr[k++] = arr[j++];
            }
        }
        while (i <= mid) {
            mergeArr[k++] = arr[i++];
        }
        while (j <= righ) {
            mergeArr[k++] = arr[j++];
        }
        for (int l = left; l <= righ; l++) {
            arr[l] = mergeArr[l - left];
        }
        return count;
    }

    static void PrintArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "   ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
       // StressTestMergeSort();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(getNumberOfInversions(a, 0, a.length - 1));
        //PrintArray(a);
    }
}
