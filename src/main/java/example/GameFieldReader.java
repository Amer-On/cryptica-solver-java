package example;

import java.nio.file.*;
import java.util.*;

import lombok.*;

public class GameFieldReader {

    public static GameField fromFile(String file) {
        return GameField.of(readFieldContent(file));
    }

    public static GameField fromFieldContent(List<String> lines) {
        return GameField.of(fillFieldContent(lines));
    }

    static char[][] readFieldContent(String file) {
        List<String> lines = readFileContent(file);
        return fillFieldContent(lines);
    }

    static char[][] fillFieldContent(List<String> lines) {
        int height = lines.size();
        int width = lines.stream()
            .map(String::length)
            .max(Integer::compareTo)
            .orElseThrow(() -> new RuntimeException(
                "Game field width is zero"));

        char[][] gameField = new char[height][width];
        for (int i = 0; i < lines.size(); i++) {
            gameField[i] = lines.get(i).toCharArray();
        }
        return gameField;
    }

    @SneakyThrows
    static List<String> readFileContent(String file) {
        Path filePath = Path.of(file);
        if (!filePath.toFile().exists()) {
            throw new RuntimeException("No file: " + file);
        }

        return Files.readAllLines(filePath);
    }

}
