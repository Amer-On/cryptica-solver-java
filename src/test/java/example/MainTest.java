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

<<<<<<< Updated upstream
=======
    @Test
    public void find_movable_blocks() {
        String file = "./src/test/resources/test-game-field-1.txt";
        List<String> content = Main.readFileContent(file);
        char[][] gameField = Main.fillGameField(content);

        List<Main.Block> movableBlocks = Main.findMovableBlocks(gameField);
        movableBlocks.forEach(System.out::println);
    }

    @Test
    public void move_left_right() {
        String file = "./src/test/resources/test-game-field-1.txt";
        List<String> content = Main.readFileContent(file);
        char[][] gameField = Main.fillGameField(content);
        Main.printGameField(gameField);

        System.out.println();
        Main.move(gameField, "left");
        System.out.println("Move Left:");
        Main.printGameField(gameField);

        System.out.println();
        Main.move(gameField, "right");
        System.out.println("Move Right:");
        Main.printGameField(gameField);
    }

    @Test
    public void move_down_up() {
        String file = "./src/test/resources/test-game-field-1.txt";
        List<String> content = Main.readFileContent(file);
        char[][] gameField = Main.fillGameField(content);
        Main.printGameField(gameField);

        System.out.println();
        System.out.println("Moving Left 3 times:");
        for (int i = 0; i < 3; i++) {
            Main.move(gameField, "left");
        }
        Main.printGameField(gameField);

        System.out.println();
        System.out.println("Moving Down:");
        Main.move(gameField, "down");
        Main.printGameField(gameField);

        System.out.println();
        System.out.println("Moving Up:");
        Main.move(gameField, "up");
        Main.printGameField(gameField);
    }

    @Test
    public void move_block_through_target() {
        List<String> content = Arrays.asList("#--Ax#");
        char[][] gameField = Main.fillGameField(content);
        Main.printGameField(gameField);
        List<Main.Block> targetBlocks= Main.findTargetBlocks(gameField);
        Main.move(gameField, "left");
        Main.move(gameField, "left");


        Main.checkTargetBlocksPos(gameField, targetBlocks);
        String result = Main.formatGameField(gameField);
        assertThat(result).isEqualTo("#-xA-#");
    }

    @Test
    public void format_multiline_game_field() {
        List<String> content = Arrays.asList("#--#", "#--#");
        char[][] gameField = Main.fillGameField(content);
        String result = Main.formatGameField(gameField);
        System.out.println(result);
        System.out.println(gameField);
        assertThat(result).isEqualTo("#--#\n#--#");
    }

    @Test
    public void find_target_blocks() {
        List<String> content = Arrays.asList("#--Ax#");
        char[][] gameField = Main.fillGameField(content);

        List<Main.Block> targetBlocks = Main.findTargetBlocks(gameField);

        Main.printBlocks(targetBlocks);
    }

    @Test
    public void check_target_block_pos() {
        String file = "./src/test/resources/test-game-field-1.txt";
        List<String> content = Main.readFileContent(file);
        char[][] gameField = Main.fillGameField(content);
        Main.printGameField(gameField);
        System.out.println();

        // code
        List<Main.Block> targetBlocks = Main.findTargetBlocks(gameField);

    }



>>>>>>> Stashed changes
}
