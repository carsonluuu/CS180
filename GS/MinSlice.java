package GS;

public class MinSlice {
    public static int minSlice(int[][] matrix) {
        /*
        * dp[j] = Math.min(dp[i+1][j-1], dp[i+1][j], dp[i+1][j+1]) + matrix[j];
        * */
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < n; i++) {
            dp[m - 1][i] = matrix[m - 1][i];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[i][j] = Math.min(dp[i + 1][j + 1], dp[i + 1][j]) + matrix[i][j];
                } else if (j == n - 1) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j - 1]) + matrix[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j + 1], Math.min(dp[i + 1][j], dp[i + 1][j - 1])) + matrix[i][j];
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            res = Math.min(res, dp[0][j]);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(minSlice(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
        System.out.println(minSlice(new int[][]{{1,2,4},{1,1,5},{1,2,0}}));
    }
}
