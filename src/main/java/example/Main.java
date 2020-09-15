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
    }

    @Value
    public static class Block {

        private char name;

        private int x;

        private int y;

    }

    enum Direction {
        left,
        right,
        up,
        down
    }

    //    Move blocks
    public static char[][] move(char[][] gameField,
                                Direction direction,
                                List<Block> targetBlocks
    ) {
        char[][] movedGameField = copyCharArray(gameField);
        List<Block> movableBlocks = findMovableBlocks(movedGameField);
        if (direction == direction.left) {
            movedGameField = moveLeft(movedGameField, movableBlocks);
        } else if (direction == direction.right) {
            movedGameField = moveRight(movedGameField, movableBlocks);
        } else if (direction == direction.up) {
            movedGameField = moveUp(gameField, movableBlocks);
        } else if (direction ==
                   direction.down) {
            movedGameField = moveDown(gameField, movableBlocks);
        }
        if (targetBlocks != null) {
            movedGameField = checkTargetBlocksPos(movedGameField, targetBlocks);
        }

        return movedGameField;
    }

    public static char[][] moveLeft(char[][] gameField, List<Block> movableBlocks) {
        movableBlocks.sort(Comparator.comparingInt(Block::getY));
        char[][] movedLeftGameField = copyCharArray(gameField);
        for (Block block : movableBlocks) {
            char newCell = movedLeftGameField[block.x][block.y - 1];
            int newY = block.y - 1;
            if (newCell == '-') {
                movedLeftGameField[block.x][newY] = block.name;
                movedLeftGameField[block.x][block.y] = newCell;
            } else if (Character.isUpperCase(newCell)) {
                movedLeftGameField[block.x][newY] = block.name;
                movedLeftGameField[block.x][block.y] = '-';
            }
        }
        return movedLeftGameField;
    }

    public static char[][] moveRight(char[][] gameField, List<Block> movableBlocks) {
        movableBlocks.sort(Comparator.comparingInt(Block::getY));
        Collections.reverse(movableBlocks);
        char[][] movedRightGameField = copyCharArray(gameField);
        for (Block block : movableBlocks) {
            char newCell = movedRightGameField[block.x][block.y + 1];
            int newY = block.y + 1;
            if (newCell == '-') {
                movedRightGameField[block.x][newY] = block.name;
                movedRightGameField[block.x][block.y] = newCell;
            } else if (Character.isUpperCase(newCell)) {
                movedRightGameField[block.x][newY] = block.name;
                movedRightGameField[block.x][block.y] = '-';
            }
        }
        return movedRightGameField;
    }

    public static char[][] moveDown(char[][] gameField, List<Block> movableBlocks) {
        movableBlocks.sort(Comparator.comparingInt(Block::getX));
        char[][] movedDownGameField = copyCharArray(gameField);
        for (Block block : movableBlocks) {
            char newCell = movedDownGameField[block.x + 1][block.y];
            int newX = block.x + 1;
            if (newCell == '-') {
                movedDownGameField[newX][block.y] = block.name;
                movedDownGameField[block.x][block.y] = newCell;
            } else if (Character.isUpperCase(newCell)) {
                movedDownGameField[newX][block.y] = block.name;
                movedDownGameField[block.x][block.y] = '-';
            }
        }
        return movedDownGameField;
    }

    public static char[][] moveUp(char[][] gameField, List<Block> movableBlocks) {
        movableBlocks.sort(Comparator.comparingInt(Block::getX));
        Collections.reverse(movableBlocks);
        char[][] movedUpGameField = copyCharArray(gameField);
        for (Block block : movableBlocks) {
            char newCell = movedUpGameField[block.x - 1][block.y];
            int newX = block.x - 1;
            if (newCell == '-' || Character.isUpperCase(newCell)) {
                movedUpGameField[newX][block.y] = block.name;
                movedUpGameField[block.x][block.y] = newCell;
            } else if (Character.isUpperCase(newCell)) {
                movedUpGameField[newX][block.y] = block.name;
                movedUpGameField[block.x][block.y] = '-';
            }
        }
        return movedUpGameField;
    }

    public static void printBlocks(List<Block> blocks) {
        for (Block block : blocks) {
            System.out.print("Значение блока: " + block.name);
            System.out.print(" Позиция по х: " + block.x);
            System.out.println(" Позиция по у: " + block.y);
        }
    }

    // Finding blocks
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

    public static List<Block> findTargetBlocks(char[][] gameField) {
        List<Block> targetBlocks = new ArrayList<Block>();
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                char cell = gameField[i][j];
                if (Character.isUpperCase(cell)) {
                    Block targetBlock = new Block(cell, i, j);
                    targetBlocks.add(targetBlock);
                }
            }
        }
        return targetBlocks;
    }

    public static char[][] checkTargetBlocksPos(char[][] gameField, List<Block> targetBlocks) {
        char[][] checkedGameField = copyCharArray(gameField);
        for (Block block : targetBlocks) {
            if (gameField[block.x][block.y] == '-') {
                checkedGameField[block.x][block.y] = block.name;
            }
        }
        return checkedGameField;
    }

    public static char[][] copyCharArray(char[][] charArray) {
        char[][] charArrayCopy = new char[charArray.length][charArray[0].length];
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[i].length; j++) {
                charArrayCopy[i][j] = charArray[i][j];
            }
        }

        return charArrayCopy;
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
            .collect(Collectors.joining("\n"));
    }

}
