import java.util.Scanner;
public class Partition3 {
    private static int partition3(int[] A) {
      if(A.length<3)
          return 0;
      int sum=0;
      for (int i = 0; i < A.length; i++) {
          sum+=A[i];
      }
      if(sum%3!=0)
          return 0;
      int W=sum/3;
      if(sum<W)
          return 0;
      int count=0;
        int[][] values = GetOptimalValuesMatrix(A, W);
        for (int i = 1; i < values.length; i++) {
            for (int j = 1; j < values[i].length; j++) {
                if(values[i][j]==W ||(j-A[i-1]>=0 &&  W-A[i-1]==values[i-1][j-A[i-1]]))
                    count++;
            }
        }
        return count>=3?1:0;
    }
  private static int[][] GetOptimalValuesMatrix(int[] w, int W) {
        int[][] values = new int[w.length + 1][W + 1];
        for (int i = 0; i < values.length; i++) {
            values[i][0] = 0;
        }
        for (int i = 0; i < values[0].length; i++) {
            values[0][i] = 0;
        }
        for (int i = 1; i < values.length; i++) {
            for (int j = 1; j < values[i].length; j++) {
                values[i][j] = values[i - 1][j];
                if (j < w[i - 1]) {
                    continue;
                }
                int val = values[i - 1][j - w[i - 1]] + w[i - 1];
                if (val > values[i][j]) {
                    values[i][j] = val;
                }
            }
        }
        return values;
    }
  private static int hasFeasibleSolution(int[] w)
  {
      if(w.length<3)
          return 0;
      int sum=0;
      for (int i = 0; i < w.length; i++) {
          sum+=w[i];
      }
      if(sum%3!=0)
          return 0;
      int W=sum/3;
      if(sum<W)
          return 0;
      
      int count=0;
      int[][]values= GetOptimalValuesMatrix(w,W);
      for (int i = 0; i < values.length; i++) {
          for (int j = 0; j < values[i].length; j++) {
              if(values[i][j]==W)
                  count++;
              if(count>=3)
                  return 1;
          }
      }
      return 0;
  }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

