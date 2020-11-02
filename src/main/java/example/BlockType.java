package example;

import static java.lang.Character.*;

import lombok.*;

@Getter
@AllArgsConstructor
public enum BlockType {

    TARGET(false),
    BLOCK(true),
    WALL(false);

    private boolean movable;

    public static BlockType of(char ch) {
        BlockType result = null;

        if (ch == '#') {
            result = WALL;
        } else if (isLetter(ch) && isUpperCase(ch)) {
            result = TARGET;
        } else if (isLetter(ch) && isLowerCase(ch)) {
            result = BLOCK;
        } else {
            throw new RuntimeException(String.format(
                "Unknown block type: %s", ch));
        }

        return result;
    }

}
