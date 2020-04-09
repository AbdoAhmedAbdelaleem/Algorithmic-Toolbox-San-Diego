import java.util.Arrays;
import java.util.Scanner;
public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        Arrays.sort(starts);
        Arrays.sort(ends);
        int n=starts.length;
        for (int i = 0; i < points.length; i++) {
            int indeStartAfter = GetNextElement(starts, points[i]);
            int indeEndBefore = GetPreviousElement(ends, points[i]);
            if(indeStartAfter<0)
                indeStartAfter=n;
            int countStartAfter=n-indeStartAfter;
            int countEndbefore=indeEndBefore+1;
           
            cnt[i] =  n-(countStartAfter+countEndbefore);
        }

        //write your code here
        return cnt;
    }

   private static int GetPreviousElement(int[] arr, int target) {
        int previous = -1;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target <= arr[mid]) {
                right  = mid - 1;
            } else if (target > arr[mid]) {
                left = mid + 1;
                previous = mid;
            }
        }
        return previous;
    }

    private static int GetNextElement(int[] arr, int target) {
        int next = -1;
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target < arr[mid]) {
                right  = mid - 1;
                next = mid;
            } else if (target >= arr[mid]) {
                left = mid + 1;
            }
        }
        return next;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}
