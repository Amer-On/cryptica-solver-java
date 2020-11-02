package example;

import static java.util.Arrays.*;

import org.junit.*;

import static org.assertj.core.api.Assertions.*;

public class GameFieldTest {

    @Test
    public void move_right() {
        char[][] result = GameFieldReader
            .fromFieldContent(asList("#a--#"))
            .move(Direction.RIGHT)
            .toArray();

        char[][] expected = GameFieldReader
            .fillFieldContent(asList("#-a-#"));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void move_right_no_diff() {
        char[][] result = GameFieldReader
            .fromFieldContent(asList("#--a#"))
            .move(Direction.RIGHT)
            .toArray();

        char[][] expected = GameFieldReader
            .fillFieldContent(asList("#--a#"));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void move_left() {
        char[][] result = GameFieldReader
            .fromFieldContent(asList("#a--#"))
            .move(Direction.RIGHT)
            .toArray();

        char[][] expected = GameFieldReader
            .fillFieldContent(asList("#-a-#"));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void move_left_no_diff() {
        char[][] result = GameFieldReader
            .fromFieldContent(asList("#a--#"))
            .move(Direction.LEFT)
            .toArray();

        char[][] expected = GameFieldReader
            .fillFieldContent(asList("#a--#"));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void move_up() {
        char[][] result = GameFieldReader
            .fromFieldContent(asList("#---#", "#a--#"))
            .move(Direction.UP)
            .toArray();

        char[][] expected = GameFieldReader
            .fillFieldContent(asList("#a--#", "#---#"));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void move_up_no_diff() {
        char[][] result = GameFieldReader
            .fromFieldContent(asList("#a--#", "#---#"))
            .move(Direction.UP)
            .toArray();

        char[][] expected = GameFieldReader
            .fillFieldContent(asList("#a--#", "#---#"));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void move_down() {
        char[][] result = GameFieldReader
            .fromFieldContent(asList("#---#", "#a--#"))
            .move(Direction.DOWN)
            .toArray();

        char[][] expected = GameFieldReader
            .fillFieldContent(asList("#---#", "#a--#"));

        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void to_array_compare() {
        String file = "./src/test/resources/test-game-field-1.txt";

        char[][] result = GameFieldReader
            .fromFile(file)
            .toArray();

        char[][] expected = GameFieldReader
            .readFieldContent(file);

        assertThat(result).isEqualTo(expected);
    }

}
