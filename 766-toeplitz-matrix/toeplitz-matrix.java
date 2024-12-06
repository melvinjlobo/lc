class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        if(matrix.length == 0)
            return true;

        int rows = matrix.length;
        int cols = matrix[0].length;

        //Toeplitz simply means checking r, c against r+1, c+1
        //We run the loop to one less. Note: we use < and not <= for matrix.length - 1, which means we are running the loop until penultimate
        for(int i = 0; i <  matrix.length - 1; i++) {
            for (int j = 0; j < matrix[0].length - 1; j++) {
                if(matrix[i][j] != matrix[i+1][j+1])
                    return false;
            }
        }

        return true;
    }
}