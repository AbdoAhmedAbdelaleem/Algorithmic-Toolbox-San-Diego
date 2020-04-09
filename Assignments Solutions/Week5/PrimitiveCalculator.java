import java.lang.reflect.Array;
import java.util.*;
public class PrimitiveCalculator {

    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    private static List<Integer> optimalSequanceDynamicProgramming(int n) {
       
        ArrayList<Integer>returnedData=new ArrayList<>();
        int[] minNumbers = new int[n + 1];
        minNumbers[0] = 0;
        minNumbers[1] = 0;
        for (int i = 2; i <= n; i++) {
            int minNumber = Integer.MAX_VALUE;
            int triple = Integer.MAX_VALUE;
            int twice = Integer.MAX_VALUE;
            int plus;
            if (i % 3 == 0) {
                triple = minNumbers[i / 3];
            }
            if (i % 2 == 0) {
                twice = minNumbers[i / 2];
            }
            plus = minNumbers[i - 1];
            minNumbers[i] = Math.min(Math.min(triple, twice), plus) + 1;
        }
        returnedData.add(n);
        while (n > 1) {
            int minValue = Integer.MAX_VALUE;
            int triple = Integer.MAX_VALUE;
            int twice = Integer.MAX_VALUE;
            int plus;
            if (n % 3 == 0) {
                triple=minNumbers[n/3];
            }
             if (n % 2 == 0) {
                twice=minNumbers[n/2];
            }
             plus=minNumbers[n-1];
             if(triple<=twice &&triple<=plus)
             {
                 n=n/3;
             }
             else if(twice<=triple &&twice<=plus)
             {
                 n=n/2;
             }
             else
             {
                 n=n-1;
             }
             returnedData.add(n);
        }
        Collections.reverse(returnedData);
        return returnedData;
    }

    /**/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> optimalSequanceDynamicProgramming = optimalSequanceDynamicProgramming(n);
        List<Integer> sequence = optimalSequanceDynamicProgramming(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}
