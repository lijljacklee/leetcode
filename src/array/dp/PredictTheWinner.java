package array.dp;

/**
 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. This continues until all the scores have been chosen. The player with the maximum score wins.

 Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

 Example 1:
 Input: [1, 5, 2]
 Output: False
 Explanation: Initially, player 1 can choose between 1 and 2.
 If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2).
 So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
 Hence, player 1 will never be the winner and you need to return False.
 Example 2:
 Input: [1, 5, 233, 7]
 Output: True
 Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
 Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
 Note:
 1 <= length of the array <= 20.
 Any scores in the given array are non-negative integers and will not exceed 10,000,000.
 If the scores of both players are equal, then player 1 is still the winner.
 */
public class PredictTheWinner {

    public boolean PredictTheWinner(int[] nums) {
        // Method 1: Recursion
        // if (nums == null || nums.length == 0) {
        //     return true;
        // }
        // return dfsHelper(nums, 0, nums.length - 1) >= 0;

        // // Method 2: Recursion with memorization
        // if (nums == null || nums.length == 0) {
        //     return true;
        // }
        // return dfsHelper(nums, 0, nums.length - 1, new Integer[nums.length][nums.length]) >= 0;

        // Method 3: DP
        if (nums == null || nums.length == 0) {
            return true;
        }
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }



    private int dfsHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        // Minimax: use negative num as the Player 2's score.
        return Math.max(nums[left] - dfsHelper(nums, left + 1, right), nums[right] - dfsHelper(nums, left, right - 1));
    }

    private int dfsHelper(int[] nums, int left, int right, Integer[][] mem) {
        if (mem[left][right] == null) {
            mem[left][right] = left == right ?
                    nums[left] :
                    Math.max(nums[left] - dfsHelper(nums, left + 1, right, mem), nums[right] - dfsHelper(nums, left, right - 1, mem));
        }
        return mem[left][right];
    }
}
