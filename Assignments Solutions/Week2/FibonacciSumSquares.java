/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Week2;
import java.util.*;

/**
 *
 * @author Asmaa
 */
public class FibonacciSumSquares {
    private static long getFibonacciSumSquaresNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current * current;
        }

        return sum % 10;
    }
      private static long getFibonacciSumSquaresEnhanced(long n) {
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
                sum+=Math.pow(pattern.get(j),2);
                sum=sum%10;
            }
            return sum;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumSquaresEnhanced(n);
        System.out.println(s);
    }
}
