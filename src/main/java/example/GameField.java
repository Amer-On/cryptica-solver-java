package example;

import java.util.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import lombok.*;

import static example.BlockType.*;

@AllArgsConstructor
public class GameField {

    private Point max;

    private Set<Block> blocks;

    private Set<Block> targets;

    public static GameField of(char[][] content) {
        Set<Block> blocks = new HashSet<>();
        Set<Block> targets = new HashSet<>();

        for (int y = 0; y < content.length; y++) {
            char[] row = content[y];
            for (int x = 0; x < row.length; x++) {
                char name = row[x];
                if (name != '-') {
                    Point point = Point.of(x, y);
                    Block block = Block.of(name, point);
                    if (block.getType() == TARGET) {
                        targets.add(block);
                    } else {
                        blocks.add(block);
                    }
                }
            }
        }

        Point maxPoint = Point.of(content[0].length, content.length);
        return new GameField(maxPoint, blocks, targets);
    }

    public GameField move(Direction direction) {
        Map<Point, Block> movedBlocksMap = new HashMap<>();

        blocks.stream()
            .sorted(comparing(
                Block::getPoint,
                direction.getComparator()))
            .forEach(block -> moveBlock(block, direction, movedBlocksMap));

        Set<Block> result = new HashSet<>(movedBlocksMap.values());
        return new GameField(max, result, targets);
    }

    private void moveBlock(Block block, Direction direction, Map<Point, Block> blocksMap) {
        Block movedBlock = moveBlock(block, direction);
        if (block.getPoint().equals(movedBlock.getPoint())) {
            blocksMap.put(movedBlock.getPoint(), movedBlock);
        } else {
            if (blocksMap.containsKey(movedBlock.getPoint())) {
                blocksMap.put(block.getPoint(), block);
            } else {
                blocksMap.put(movedBlock.getPoint(), movedBlock);
                blocksMap.remove(block.getPoint());
            }
        }
    }

    private Block moveBlock(Block block, Direction direction) {
        if (block.getType() == BLOCK) {
            Point point = Optional
                .of(block.getPoint())
                .map(direction::move)
                .filter(this::isLegalPoint)
                .orElseGet(block::getPoint);
            return Block.of(block.getName(), point);
        }
        return block;
    }

    private boolean isLegalPoint(Point point) {
        return isLegalPoint(point.getX(), point.getY());
    }

    private boolean isLegalPoint(int x, int y) {
        return 0 <= x && x < max.getX() &&
               0 <= y && y < max.getY();
    }

    public char[][] toArray() {
        char[][] gameField = new char[max.getY()][max.getX()];

        var blocksMap = blocks.stream()
            .collect(toMap(Block::getPoint, x -> x));

        var targetsMap = targets.stream()
            .collect(toMap(Block::getPoint, x -> x));

        for (int y = 0; y < max.getY(); y++) {
            for (int x = 0; x < max.getX(); x++) {
                Point point = Point.of(x, y);

                char cell;
                if (blocksMap.containsKey(point)) {
                    cell = blocksMap.get(point).getName();
                } else if (targetsMap.containsKey(point)) {
                    cell = targetsMap.get(point).getName();
                } else {
                    cell = '-';
                }
                gameField[y][x] = cell;
            }
        }
        return gameField;
    }

}
