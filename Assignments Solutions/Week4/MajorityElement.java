import java.util.*;
import java.io.*;
public class MajorityElement {

    private static boolean hasMajorUsingMap(int[] a) {
        HashMap<Integer, Integer> ma = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (!ma.containsKey(a[i])) {
                ma.put(a[i], 1);
            } else {
                ma.put(a[i], ma.get(a[i]) + 1);
            }
            if (ma.get(a[i]) > a.length / 2) {
                return true;
            }
        }
        return false;
    }

    private static int getMajorityElement(int[] a, int left, int right) {
        if (left == right || left + 1 == right) {
                return a[left];
        }
        int mid = left + (right - left) / 2;
        int majorLeft = getMajorityElement(a, left, mid);
        int majorRight = getMajorityElement(a, mid + 1, right);
        int n = right - left + 1;
        int halfN=n/2;
        if (majorLeft == -1) {
                 if(countElement(a, left, right, majorRight)<=halfN)
                   return -1;
            return majorRight;
        }
        if (majorRight == -1) {
                  if(countElement(a, left, right, majorLeft)<=halfN)
                return -1;
            return majorLeft;
        }
        int countMajorLeft = countElement(a, left, right, majorLeft);
        int countMajorRight = countElement(a, left, right, majorRight);
        int max=Math.max(countMajorLeft, countMajorRight);
        if(max<=halfN)
          return -1;
        if (countMajorLeft >= countMajorRight) {
            return majorLeft;
        }
        return majorRight;
    }

    static int countElement(int[] arr, int left, int right, int element) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (arr[i] == element) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length - 1)!=-1) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static class FastScanner {

        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
