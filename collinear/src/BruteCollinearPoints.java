public class BruteCollinearPoints {

    private LineSegment[] result; // can be partially empty
    private int nextEmpty = 0;

    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if (points == null) throw new NullPointerException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new NullPointerException();
            for (int j = 0; j < i; j++) {
                if (points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException();
            }
        }
        result = new LineSegment[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++){

                        if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])
                                && points[j].slopeTo(points[k]) == points[k].slopeTo(points[l])) {
//                            System.out.println(points[i]);
//                            System.out.println(points[j]);
//                            System.out.println(points[k]);
//                            System.out.println(points[l]);
                            System.out.println();
                            Point p = points[i];
                            Point q = points[i];
                            int[] jkl = {j, k, l};
                            for (int index : jkl) {
                                if (points[index].compareTo(p) < 0) p = points[index];
                                else if (points[index].compareTo(q) > 0) q = points[index];
                            }
                            result[nextEmpty++] = new LineSegment(p, q);
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        // the number of line segments
        return nextEmpty;
    }

    public LineSegment[] segments() {
        // the line segments
        LineSegment[] returnArray = new LineSegment[nextEmpty];
        for (int i = 0; i < nextEmpty; i++) {
            returnArray[i] = result[i];
        }
        return returnArray;
    }

    public static void main(String args[]) {

//        // read the n points from a file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }

//        // draw the points
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();

//        // print and draw the line segments
//        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();

//        // TEST
//        String s = "1000 17000,14000 24000,26000 8000,10000 28000,18000 5000,1000 27000,14000 14000,11000 16000,29000 17000,5000 21000,19000 26000,28000 21000,25000 24000,30000 10000,25000 14000,31 000,16000 5000,12000 1000,31000 2000,24000 13000,17000 1000,28000 14000,16000 26000,26000 10000,31000 12000,4000 9000,24000 28000,29000 12000,20000 13000,11000 4000,26000 8000,1000 0,15000 12000,22000 29000,7000 15000,10000 4000,2000 29000,17000 17000,3000 15000,4000 29000,19000 2000";

//        String[] array1 = s.split(",");
//        int[][] array2 = new int[array1.length][2];
//        int index = 0;
//        for (String item : array1) {
//            String[] temp1 = item.split(" ");
//            int[] temp2 = {Integer.parseInt(temp1[0]), Integer.parseInt(temp1[1])};
//            array2[index++] = temp2;
//        }

//        Point[] points = new Point[array1.length];
//        for (int i = 0; i < array1.length; i++) {
//            points[i] = new Point(array2[i][0], array2[i][1]);
//        }

//        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
//        for (LineSegment segment : bruteCollinearPoints.segments()) {
//            System.out.println(segment);
//        }


    }
}
