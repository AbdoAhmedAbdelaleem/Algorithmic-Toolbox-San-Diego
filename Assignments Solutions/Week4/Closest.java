import java.io.*;
import java.util.*;
import static java.lang.Math.*;
public class Closest {

    static class YComparer implements Comparator<Point> {

        @Override
        public int compare(Point t, Point t1) {
            if (t.y < t1.y) {
                return 1;
            } else if (t.y > t1.y) {
                return -1;
            }
            return 0;
        }

    }

    static class Point implements Comparable<Point> {

        long x, y;

        public double distanceTo(Point p) {
            return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
        }

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }
    }

    static double minimalDistance(Point[] points, int left, int right) {
        double ans = Double.POSITIVE_INFINITY;
        if (left >= right) {
            return ans;
        }
        int mid = (left + right) / 2;
        double leftDist = minimalDistance(points, left, mid);
        double rightDist = minimalDistance(points, mid + 1, right);
        if (right - left == 1) {
            return points[right].distanceTo(points[left]);
        } else {
            double minDist = Math.min(leftDist, rightDist);
            double rightBound = points[mid].x + minDist;
            double leftBound = points[mid].x - minDist;
            int righIndex=right;
            int leftinex=left;
           
            
            for (int i = mid; i >= left; i--) {
                if(points[i].x<leftBound)
                {
                    leftinex=i;
                    break;
                }
            }
            for (int i = mid; i <= right; i++) {
                if(points[i].x>rightBound)
                {
                    righIndex=i;
                    break;
                }
            }
            double dist = getMinimalDistance(points, leftinex, righIndex);
            return Math.min(dist, minDist);
        }
        // return ans;
    }

   

    private static int GetPreviousElement(Point[] arr, double target) {
        int start = 0, end = arr.length-1;  
    
        int ans = -1;  
        while (start <= end) {  
            int mid = (start + end) / 2;  
    
            // Move to the left side if the target is smaller  
            if (arr[mid].x >= target) {  
                end = mid - 1;  
            }  
    
            // Move right side  
            else {  
                ans = mid;  
                start = mid + 1;  
            }  
        }  
        return ans;  
    }

    private static int GetNextElement(Point[] arr, double target) {
       int start = 0, end = arr.length - 1;  
    
        int ans = -1;  
        while (start <= end) {  
            int mid = (start + end) / 2;  
    
            // Move to right side if target is  
            // greater.  
            if (arr[mid].x <= target) {  
                start = mid + 1;  
            }  
    
            // Move left side.  
            else {  
                ans = mid;  
                end = mid - 1;  
            }  
        }  
        return ans;  
    }

    private static double getMinimalDistance(Point[] arr, int left, int right) {
        Point[]copy=new Point[right-left+1];
        int j=0;
           for (int i = left; i <= right; i++,j++) {
            copy[j]=arr[i];
           }
        Arrays.sort(copy, new YComparer());

        double minDist = Double.MAX_VALUE;
        for (int i = 0; i < copy.length; i++) {
            int rightBound = Math.min(i + 3, copy.length-1);
            int leftBound = Math.max(0, i - 3);
            for (j = leftBound; j <= rightBound; j++) {
                if(i==j)
                    continue;
                double dist=copy[j].distanceTo(copy[i]);
                 if(dist<minDist)
                     minDist=dist;
            }
        }
        return minDist;
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = nextInt();
            int y = nextInt();
            points[i] = new Point(x, y);
        }
        Arrays.sort(points);
        System.out.println(minimalDistance(points, 0, points.length - 1));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");

    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null) {
                return null;
            }
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
