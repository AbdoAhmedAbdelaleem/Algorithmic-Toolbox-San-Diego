package Week2;

import java.util.*;
public class FibonacciHuge {

    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1) {
            return n;
        }

        long previous = 0;
        long current = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }

    private static long getFibonacciHugeEnhanced(long n, long m) {
        if (n < 2) {
            return n % m;
        }
        ArrayList<Long> pattern = new ArrayList<>();
        pattern.add(0l);
        pattern.add(1l);
        long prev=0;
        long current=1;
        int i=2;
        // mod(fib(n))=mod(mod(fib(n-1))+mod(fib(n-2))
        while(true) {
            long next=(prev+current)%m;
            prev=current;
            current=next;
            next%=m;
            if(next==1&&pattern.get(i-1)==0)
                break;
            else 
                pattern.add(next);
            i++;
        }
        pattern.remove(pattern.size()-1);
        return pattern.get((int)(n%pattern.size()));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHugeEnhanced(n, m));
    }
}
