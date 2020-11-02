package example;

import java.util.*;

import static java.util.stream.Collectors.*;

import static example.Direction.*;

import org.junit.*;

import static org.assertj.core.api.Assertions.*;

public class DirectionTest {

    private List<Point> points;

    @Before
    public void init() {
        points = Arrays.asList(
            Point.of(0, 0),
            Point.of(0, 2),
            Point.of(2, 0),
            Point.of(2, 2));
    }

    @Test
    public void check_sorting_up() {
        List<Point> result = points.stream()
            .sorted(UP.getComparator())
            .collect(toList());

        List<Point> expected = Arrays.asList(
            Point.of(0, 0),
            Point.of(2, 0),
            Point.of(0, 2),
            Point.of(2, 2));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void check_sorting_down() {
        List<Point> result = points.stream()
            .sorted(DOWN.getComparator())
            .collect(toList());

        List<Point> expected = Arrays.asList(
            Point.of(0, 2),
            Point.of(2, 2),
            Point.of(0, 0),
            Point.of(2, 0));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void check_sorting_left() {
        List<Point> result = points.stream()
            .sorted(LEFT.getComparator())
            .collect(toList());

        List<Point> expected = Arrays.asList(
            Point.of(0, 0),
            Point.of(0, 2),
            Point.of(2, 0),
            Point.of(2, 2));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void check_sorting_right() {
        List<Point> result = points.stream()
            .sorted(RIGHT.getComparator())
            .collect(toList());

        List<Point> expected = Arrays.asList(
            Point.of(2, 0),
            Point.of(2, 2),
            Point.of(0, 0),
            Point.of(0, 2));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void move_up() {
        Point point = Point.of(0, 0);
        Point expected = Point.of(0, -1);
        Point result = UP.move(point);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void move_down() {
        Point point = Point.of(0, 0);
        Point expected = Point.of(0, 1);
        Point result = DOWN.move(point);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void move_left() {
        Point point = Point.of(0, 0);
        Point expected = Point.of(-1, 0);
        Point result = LEFT.move(point);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void move_right() {
        Point point = Point.of(0, 0);
        Point expected = Point.of(1, 0);
        Point result = RIGHT.move(point);

        assertThat(result).isEqualTo(expected);
    }

}
