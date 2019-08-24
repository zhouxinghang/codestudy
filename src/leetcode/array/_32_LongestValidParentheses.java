package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-08-24
 * 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class _32_LongestValidParentheses {

    /**
     * 暴力法：两层for循环，判断每个字字符串是否有效
     * 动态规划：dp[i] 表示前i个元素的有效长度
     * 如果 s[i-1]=='(' && s[i]==')'  ==>  dp[i] = dp[i-2] + 2
     * 如果 s[i-1]==')' && s[i]==')' && s[i-dp[i-1]-1]=='('  ===>  dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]   // 需要之前由于断开的部分接起来
     * 时间复杂度 O(n)。遍历整个字符串一次，就可以将 dpdp 数组求出来。
     * 空间复杂度 O(n)。需要一个大小为 nn 的 dpdp 数组。
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int maxLen = Integer.MIN_VALUE;
        int[] dp = new int[s.length()];
        for (int i = 1 ;i < s.length(); i++) {
            if (s.charAt(i-1) == '(' && s.charAt(i) == ')') {
                dp[i] = i > 2 ? dp[i-2] + 2 : 2;
            } else if (s.charAt(i-1) == ')' && s.charAt(i) == ')' && s.charAt(i - dp[i-1] - 1) == '(') {
                // 需要之前由于断开的部分接起来
                dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2];
            } else {
                // 不符合的话，当做0处理
                dp[i] = 0;
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    /**
     * 采用两个计数器，left和right，先从左到右遍历字符串，然后从右到左遍历字符串。
     * 从左到右遍历
     *      遇到 '(' left++，遇到 ')' right++，如果 right > left 从头开始计数， right <= left ，有效长度=right*2
     * 从右到左遍历
     *      遇到 '(' left++，遇到 ')' right++，如果 right < left 从头开始计数， right >= left ，有效长度=left*2
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        int left = 0, right = 0, maxLen = 0;
        // 从左到右遍历
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (right > left) {
                left = right = 0;
            } else if (right == left){
                maxLen = Math.max(maxLen, right*2);
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (right < left) {
                left = right = 0;
            } else if (right == left){
                maxLen = Math.max(maxLen, left*2);
            }
        }
        return maxLen;
    }
    public static void main(String[] args) {
        System.out.println(new _32_LongestValidParentheses().longestValidParentheses2(")(()())"));
    }
}
