package Week6;

import java.util.*;
public class Knapsack {

    static int optimalWeightWithDuplication(int W, int[] w) {
        //write you code here
        int result = 0;
        for (int i = 0; i < w.length; i++) {
            if (result + w[i] <= W) {
                result += w[i];
            }
        }
        return result;
    }

    static int optimalWeightWithoutDuplicationRecurrence(int W, int[] w, int numOfUsableItems) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        if (map.containsKey(W + "," + numOfUsableItems)) {
            return map.get(W + "," + numOfUsableItems);
        }
        if (numOfUsableItems == 1) {
            if (W >= w[0]) {
                return w[0];
            }
        } else if (numOfUsableItems > 1) {
            int val = Integer.MIN_VALUE;
            if (W >= w[numOfUsableItems - 1]) {
                val = optimalWeightWithoutDuplicationRecurrence(W - w[numOfUsableItems - 1], w, numOfUsableItems - 1) + w[numOfUsableItems - 1];
            }
            return Math.max(val, optimalWeightWithoutDuplicationRecurrence(W, w, numOfUsableItems - 1));
        }
        return 0;
    }

    static int optimalWeightWithoutDuplicationIterative(int W, int[] w) {
        int[][] values = GetOptimalValuesMatrix(w, W);

        return values[w.length][W];

    }

    private static ArrayList<Integer> ConstructSolution(int[][] optimalMatrix, int[] w, int W) {
        int optimalValue = optimalMatrix[optimalMatrix.length - 1][optimalMatrix[0].length - 1];
        boolean[] useValues = new boolean[w.length];
        int currentW = optimalValue;//optimalMatrix[0].length-1;
        int currentUsableValueIndex = w.length;
        while (currentUsableValueIndex >= 1) {
            if (optimalMatrix[currentUsableValueIndex][currentW] != optimalMatrix[currentUsableValueIndex - 1][currentW]) {
                currentW -= w[currentUsableValueIndex - 1];
                useValues[currentUsableValueIndex - 1] = true;
            }
            currentUsableValueIndex -= 1;
        }
        ArrayList<Integer> returnedData = new ArrayList<>();
        for (int i = 0; i < useValues.length; i++) {
            if (useValues[i]) {
                returnedData.add(w[i]);
            }
        }
        return returnedData;
    }

    private static ArrayList<ArrayList<Integer>> constructAllSolution(int[][] optimalMatrix, int[] w, int targetValue) {
        ArrayList<ArrayList<Integer>> output = new ArrayList<>();
        for (int i = 0; i < optimalMatrix.length; i++) {
            for (int j = 0; j < optimalMatrix[i].length; j++) {
                if (optimalMatrix[i][j] == targetValue) {
                    ArrayList solution = constructSlution(optimalMatrix, targetValue, w, j, i);
                    if (solution.size() > 0) {
                        output.add(solution);
                    }
                }
            }
        }
        return output;
    }

    private static ArrayList<Integer> constructSlution(int[][] optimalMatrix, int targetValue, int[] w, int col, int row) {
        boolean[] useValues = new boolean[w.length];
        int currentW = col;//optimalMatrix[0].length-1;
        int currentUsableValueIndex = row;//w.length;
        while (currentUsableValueIndex >= 1) {
            if (optimalMatrix[currentUsableValueIndex][currentW] != optimalMatrix[currentUsableValueIndex - 1][currentW]) {
                currentW -= w[currentUsableValueIndex - 1];
                useValues[currentUsableValueIndex - 1] = true;
            }
            currentUsableValueIndex -= 1;
        }
        ArrayList<Integer> returnedData = new ArrayList<>();
        for (int i = 0; i < useValues.length; i++) {
            if (useValues[i]) {
                returnedData.add(w[i]);
            }
        }
        return returnedData;
    }

    private static void printArrayList(ArrayList<Integer> ll) {
        int n = ll.size();
        for (int i = 0; i < n; i++) {
            System.out.print(ll.get(i) + "  ");
        }
        System.out.println("");
    }

    private static void printArrayLists(ArrayList<ArrayList<Integer>> ll) {
        int n = ll.size();
        for (int j = 0; j < n; j++) {
            System.out.println("Solution");
            int nInner = ll.get(j).size();
            for (int i = 0; i < nInner; i++) {
                System.out.print(ll.get(i) + "  ");
            }
            System.out.println("");
        }
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

    static int optimalWeightWithoutDuplication(int W, int[] w, int[] v, int numOfUsableItems) {
        if (W == 10 && numOfUsableItems == 3) {
            int x = 5;
            x += 100;
        }
        if (numOfUsableItems == 1) {
            if (W >= w[0]) {
                return v[0];
            }
        } else if (numOfUsableItems > 1) {
            int val = Integer.MIN_VALUE;
            if (W >= w[numOfUsableItems - 1]) {
                val = Knapsack.optimalWeightWithoutDuplication(W - w[numOfUsableItems - 1], w, v, numOfUsableItems - 1) + v[numOfUsableItems - 1];
            }
            int d = Knapsack.optimalWeightWithoutDuplication(W, w, v, numOfUsableItems - 1);
            return Math.max(val, d);
        }
        return 0;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        /*  for (int i = 0; i < n; i++) {
            v[i] = scanner.nextInt();
        }*/
        int[][] mat = GetOptimalValuesMatrix(w, W);
        printArrayLists(constructAllSolution(mat, w, W));
        System.out.println(Knapsack.optimalWeightWithoutDuplicationIterative(W, w));
    }
}
