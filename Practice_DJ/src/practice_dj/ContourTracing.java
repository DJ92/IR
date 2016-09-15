/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice_dj;

/**
 *
 * @author DJ
 */
import java.util.LinkedList;
import java.util.List;
 
/**
 *
 * @author nayef
 */
public class ContourTracing {
 
    public class Point {
 
    public static final int DIR_NORTH = 0;
    public static final int DIR_EAST = 1;
    public static final int DIR_SOUTH = 2;
    public static final int DIR_WEST = 3;
    private int x;
    private int y;
    private int direction;
 
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = DIR_NORTH;
    }
 
    public Point(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
 
    public Point(Point refPoint) {
        this.x = refPoint.x;
        this.y = refPoint.y;
        this.direction = refPoint.direction;
    }
 
    public void faceRight() {
 
        direction = (direction + 1) % 4;
 
    }
 
    private void faceLeft() {
        if (direction == 0) {
            direction = 3;
        } else {
            direction--;
        }
    }
 
    private void goForward() {
        if (direction == DIR_NORTH) {
            y = y - 1;
        }
        if (direction == DIR_EAST) {
            x = x + 1;
        }
        if (direction == DIR_SOUTH) {
            y = y + 1;
        }
        if (direction == DIR_WEST) {
            x = x - 1;
        }
 
    }
 
    public void advanceToLeft() {
 
        faceLeft();
        goForward();
    }
 
    public void advanceToRight() {
        faceRight();
        goForward();
    }
 
    public Point getClone() {
        return new Point(this);
    }
 
    public int getDirection() {
        return direction;
    }
 
    public int getX() {
        return x;
    }
 
    public int getY() {
        return y;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point other = (Point) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
 
    
    }
    public List getContourPoints(int[][] srcImage) {
        int[][] image = srcImage.clone();
        clearBorder(image);
 
        List points = new LinkedList();
 
        Point startingPoint = getStartingPoint(image);
        Point currentPoint = startingPoint.getClone();
 
        do {
            if (image[currentPoint.getY()][currentPoint.getX()] == 1) {
                points.add(currentPoint.getClone());
                currentPoint.advanceToLeft();
            } else {
                currentPoint.advanceToRight();
            }
 
        } while (!startingPoint.equals(currentPoint));
 
        return points;
    }
 
    private Point getStartingPoint(int[][] image) {
 
        for (int y = image.length - 1; y >= 0; y--) {
 
            for (int x = image[y].length - 1; x >= 0; x--) {
 
                if (image[y][x] == 1) {
                    return new Point(x, y);
                }
 
            }
        }
 
        return null;
    }
 
    private void clearBorder(int[][] image) {
 
        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image[y].length; x++) {
                if (y == 0 || x == 0 || y == image.length - 1 || x == image[y].length - 1) {
                    image[y][x] = 0;
                }
 
            }
 
        }
 
    }
}




