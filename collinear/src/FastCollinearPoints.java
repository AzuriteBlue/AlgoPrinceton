import java.util.Arrays;

public class FastCollinearPoints {
    private LineSegment[] result;
    private int nextEmpty = 0;


    public FastCollinearPoints(Point[] points) {
        // finds all line segments containing 4 or more points
        if (points == null) throw new NullPointerException();
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new NullPointerException();
            for (int j = 0; j < i; j++) {
                if (points[j].compareTo(points[i]) == 0) throw new IllegalArgumentException();
            }
        }
        result = new LineSegment[points.length];

        for (int i = 0; i + 1 < points.length; i++) {
            exch(points, 0, i);
            Arrays.sort(points, i + 1, points.length);
//            mergeSort(null, points, 0, points.length, Point::compareTo);

//            // TEST
//            for (Point point : points) {
//                System.out.println(point);
//            }
//            System.out.println();


//            mergeSort(slopes, points, 1, points.length, points[0].slopeOrder()); // left inclusive, right exclusive

            Arrays.sort(points, i + 1, points.length, points[0].slopeOrder());
            // DEL
            double[] slopes = new double[points.length];
            slopes[0] = Double.NaN;
            for (int t = i + 1; t < slopes.length; t++) {
                slopes[t] = points[0].slopeTo(points[t]);
            }
//            for (int q = 1; q < points.length; q++) {
//                System.out.println(slopes[q]);
//            }
//            System.out.println();

            // TEST
//            for (int q = 1; q < points.length; q++) {
//                System.out.println(slopes[q]);
//            }
//            System.out.println();
//            for (Point point : points) {
//                System.out.println(point);
//            }
//            System.out.println();

            for (int p = i + 1; p < points.length; p++) {
                Point beginning, ending;
                if (p + 2 < points.length && slopes[p - 1] != slopes[p]
                        && slopes[p] == slopes[p + 1]
                        && slopes[p + 1] == slopes[p + 2]) {
                    beginning = points[p].compareTo(points[0]) < 0 ? points[p] : points[0];
                    p += 2; // p++ -> equivalent to p += 3
                    while (p + 1 < slopes.length && slopes[p] == slopes[p + 1]) {
                        p++;
                    }
                    ending = points[p].compareTo(points[0]) > 0 ? points[p] : points[0];
                    result[nextEmpty++] = new LineSegment(beginning, ending);
                }
            }
        }

//        for (LineSegment lineSegment : result) {
//            System.out.println(lineSegment);
//        }
//        System.out.println();
    }






    private void exch(Point[] points, int i1, int i2) {
        Point temp = points[i1];
        points[i1] = points[i2];
        points[i2] = temp;
    }

//    private void mergeSort(double[] slopes, Point[] points, int begin, int end, Comparator<Point> comparator) {
//        Point[] aux = new Point[points.length];
//        sort(slopes, aux, points, begin, end, comparator);
//    }

//    private void merge(double[] slopes, Point[] aux, Point[] points, int begin, int mid, int end, Comparator<Point> comparator) {
//        for (int p = begin; p < end; p++) {
//            aux[p] = points[p];
//        }

//        int i = begin;
//        int j = mid + 1;

//        for (int p = begin; p < end; p++) {
//            if (i > mid) points[p] = aux[j++];
//            else if (j == end) points[p] = aux[i++];
//            else if (comparator.compare(aux[i], aux[j]) == 1) points[p] = aux[j++];
//            else points[p] = aux[i++];
//            if (slopes != null) slopes[p] = points[0].slopeTo(points[p]);
//        }
//    }

//    private void sort(double[] slopes, Point[] aux, Point[] points, int begin, int end, Comparator<Point> comparator) {
//        if (end - begin == 3) {
//            // insertionSort
//            for (int i = 0; i < points.length; i++) {
//                int curIndex = i;
//                while (curIndex > 0 && comparator.compare(points[curIndex - 1], points[curIndex]) == 1) {
//                    exch(points, curIndex - 1, curIndex);
//                }
//            }
//            return;
//        } else if (end - begin == 2) {
//            if (comparator.compare(points[begin], points[end - 1]) == 1) { exch(points, begin, end - 1); }
//            return;
//        }
//        int mid = (end + begin) / 2;
//        sort(slopes, aux, points, begin, mid, comparator);
//        sort(slopes, aux, points, mid, end, comparator);
//        merge(slopes, aux, points, begin, mid, end, comparator);
//    }






    public int numberOfSegments() {
        // the number of line segments
        return nextEmpty;
    }

    public LineSegment[] segments() {
        // the line segments
//        System.out.println(nextEmpty);
        LineSegment[] returnArray = new LineSegment[nextEmpty];
        for (int i = 0; i < nextEmpty; i++) {
            returnArray[i] = result[i];
//            System.out.println(returnArray[i]);
        }
        return returnArray;
    }

    public static void main(String[] args) {

//        Point p0 = new Point(2, 0);
//        Point p1 = new Point(2, 1);
//        Point p2 = new Point(2, 2);
//        Point p3 = new Point(2, 3);
//        Point p4 = new Point(2, 4);

//        Point[] testPoints = {p4, p3, p2, p1, p0};
//        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(testPoints);
//        for (Point point : testPoints) {
//            System.out.println("here");
//            System.out.println(point);
//        }






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
//        FastCollinearPoints collinear = new FastCollinearPoints(points);
//        System.out.println(collinear.numberOfSegments());

//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
    }
}