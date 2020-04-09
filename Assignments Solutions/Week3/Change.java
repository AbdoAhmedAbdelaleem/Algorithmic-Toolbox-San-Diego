import java.util.Scanner;
public class Change {
    private static int getChange(int m) {
        int[]units={10,5,1};
        int currentUnitIndex=0;
        int count=0;
        while(m>0&&currentUnitIndex<units.length)
        {
            while(m>=units[currentUnitIndex])
            {
                m-=units[currentUnitIndex];
                count++;
            }
            currentUnitIndex++;
                
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}


