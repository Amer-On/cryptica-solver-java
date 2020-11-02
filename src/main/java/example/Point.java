package example;

import lombok.*;

@Value
public class Point {

    private int x;

    private int y;

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

}
