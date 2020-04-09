import java.util.Scanner;


public class Fibonacci {
  private static long calc_fib(int n) {
    if (n <= 1)
      return n;

    return calc_fib(n - 1) + calc_fib(n - 2);
  }
  private static long calcFibEnhanced(int n)
  {
      if(n<=1)
          return n;
      long current=0;
      long next=1;
      long sum;
      for (int i = 2; i <= n; i++) {
          sum=current+next;
          current=next;
          next=sum;
      }
      return next;
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calcFibEnhanced(n));
  }
}

