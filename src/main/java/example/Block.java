package example;

import lombok.*;

@Value
public class Block {

    private char name;

    private Point point;

    private BlockType type;

    public static Block of(char name, Point point) {
        return new Block(name, point, BlockType.of(name));
    }

    public Block to(Point point) {
        return new Block(name, point, type);
    }

}
