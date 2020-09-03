package example;

import java.nio.file.*;
import java.util.*;

import lombok.*;
import lombok.extern.slf4j.*;

@Slf4j
public class Main {

    public static void main(String[] args) {

    }

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

}
