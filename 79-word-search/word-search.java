class Solution {

    int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        // Scan across the entire board
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0)) {
                    boolean ans = dfs(board, word, i, j, 0);
                    if(ans)
                        return true;
                }
            }
        }

        return false; // Nothing found
    }

    public boolean dfs(char[][] board, String word, int row, int col, int index) {
        // Base case if we have reached the end of string
        if(index == word.length()) {
            return true;
        }

        // Boundaries and char mismatch
        if(row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1 || board[row][col] != word.charAt(index))
            return false;

        // Temporarily mark the spot as visited (Pick / not pick style of backtracking)
        char temp = board[row][col];
        board[row][col] = '*';

        // Go in all 4 directions and recursively do a DFS
        for(int[] dir: dirs) {
            boolean ans = dfs(board, word, row + dir[0], col + dir[1], index + 1);
            // If at any point in time we find the word, return true and short circuit
            if(ans)
                return true;
        }
        
        // Back track: 
        board[row][col] = temp;

        return false;       // None of the dfs above found the word
    }
}