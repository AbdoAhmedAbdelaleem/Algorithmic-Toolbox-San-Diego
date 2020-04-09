import java.util.*;

public class LargestNumber {

    private static String largestNumber(String[] a) {
        Arrays.sort(a,new StringNumberComparator());
        String result = "";
        for (int i = 0; i < a.length; i++) {
            result += a[i];
        }
        return result;
    }

    static class StringNumberComparator implements Comparator<String> {

        public int compare(String c1, String c2) {
            String s1 = c1 + c2;
            String s2 = c2 + c1;
            int s1Number = Integer.parseInt(s1);
            int s2Number = Integer.parseInt(s2);
            if (s1Number > s2Number) {
                return -1;
            } else if (s1Number < s2Number) {
                return 1;
            }
            return 0;

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}
