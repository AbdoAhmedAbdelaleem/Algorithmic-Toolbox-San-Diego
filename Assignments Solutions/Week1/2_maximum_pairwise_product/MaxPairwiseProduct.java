
import java.util.*;
import java.io.*;
import static java.lang.Integer.max;

public class MaxPairwiseProduct {

    static int getMaxPairwiseProduct(int[] numbers) {
        int max_product = 0;
        int n = numbers.length;

        for (int first = 0; first < n; ++first) {
            for (int second = first + 1; second < n; ++second) {
                max_product = max(max_product,
                        numbers[first] * numbers[second]);
            }
        }

        return max_product;
    }

    static long getMaxPairwiseProductFasterSolution(int[] numbers) {
        long max_product = 0;
        int n = numbers.length;
        int firstMaxIndex = 0;
        for (int i = 0; i < n; i++) {
            if(numbers[i]>numbers[firstMaxIndex])
                firstMaxIndex=i;
        }        
        int secondMaxIndex =firstMaxIndex==0?1:0;//get any other index not equal to first max index
        
    for (int i = 0; i < n; i++) {
            if(i!=firstMaxIndex && numbers[i]>numbers[secondMaxIndex])
                secondMaxIndex=i;
        }
       return ((long)numbers[firstMaxIndex])*numbers[secondMaxIndex];

    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProductFasterSolution(numbers));
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
