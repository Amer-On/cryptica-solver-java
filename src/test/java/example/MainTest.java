package example;

import java.util.*;

import org.junit.*;

import static org.assertj.core.api.Assertions.*;

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
    }

    @Test
    public void fill_game_field() {
        String file = "./src/test/resources/test-game-field-1.txt";
        List<String> content = Main.readFileContent(file);
        char[][] gameField = Main.fillGameField(content);

        boolean allLinesSameWidth = Arrays.stream(gameField)
            .allMatch(line -> line.length == 9);

        assertThat(allLinesSameWidth).isTrue();
        assertThat(gameField.length).isEqualTo(7);
    }

    @Test
    public void find_movable_blocks() {
        String file = "./src/test/resources/test-game-field-1.txt";
        List<String> content = Main.readFileContent(file);
        char[][] gameField = Main.fillGameField(content);

        List<Main.Block> movableBlocks = Main.findMovableBlocks(gameField);
        Main.printBlocks(movableBlocks);
    }


    @Test
    public void move_left() {
        List<String> content = Arrays.asList("#---xa#");
        char[][] gameField = Main.fillGameField(content);
        gameField = Main.move(gameField, Main.enumValues.direction.left, null);
        gameField = Main.move(gameField, Main.enumValues.direction.left, null);

        String result = Main.formatGameField(gameField);
        assertThat(result).isEqualTo("#-xa--#");
    }


    @Test
    public void move_left_advanced() {
        List<String> content = Arrays.asList("#-xxxa#");
        char[][] gameField = Main.fillGameField(content);
        gameField = Main.move(gameField, Main.enumValues.direction.left, null);
        gameField = Main.move(gameField, Main.enumValues.direction.left, null);

        String result = Main.formatGameField(gameField);
        assertThat(result).isEqualTo("#xxxa-#");
    }


    @Test
    public void move_right() {
        List<String> content = Arrays.asList("#ax---#");
        char[][] gameField = Main.fillGameField(content);
        gameField = Main.move(gameField, Main.enumValues.direction.right, null);
        gameField = Main.move(gameField, Main.enumValues.direction.right, null);

        String result = Main.formatGameField(gameField);
        assertThat(result).isEqualTo("#--ax-#");
    }

    @Test
    public void move_right_advanced() {
        List<String> content = Arrays.asList("#axxx-#");
        char[][] gameField = Main.fillGameField(content);
        gameField = Main.move(gameField, Main.enumValues.direction.right, null);
        gameField = Main.move(gameField, Main.enumValues.direction.right, null);

        String result = Main.formatGameField(gameField);
        assertThat(result).isEqualTo("#-axxx#");
    }

    @Test
    public void move_down() {
        List<String> content = Arrays.asList("##xa##", "##--##");
        char[][] gameField = Main.fillGameField(content);
        gameField = Main.move(gameField, Main.enumValues.direction.down, null);

        String result = Main.formatGameField(gameField);
        assertThat(result).isEqualTo("##--##\n##xa##");
    }

    @Test
    public void move_down_advanced() {
        List<String> content = Arrays.asList("##xa##", "##x-##", "##--##", "######");
        char[][] gameField = Main.fillGameField(content);
        gameField = Main.move(gameField, Main.enumValues.direction.down, null);
        gameField = Main.move(gameField, Main.enumValues.direction.down, null);

        String result = Main.formatGameField(gameField);
        assertThat(result).isEqualTo("##--##\n##x-##\n##xa##\n######");
    }

    @Test
    public void move_up() {
        List<String> content = Arrays.asList("##--##", "##xa##");
        char[][] gameField = Main.fillGameField(content);
        gameField = Main.move(gameField, Main.enumValues.direction.up, null);

        String result = Main.formatGameField(gameField);
        assertThat(result).isEqualTo("##xa##\n##--##");
    }

    @Test
    public void move_up_advanced() {
        List<String> content = Arrays.asList("######", "##--##", "##x-##", "##xa##");
        char[][] gameField = Main.fillGameField(content);
        gameField = Main.move(gameField, Main.enumValues.direction.up, null);
        gameField = Main.move(gameField, Main.enumValues.direction.up, null);

        String result = Main.formatGameField(gameField);
        assertThat(result).isEqualTo("######\n##xa##\n##x-##\n##--##");
    }

    @Test
    public void move_block_through_target_left() {
        List<String> content = Arrays.asList("#--Ax#");
        char[][] gameField = Main.fillGameField(content);
        List<Main.Block> targetBlocks = Main.findTargetBlocks(gameField);
        gameField = Main.move(gameField, Main.enumValues.direction.left, targetBlocks);
        gameField = Main.move(gameField, Main.enumValues.direction.left, targetBlocks);

        String result = Main.formatGameField(gameField);
        assertThat(result).isEqualTo("#-xA-#");
    }

    @Test
    public void move_block_through_target_right() {
        List<String> content = Arrays.asList("#xA--#");
        char[][] gameField = Main.fillGameField(content);
        List<Main.Block> targetBlocks = Main.findTargetBlocks(gameField);
        gameField = Main.move(gameField, Main.enumValues.direction.right, targetBlocks);
        gameField = Main.move(gameField, Main.enumValues.direction.right, targetBlocks);

        String result = Main.formatGameField(gameField);
        assertThat(result).isEqualTo("#-Ax-#");
    }

    @Test
    public void copy_char_array() {
        List<String> content = Arrays.asList("#--A--#", "#--x--#");
        char[][] gameField = Main.fillGameField(content);
        char[][] newGameField = Main.copyCharArray(gameField);
        newGameField[0][3] = 'a';
        assertThat(newGameField).isNotEqualTo(gameField);
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
    public void check_target_blocks_pos() {
        List<String> content = Arrays.asList("#--A-#", "#B---#");
        char[][] gameField = Main.fillGameField(content);
        List<Main.Block> targetBlocks = Main.findTargetBlocks(gameField);

        gameField = Main.fillGameField(Arrays.asList("#----#", "#x---#"));
        gameField = Main.checkTargetBlocksPos(gameField, targetBlocks);
        String result = Main.formatGameField(gameField);
        assertThat(result).isEqualTo("#--A-#\n#x---#");
    }

}
