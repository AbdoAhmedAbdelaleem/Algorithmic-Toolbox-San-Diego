import java.util.*;
public class FibonacciSumLastDigit {
    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }
        private static long getFibonacciSumEnhanced(long n) {
       long m=10;
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
        long l= (int)(n%pattern.size());
        long sum=0;
            for (int j = 0; j <= l; j++) {
                sum+=pattern.get(j);
                sum=sum%10;
            }
            return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumEnhanced(n);
        System.out.println(s);
    }
}
