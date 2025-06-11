---
id: utils
aliases: []
tags: []
---

# Utility Codes

## Matrix

### Adjacent Cells Iterator

```java
class AdjacentCellsIterator implements Iterator<AdjacentCellsIterator.Coordinate> {
  private final int[][] directions;
  private final int srcRow;
  private final int srcCol;
  private final int maxRow;
  private final int maxCol;
  private int idx = 0;

  public static class Coordinate {
    public int row;
    public int col;

    public Coordinate(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  public AdjacentCellsIterator(int srcRow, int srcCol, int maxRow, int maxCol) {
    this(srcRow, srcCol, maxRow, maxCol, false);
  }

  public AdjacentCellsIterator(
      int srcRow, int srcCol, int maxRow, int maxCol, boolean includeDiagonals) {
    this.srcRow = srcRow;
    this.srcCol = srcCol;
    this.maxRow = maxRow;
    this.maxCol = maxCol;

    if (includeDiagonals) {
      this.directions =
          new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
    } else {
      this.directions = new int[][] {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    }
  }

  @Override
  public boolean hasNext() {
    while (idx != directions.length) {
      int row = srcRow + directions[idx][0];
      int col = srcCol + directions[idx][1];

      if (row >= 0 && row < maxRow && col >= 0 && col < maxCol) return true;

      // skipping invalid coordinates
      idx++;
    }
    return false;
  }

  @Override
  public Coordinate next() {
    if (!hasNext()) throw new NoSuchElementException("Iterator ran out bro!");
    int[] dir = directions[idx++];
    return new Coordinate(srcRow + dir[0], srcCol + dir[1]);
  }
}
```
