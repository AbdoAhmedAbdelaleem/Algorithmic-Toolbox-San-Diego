package Week5;

import java.util.Random;
import java.util.Scanner;
public class ChangeDP {

    private static int getChange(int m, int[] currencies) {
        int[] minCoins = new int[m + 1];
        minCoins[0] = 0;
        for (int i = 1; i <= m; i++) {
            int minValue = Integer.MAX_VALUE;
            for (int j = 0; j < currencies.length; j++) {
                if (currencies[j] <= i) {
                    int val = minCoins[i - currencies[j]] + 1;
                    if (val < minValue) {
                        minValue = val;
                    }
                }
            }
            minCoins[i] = minValue;

        }
        return minCoins[m];
    }

    private static int getChangeRecursion(int m, int[] currencies) {

        if (m == 0) {
            return m;
        }
        int minValue = Integer.MAX_VALUE;
        for (int j = 0; j < currencies.length; j++) {
            if (currencies[j] <= m) {
                int val = getChangeRecursion(m - currencies[j], currencies) + 1;
                if (val < minValue) {
                    minValue = val;
                }
            }
        }
        return minValue;
    }

    public static void StressTest() {
        int j = 1;
        int[] currencies = {1, 3, 4};
        Random rnd = new Random();
        while (j < 500) {
            j++;
            int num = rnd.nextInt(20);
            int res1 = getChange(num, currencies);
            int res2 = getChangeRecursion(num, currencies);
            if (res1 == res2) {
                System.out.println(num + "   is Good");
            } else {
                System.out.println(num + "   is Bad");
                break;
            }
        }
    }

    public static void main(String[] args) {
        //StressTest();
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int[] currencies = {1, 3, 4};
        System.out.println(getChange(m, currencies));

    }
}
