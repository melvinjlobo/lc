class Solution {

    int[][] directions = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public class Cell {
        int row;
        int col;
        int distance;

        public Cell(int x, int y, int d) {
            this.row = x;
            this.col = y;
            this .distance = d;
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int rowLength = grid.length;
        int colLength = grid[0].length;

        if(grid[0][0] == 1 || grid[rowLength - 1][colLength - 1] == 1)
            return -1;
        
        // Special case for [[0]]
        if(rowLength == 1 && colLength == 1)
            return 1;
        
        Deque<Cell> queue = new ArrayDeque<>();

        // DO BFS
        queue.add(new Cell(0, 0, 1));       //Add first cell

        while(!queue.isEmpty()) {
            Cell curr = queue.poll();
            for(int[] dir : directions) {
                int newRow = curr.row + dir[0];
                int newCol = curr.col + dir[1];
                if(isInBounds(newRow, newCol, rowLength, colLength) && grid[newRow][newCol] != 1) {
                    queue.add(new Cell(newRow, newCol, curr.distance + 1));
                    grid[newRow][newCol] = 1;

                    //Return case:
                    if(newRow == rowLength - 1 && newCol == colLength - 1)
                        return curr.distance + 1;
                }
            }
        }

        return -1;
    }

    public boolean isInBounds(int row, int col, int rowLimit, int colLimit) {
        if(row >= 0 && row < rowLimit && col >= 0 && col < colLimit)
            return true;
        return false; 
    }
}