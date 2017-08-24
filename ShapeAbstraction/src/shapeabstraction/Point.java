package shapeabstraction;

public class Point {

    Point NEXT, PREVIOUS;

    static int numOfPoints = 0;
    double VI;
    int x, y, index;

    public Point(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
        numOfPoints++;
    }
}
