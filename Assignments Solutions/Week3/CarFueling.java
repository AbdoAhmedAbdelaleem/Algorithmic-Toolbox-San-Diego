
import java.util.*;
import java.io.*;

public class CarFueling {
    static int computeMinRefills(int dist, int tank, int[] stops) {
        
        //check if we reached to final stop and we cannot get target
        if(stops[stops.length-1]+tank<dist)
            return -1;
       int lastHop=0;
       int lastHopIndex=-1;
       int numOfRefills=0;
       while(lastHop<dist&&lastHopIndex+1<stops.length)
       {
           int currentStop=lastHopIndex;
           while(currentStop+1<stops.length&&stops[currentStop+1]-lastHop<=tank)
               currentStop++;
           if(currentStop==lastHopIndex)
               return -1;
           if(lastHop+tank<dist)
           numOfRefills++;
           lastHop=stops[currentStop];
           lastHopIndex=currentStop;
       }
        return numOfRefills;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
