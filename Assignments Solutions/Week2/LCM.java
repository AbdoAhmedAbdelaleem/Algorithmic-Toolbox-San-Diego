import java.util.*;

public class LCM {
  private static long lcm_naive(int a, int b) {
    for (long l = 1; l <= (long) a * b; ++l)
      if (l % a == 0 && l % b == 0)
        return l;

    return (long) a * b;
  }
  private static long lcmEnhanced(int a, int b) {
       return ((long)a*b/gcdEnhanced(a, b));  //(a*b)/gcd(a,b)
  }
 private static int gcdEnhanced(int a, int b) {
      if(a%b==0)
          return b;
      if(b%a==0)
          return a;
      return gcdEnhanced(b, a%b);
  }
  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcmEnhanced(a, b));
  }
}

