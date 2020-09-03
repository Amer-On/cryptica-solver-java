package example;

import java.util.*;

import lombok.extern.slf4j.*;

import org.junit.*;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {

    @Test
    public void read_file() {
        String file = "./src/test/resources/test-game-field-1.txt";
        List<String> content = Main.readFileContent(file);
        assertThat(content).isNotEmpty();
    }

    @Test(expected = RuntimeException.class)
    public void read_file_not_present() {
        String file = "./src/test/resources/test-game-field-666.txt";
        List<String> content = Main.readFileContent(file);
        fail("Must throw exception");
    }

    @Test
    public void fill_game_field() {
        String file = "./src/test/resources/test-game-field-1.txt";
        List<String> content = Main.readFileContent(file);
        char[][] gameField = Main.fillGameField(content);

        boolean allLinesSameWidth = Arrays.stream(gameField)
            .allMatch(line -> line.length == 9);

        assertTrue(allLinesSameWidth);
        assertThat(gameField.length).isEqualTo(7);
    }

}
