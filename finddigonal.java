public class finddigonal {
 public int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) {
            return new int[0];
        }
        
        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];
        int k = 0;
        
        // Process each diagonal
        for (int d = 0; d < m + n - 1; d++) {
            if (d % 2 == 0) {
                // Even diagonal: go up-right (bottom-left to top-right)
                int row = Math.min(d, m - 1);
                int col = d - row;
                
                while (row >= 0 && col < n) {
                    result[k++] = mat[row][col];
                    row--;
                    col++;
                }
            } else {
                // Odd diagonal: go down-left (top-right to bottom-left)
                int col = Math.min(d, n - 1);
                int row = d - col;
                
                while (row < m && col >= 0) {
                    result[k++] = mat[row][col];
                    row++;
                    col--;
                }
            }
        }
        
        return result;
    }
}

// Test class with examples
class DiagonalTraverseTest {
    public static void main(String[] args) {
         finddigonal solution = new finddigonal();
        
        // Test case 1
        int[][] mat1 = {{1,2,3},{4,5,6},{7,8,9}};
        int[] result1 = solution.findDiagonalOrder(mat1);
        System.out.println("Input: [[1,2,3],[4,5,6],[7,8,9]]");
        System.out.print("Output: [");
        for (int i = 0; i < result1.length; i++) {
            System.out.print(result1[i]);
            if (i < result1.length - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected: [1,2,4,7,5,3,6,8,9]");
        System.out.println();
        
        // Test case 2
        int[][] mat2 = {{1,2},{3,4}};
        int[] result2 = solution.findDiagonalOrder(mat2);
        System.out.println("Input: [[1,2],[3,4]]");
        System.out.print("Output: [");
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i]);
            if (i < result2.length - 1) System.out.print(",");
        }
        System.out.println("]");
        System.out.println("Expected: [1,2,3,4]");
    }
}
