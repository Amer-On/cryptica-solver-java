package example;

import java.nio.file.*;
import java.util.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
public class Main {

    public static void main(String[] args) {
<<<<<<< Updated upstream
=======
        String file = "./src/test/resources/test-game-field-1.txt";
    }

    @Value
    public static class Block {

        private char name;

        private int y;
>>>>>>> Stashed changes

        private int x;
    }

<<<<<<< Updated upstream
=======

    //    Move blocks
    public static char[][] move(char[][] gameField, String direction) {
        List<Block> movableBlocks = findMovableBlocks(gameField);
        List<Block> targetBlocks = findTargetBlocks(gameField);
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
        movableBlocks.sort(Comparator.comparingInt(Block::getX));
        for (Block block : movableBlocks) {
            char newCell = gameField[block.y][block.x - 1];
            if (newCell == '-') {
                int newY = block.x - 1;
                gameField[block.y][newY] = block.name;
                gameField[block.y][block.x] = newCell;
            } else if (Character.isUpperCase(newCell)) {
                int newY = block.x - 1;
                gameField[block.y][newY] = block.name;
                gameField[block.y][block.x] = '-';
            }
        }
        return gameField;
    }

    public static char[][] moveRight(char[][] gameField, List<Block> movableBlocks) {
        movableBlocks.sort(Comparator.comparingInt(Block::getX));
        Collections.reverse(movableBlocks);
        for (Block block : movableBlocks) {
            char newCell = gameField[block.y][block.x + 1];
            if (newCell == '-' || Character.isUpperCase(newCell)) {
                int newY = block.x + 1;
                gameField[block.y][newY] = block.name;
                gameField[block.y][block.x] = newCell;
            } else if (Character.isUpperCase(newCell)) {
                int newY = block.x - 1;
                gameField[block.y][newY] = block.name;
                gameField[block.y][block.x] = '-';
            }
        }
        return gameField;
    }

    public static char[][] moveDown(char[][] gameField, List<Block> movableBlocks) {
        movableBlocks.sort(Comparator.comparingInt(Block::getY));
        for (Block block : movableBlocks) {
            char newCell = gameField[block.y + 1][block.x];
            if (newCell == '-' || Character.isUpperCase(newCell)) {
                int newX = block.y + 1;
                gameField[newX][block.x] = block.name;
                gameField[block.y][block.x] = newCell;
            } else if (Character.isUpperCase(newCell)) {
                int newY = block.x - 1;
                gameField[block.y][newY] = block.name;
                gameField[block.y][block.x] = '-';
            }
        }
        return gameField;
    }

    public static char[][] moveUp(char[][] gameField, List<Block> movableBlocks) {
        movableBlocks.sort(Comparator.comparingInt(Block::getY));
        Collections.reverse(movableBlocks);
        for (Block block : movableBlocks) {
            char newCell = gameField[block.y - 1][block.x];
            if (newCell == '-' || Character.isUpperCase(newCell)) {
                int newX = block.y - 1;
                gameField[newX][block.x] = block.name;
                gameField[block.y][block.x] = newCell;
            } else if (Character.isUpperCase(newCell)) {
                int newY = block.x - 1;
                gameField[block.y][newY] = block.name;
                gameField[block.y][block.x] = '-';
            }
        }
        return gameField;
    }

    public static void printBlocks(List<Block> Blocks) {
        for (Block block : Blocks) {
            System.out.print("Значение блока: " + block.name);
            System.out.print(" Позиция по х: " + block.y);
            System.out.println(" Позиция по у: " + block.x);
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
        for (Block block : targetBlocks) {
            if (gameField[block.y][block.x] == '-') {
                gameField[block.y][block.x] = block.name;
            }
        }
        return gameField;
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
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
=======
    public static String formatGameField(char[][] gameField) {
        return Arrays.stream(gameField)
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));
    }

>>>>>>> Stashed changes
}
