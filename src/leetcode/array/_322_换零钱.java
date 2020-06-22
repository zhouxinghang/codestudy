package leetcode.array;

import java.util.Arrays;

/**
 * @author zhouxinghang
 * Created on 2020-06-22
 */
public class _322_换零钱 {

    // dp[n] = min(dp[n-x] + 1, dp[n]) ； dp[n] 表示 金额 n 最少兑换硬币数
    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] == (amount+1) ? -1 : dp[amount];
    }
}
