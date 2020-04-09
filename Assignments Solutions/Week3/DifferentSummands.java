import java.util.*;

public class DifferentSummands {

    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        int currentItem = 1;
        int sum = 0;
        while (n > 0) {
            int rest=n-currentItem;
            if (rest==0 || rest>currentItem) {
                summands.add(currentItem);
                n-=currentItem;
            }
            currentItem++;
        }
        return summands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}
