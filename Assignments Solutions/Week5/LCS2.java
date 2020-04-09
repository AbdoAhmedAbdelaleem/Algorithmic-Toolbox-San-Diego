import java.util.*;
public class LCS2 {

    private static int lcs2(int[] a, int[] b) {
        int aLength = a.length;
        int bLength = b.length;
        int[][] editMatrix = new int[bLength + 1][];
        for (int i = 0; i < editMatrix.length; i++) {
            editMatrix[i] = new int[aLength + 1];
        }
        for (int i = 0; i < editMatrix[0].length; i++) {
            editMatrix[0][i] = 0;
        }
        for (int i = 0; i < editMatrix.length; i++) {
            editMatrix[i][0] = 0;
        }
        int max = 0;
        for (int i = 1; i < editMatrix.length; i++) {
            for (int j = 1; j < editMatrix[i].length; j++) {
                if (a[j - 1] == b[i - 1]) {
                    editMatrix[i][j] = editMatrix[i - 1][j - 1] + 1;
               } else {
                    editMatrix[i][j] = Math.max(editMatrix[i - 1][j], editMatrix[i][j - 1]);
                  
                }
            }
        }
        return editMatrix[b.length][a.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(lcs2(a, b));
    }
}
