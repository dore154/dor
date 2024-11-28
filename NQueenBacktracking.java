public class NQueenBacktracking {

        // Function to check if a queen can be placed at board[row][col]
        private static boolean isSafe(int[][] board, int row, int col, int n) {
            // Check this column on the left
            for (int i = 0; i < row; i++) {
                if (board[i][col] == 1) {
                    return false;
                }
            }
    
            // Check the upper diagonal on the left
            for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == 1) {
                    return false;
                }
            }
    
            // Check the upper diagonal on the right
            for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
                if (board[i][j] == 1) {
                    return false;
                }
            }
    
            return true;
        }
    
        // Function to solve the N-Queen problem
        private static boolean solveNQueen(int[][] board, int row, int n) {
            if (row >= n) {
                return true;
            }
    
            for (int col = 0; col < n; col++) {
                if (isSafe(board, row, col, n)) {
                    board[row][col] = 1; // Place the queen
    
                    if (solveNQueen(board, row + 1, n)) {
                        return true;
                    }
    
                    // Backtrack
                    board[row][col] = 0;
                }
            }
    
            return false;
        }
    
        // Function to print the board
        private static void printBoard(int[][] board, int n) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
    
        public static void main(String[] args) {
            int n = 4; // You can change the size of the board here
            int[][] board = new int[n][n];
    
            if (solveNQueen(board, 0, n)) {
                System.out.println("Solution for " + n + "-Queen problem:");
                printBoard(board, n);
            } else {
                System.out.println("No solution exists for " + n + "-Queen problem.");
            }
        }
    }
// time complexity  O(n!)
    // space complexity O(n^2)
    
