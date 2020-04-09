import java.util.Scanner;
import java.util.StringTokenizer;
public class PlacingParentheses {

    private static long getMaximValue(String exp) {
        Object[] arr = parseArthimaticExpression(exp);
        long[] numbers = (long[]) arr[0];
        int n = numbers.length;
        char[] ops = (char[]) arr[1];
        long[][] maxMatrix = new long[numbers.length][numbers.length];
        long[][] minMatrix = new long[numbers.length][numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            minMatrix[i][i] = numbers[i];
            maxMatrix[i][i] = numbers[i];
        }
        for (int i = 1; i <= n; i++) {
                int s=n-i;
            for (int j = s+1; j < n; j++) {
                long[] minMax = minMax(minMatrix, maxMatrix, ops, s, j);
                minMatrix[s][j] = minMax[0];
                maxMatrix[s][j] = minMax[1];
            }
        }
        return maxMatrix[0][n-1];
    }
    private static Object[] parseArthimaticExpression(String exp) {
        Object[] arr = new Object[2];
        StringTokenizer tokenizer = new StringTokenizer(exp, "+-*", true);
        int n = tokenizer.countTokens() / 2 + 1;
        long[] numbers = new long[n];
        char[] ops = new char[n - 1];
        int operationIndex = 0;
        int numbersIndex = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (isOperation(token)) {
                ops[operationIndex++] = token.charAt(0);
            } else {
                numbers[numbersIndex++] = Long.parseLong(token);
            }
        }
        arr[0] = numbers;
        arr[1] = ops;
        return arr;
    }
    private static boolean isOperation(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*");
    }
    private static long[] minMax(long[][] minMatrix, long[][] maxMatrix, char[] op, int i, int j) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long[] minMaxArr = new long[2];
        minMaxArr[0]=min;
        minMaxArr[1]=max;
        for (int k = i; k < j; k++) {
            long maxLeft = maxMatrix[i][k];
            long minLeft = minMatrix[i][k];
            long maxRight = maxMatrix[k + 1][j];
            long minRight = minMatrix[k + 1][j];

            long maxLeftMaxRight = eval(maxLeft, maxRight, op[k]);
            long minLeftMinRight = eval(minLeft, minRight, op[k]);
            long maxLeftMinRight = eval(maxLeft, minRight, op[k]);
            long minLeftMaxRight = eval(minLeft, maxRight, op[k]);
            minMaxArr[0] = getMin(maxLeftMaxRight, minLeftMinRight, maxLeftMinRight, minLeftMaxRight,minMaxArr[0]);
            minMaxArr[1] = getMax(maxLeftMaxRight, minLeftMinRight, maxLeftMinRight, minLeftMaxRight,minMaxArr[1]);
        }
        
        return minMaxArr;
    }
    private static long getMin(long first, long... a) {
        long min = first;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        return min;
    }
    private static long getMax(long first, long... a) {
        long min = first;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > min) {
                min = a[i];
            }
        }
        return min;
    }
    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}
