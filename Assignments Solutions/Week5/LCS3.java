import java.util.*;
public class LCS3 {

    private static int lcs2(int[] a, int[] b, int[] c) {
        int aLength = a.length;
        int bLength = b.length;
        int cLength = c.length;
        int[][][] editMatrix = new int[cLength + 1][][];
        for (int i = 0; i < editMatrix.length; i++) {
            editMatrix[i] = new int[b.length + 1][a.length + 1];

        }
        for (int i = 0; i < editMatrix.length; i++) {
            for (int j = 0; j < editMatrix[0][0].length; j++) {

                editMatrix[i][0][j] = 0;
            }
        }
        for (int i = 0; i < editMatrix.length; i++) {
            for (int j = 0; j < editMatrix[0].length; j++) {

                editMatrix[i][j][0] = 0;
            }
        }
        int max = 0;
        for (int i = 1; i < editMatrix.length; i++) {
            for (int j = 1; j < editMatrix[i].length; j++) {
                for (int k = 1; k < editMatrix[i][j].length; k++) {
                    
                    if(i==6&&j==7)
                    {
                        int m=5;
                                m++;
                        System.out.println("");
                    }
                    if (b[j - 1] == a[k - 1]&&b[j-1] == c[i - 1]) {
                        editMatrix[i][j][k] = editMatrix[i - 1][j - 1][k-1] + 1;
                    } else {
                        editMatrix[i][j][k] = Math.max(Math.max(editMatrix[i - 1][j][k], editMatrix[i][j - 1][k]),editMatrix[i][j][k-1]);

                    }
                }
            }
        }
        return editMatrix[c.length][b.length][a.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs2(a, b, c));
    }
}
