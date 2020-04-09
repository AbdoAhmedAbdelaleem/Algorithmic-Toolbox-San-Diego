import java.util.*;
class EditDistance {

    public static int EditDistance(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        int[][] editMatrix = new int[tLength + 1][];
        for (int i = 0; i < editMatrix.length; i++) {
            editMatrix[i] = new int[sLength + 1];
        }
        for (int i = 0; i < editMatrix[0].length; i++) {
            editMatrix[0][i] = i;
        }
        for (int i = 0; i < editMatrix.length; i++) {
            editMatrix[i][0] = i;
        }
        int max=0;
        for (int i = 1; i < editMatrix.length; i++) {
            for (int j = 1; j < editMatrix[i].length; j++) {
                int diagonal = editMatrix[i - 1][j - 1];
                if (s.charAt(j - 1) != t.charAt(i - 1)) {
                    diagonal += 1;
                }
                else
                {
                    max++;
                }
                int addition = editMatrix[i - 1][j] + 1;
                int deletion = editMatrix[i][j - 1] + 1;
                int min = Math.max(addition, deletion);
                min = Math.max(min, diagonal);
                editMatrix[i][j] = min;
            }
        }
        System.out.println("LCS  :  "+max);
        return editMatrix[t.length()][s.length()];
    }

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();
        System.out.println(EditDistance(s, t));
    }

}
