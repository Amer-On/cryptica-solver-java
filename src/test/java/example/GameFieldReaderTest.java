package example;

import java.util.*;

import static example.GameFieldReader.*;

import org.junit.*;

import static org.assertj.core.api.Assertions.*;

public class GameFieldReaderTest {

    @Test
    public void read_file() {
        String file = "./src/test/resources/test-game-field-1.txt";
        List<String> content = readFileContent(file);
        assertThat(content).isNotEmpty();
    }

    @Test(expected = RuntimeException.class)
    public void read_file_not_present() {
        String file = "./src/test/resources/test-game-field-666.txt";
        List<String> content = readFileContent(file);
        fail("Must throw exception");
    }

    @Test
    public void fill_game_field() {
        String file = "./src/test/resources/test-game-field-1.txt";
        List<String> content = readFileContent(file);
        char[][] gameField = fillFieldContent(content);

        boolean allLinesSameWidth = Arrays.stream(gameField)
            .allMatch(line -> line.length == 9);

        assertThat(allLinesSameWidth).isTrue();
        assertThat(gameField.length).isEqualTo(7);
    }

}
