import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

class KnapsckItem implements Comparable<KnapsckItem> {

    int weight;
    int value;

    public KnapsckItem(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(KnapsckItem t) {
        double valuePerUnit = t.value / (double)t.weight;
        double valuePerUnitCurrent = value / (double)weight;
        if (valuePerUnitCurrent > valuePerUnit) {
            return -1;
        }
        else if (valuePerUnitCurrent < valuePerUnit) {
            return 1;
        }
        return 0;
    }
}

public class FractionalKnapsack {

    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        KnapsckItem[]items=new KnapsckItem[values.length];
        for (int i = 0; i < values.length; i++) {
            items[i]=new KnapsckItem(weights[i],values[i]);
        }
        Arrays.sort(items);
        
       int currentItemIndex=0;
       double currentValue=0;
       while(capacity>0&&currentItemIndex<items.length)
       {
           int min=Math.min(items[currentItemIndex].weight, capacity);
           currentValue+=min*(items[currentItemIndex].value/(double)items[currentItemIndex].weight);
           capacity-=min;
           currentItemIndex++;
       }
        return currentValue;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
}
