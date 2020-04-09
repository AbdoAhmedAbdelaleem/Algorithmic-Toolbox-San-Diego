import java.util.*;
public class FibonacciPartialSum {

    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;

        long current = 0;
        long next = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current;
            }

            long new_current = next;
            next = next + current;
            current = new_current;
        }

        return sum % 10;
    }

    private static long getFibonacciPartialSumEnhanced(long from, long to) {
        long m = 10;
        ArrayList<Long> pattern = new ArrayList<>();
        pattern.add(0l);
        pattern.add(1l);
        long prev = 0;
        long current = 1;
        int i = 2;
        // mod(fib(n))=mod(mod(fib(n-1))+mod(fib(n-2))
        while (true) {
            long next = (prev + current) % m;
            prev = current;
            current = next;
            next %= m;
            if (next == 1 && pattern.get(i - 1) == 0) {
                break;
            } else {
                pattern.add(next);
            }
            i++;
        }
        pattern.remove(pattern.size() - 1);
        int l = (int) (from % pattern.size());
        int k = (int) (to % pattern.size());
        if(k<l)
        {
            k=k+pattern.size();
        }
        long sum = 0;
        for (int j = l; j <= k; j++) {
            sum += pattern.get(j%pattern.size());
            sum = sum % 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSumEnhanced(from, to));
    }
}
