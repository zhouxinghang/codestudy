package leetcode.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhouxinghang
 * @date 2019/3/4
 * 给定一个密码 secret 和 猜测 guess，给出猜中的个数A（公牛）和数字正确但未在正确位置上的数字个数B（奶牛）
 * Example 1:
    Input: secret = "1807", guess = "7810"

    Output: "1A3B"

    Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
 * Note：只包含数据，且 secret 和 guess 长度相等
 */
public class _299_BullsAndCows {
    public String getHint(String secret, String guess) {
        if (secret == null || secret.length() == 0) {
            return "0A0B";
        }
        char[] secretChars = secret.toCharArray();
        char[] guessChars = guess.toCharArray();
        int countA = 0;
        int countB = 0;
        // 将 secret 当做生产者，将 guess当做消费者，如果为负数，表示有消费者消费，如果为正，表示有生产者生产
        int[] count = new int[10];
        Set<Character> secretSet = new HashSet<>();
        List<Character> guessList = new ArrayList<>();
        for (int i = 0; i < secretChars.length; i++) {
            if (secretChars[i] == guessChars[i]) {
                countA++;
            } else {
                // 如果secret的数字个数为负，表示有guess是这个数，countB ++，同时将secret的这个数 ++
                if (count[secretChars[i] - '0'] ++ < 0) {
                    countB ++;
                }
                if (count[guessChars[i] - '0'] -- > 0) {
                    countB ++;
                }
            }
        }
        return countA + "A" + countB + "B";
    }
}
