class Solution {

    public class Pair {
        int row = 0;
        int col = 0;
        

        public Pair(int x, int y) {
            row = x;
            col = y;
        }
    }

    public int orangesRotting(int[][] grid) {
        int freshOranges = 0;
        int m = grid.length, n = grid[0].length;
        Queue<Pair> queue = new ArrayDeque<>();
        //boolean[][] visited = new boolean[m][n];
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int time = 0, rottenCount = 0, minutes = 0;

        // Check the grid for rotten and fresh oranges and keep track of the number
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 2){
                    queue.offer(new Pair(i, j));
                    //visited[i][j] = true;
                }
                else if (grid[i][j] == 1)
                    freshOranges++;
            }
        }

        // Optimization, when there are no fresh oranges in the first place
        if(freshOranges == 0)
            return 0;

        // Run BFS
        while(!queue.isEmpty()) {
            int size = queue.size();
            boolean hasRotten = false;
            for(int i = 0 ; i < size ; i++) {
                Pair cell = queue.poll();
                for(int[] dir : dirs) {
                    int newRow = cell.row + dir[0];
                    int newCol = cell.col + dir[1];
                    if (canRot(grid, m, n, newRow, newCol)) {
                        grid[newRow][newCol] = 2;
                        //visited[newRow][newCol] = true;
                        queue.offer(new Pair(newRow, newCol));
                        freshOranges--;
                        hasRotten = true;
                    }
                }
            }

            if (hasRotten)
                minutes++;
        }

        return freshOranges == 0 ? minutes :  -1;
    }

    public boolean canRot(int[][] grid, int m, int n, int row, int col) {
        if(row >= 0 && row < m && col >= 0 && col < n && grid[row][col] == 1)
            return true;
        return false;
       
    }
}