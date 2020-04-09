import java.util.*;
public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
       Arrays.sort(segments);
       ArrayList<Segment>comonSegments=new ArrayList<>();
       int i=0;
       while(i<segments.length)
       {
           Segment commonSegment=segments[i];
           while(i+1<segments.length)
           {
               Segment temp=commonSegment.getCommonSegment(segments[i+1]);
               if(temp!=null)
               {
                   commonSegment=temp;
                   i++;
               }
               else 
                   break;
           }
           i++;
           comonSegments.add(commonSegment);
       }
       int n=comonSegments.size();
        int[] points = new int[n];
        for (int j = 0; j < n; j++) {
            points[j]=comonSegments.get(j).end;
        }
        
        return points;
    }

    private static class Segment implements Comparable<Segment>{

        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Segment getCommonSegment(Segment b) {
            Segment firstSegment = start < b.start ? this : b;
            Segment secondSegment = this == firstSegment  ? b : this;
            if(secondSegment.start>firstSegment.end)
                return null;
            return new Segment(secondSegment.start,Math.min(firstSegment.end, secondSegment.end));
        }

        @Override
        public int compareTo(Segment t) {
            if(start>t.start)
                return 1;
            else if(start<t.start)
                return -1;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
