package example;

import java.util.*;
import java.util.function.*;

import static java.util.Comparator.*;

import lombok.*;

@Getter
@AllArgsConstructor
public enum Direction {

    UP(comparing(Point::getY)
        .thenComparing(Point::getX),
        Direction::moveUp),

    DOWN(comparing(Point::getY).reversed()
        .thenComparing(Point::getX),
        Direction::moveDown),

    LEFT(comparing(Point::getX)
        .thenComparing(Point::getY),
        Direction::moveLeft),

    RIGHT(comparing(Point::getX).reversed()
        .thenComparing(Point::getY),
        Direction::moveRight);

    private Comparator<Point> comparator;

    private Function<Point, Point> moveFunction;

    public Point move(Point point) {
        return moveFunction.apply(point);
    }

    private static Point moveUp(Point point) {
        return Point.of(point.getX(), point.getY() - 1);
    }

    private static Point moveDown(Point point) {
        return Point.of(point.getX(), point.getY() + 1);
    }

    private static Point moveLeft(Point point) {
        return Point.of(point.getX() - 1, point.getY());
    }

    private static Point moveRight(Point point) {
        return Point.of(point.getX() + 1, point.getY());
    }
}
