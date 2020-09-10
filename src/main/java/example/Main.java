package example;

import java.nio.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
public class Main {

    public static void main(String[] args) {
        String file = "./src/test/resources/test-game-field-1.txt";
    }

    @Value
    public static class Block {

        private char name;

        private int x;

        private int y;

    }

    //    Move blocks
    public static char[][] move(char[][] gameField, String direction) {
        List<Block> movableBlocks = findMovableBlocks(gameField);
        if (direction.equals("left")) {
            moveLeft(gameField, movableBlocks);
        } else if (direction.equals("right")) {
            moveRight(gameField, movableBlocks);
        } else if (direction.equals("up")) {
            moveUp(gameField, movableBlocks);
        } else if (direction.equals("down")) {
            moveDown(gameField, movableBlocks);
        }
        return gameField;
    }

    public static char[][] moveLeft(char[][] gameField, List<Block> movableBlocks) {
        movableBlocks.sort(Comparator.comparingInt(Block::getY));
        for (Block block : movableBlocks) {
            char newCell = gameField[block.x][block.y - 1];
            if (newCell == '-' || Character.isUpperCase(newCell)) {
                int newY = block.y - 1;
                gameField[block.x][newY] = block.name;
                gameField[block.x][block.y] = newCell;
            }
        }
        return gameField;
    }

    public static char[][] moveRight(char[][] gameField, List<Block> movableBlocks) {
        movableBlocks.sort(Comparator.comparingInt(Block::getY));
        Collections.reverse(movableBlocks);
        for (Block block : movableBlocks) {
            char newCell = gameField[block.x][block.y + 1];
            if (newCell == '-' || Character.isUpperCase(newCell)) {
                int newY = block.y + 1;
                gameField[block.x][newY] = block.name;
                gameField[block.x][block.y] = newCell;
            }
        }
        return gameField;
    }

    public static char[][] moveDown(char[][] gameField, List<Block> movableBlocks) {
        movableBlocks.sort(Comparator.comparingInt(Block::getX));
        for (Block block : movableBlocks) {
            char newCell = gameField[block.x + 1][block.y];
            if (newCell == '-' || Character.isUpperCase(newCell)) {
                int newX = block.x + 1;
                gameField[newX][block.y] = block.name;
                gameField[block.x][block.y] = newCell;
            }
        }
        return gameField;
    }

    public static char[][] moveUp(char[][] gameField, List<Block> movableBlocks) {
        movableBlocks.sort(Comparator.comparingInt(Block::getX));
        Collections.reverse(movableBlocks);
        for (Block block : movableBlocks) {
            char newCell = gameField[block.x - 1][block.y];
            if (newCell == '-' || Character.isUpperCase(newCell)) {
                int newX = block.x - 1;
                gameField[newX][block.y] = block.name;
                gameField[block.x][block.y] = newCell;
            }
        }
        return gameField;
    }

    public static List<Block> findMovableBlocks(char[][] gameField) {
        List<Block> blocks = new ArrayList<Block>();
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                char cell = gameField[i][j];
                if (cell != '-' && cell != '#' && !Character.isUpperCase(cell)) {
                    Block block = new Block(cell, i, j);
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    //    Print field out
    public static void printGameField(char[][] gameField) {
        for (char[] row : gameField) {
            String separator = String.valueOf(" ");
            String joined = CharBuffer.wrap(row).chars()
                .mapToObj(intValue -> String.valueOf((char) intValue))
                .collect(Collectors.joining(separator));
            System.out.println(joined);
        }
    }

    //    Read game field
    public static char[][] readGameField(String file) {
        List<String> lines = readFileContent(file);
        return fillGameField(lines);
    }

    public static char[][] fillGameField(List<String> lines) {
        int height = lines.size();

        int width = 0;
        for (String line : lines) {
            width = Math.max(width, line.length());
        }

        char[][] gameField = new char[height][width];

        int i = 0;
        for (String line : lines) {
            gameField[i++] = line.toCharArray();
        }

        return gameField;
    }

    @SneakyThrows
    public static List<String> readFileContent(String file) {
        Path filePath = Path.of(file);
        if (!filePath.toFile().exists()) {
            throw new RuntimeException("No file: " + file);
        }

        return Files.readAllLines(filePath);
    }

    public static String formatGameField(char[][] gameField) {
        return Arrays.stream(gameField)
            .map(String::valueOf)
            .collect(Collectors.joining());
    }

}
