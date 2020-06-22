package leetcode.array;

/**
 * @author zhouxinghang
 * @date 2019-07-19
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 */
public class _5_最长回文子串 {

    /**
     * 采用动态规划方式
     * dp[i][j] = dp[i+1][j-1] && (s[i] == s[j])
     *
     * Runtime: 62 ms, faster than 24.65% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 37.6 MB, less than 59.22% of Java online submissions for Longest Palindromic Substring
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        boolean[][] dp = new boolean[s.length()][s.length()];

        dp[0][0] = true;
        char[] chars = s.toCharArray();
        int left = 0,right =0;
        int maxLength = Integer.MIN_VALUE;
        // 左边从大到小遍历，右边从小到大遍历。这样 dp[i+1][j-1] 就是已知值
        for (int i = s.length() - 1; i >= 0; i--) {
            //
            for (int j = i; j < s.length() ; j ++) {
                if (j == 0) {
                   continue;
                }
                if (j == i) {
                    dp[i][j] = true;
                } else if (j == i+1){
                    dp[i][j] = chars[i] == chars[j];
                } else {
                    dp[i][j] = dp[i+1][j-1] && (chars[i] == chars[j]);
                }
                if (dp[i][j] && (j-i) > maxLength) {
                    left = i;
                    right = j;
                    maxLength = j - i;
                }
            }
        }
        System.out.println(maxLength);
        return s.substring(left, right +1);
    }

    /**
     * 中心扩展法
     * Runtime: 6 ms, faster than 86.92% of Java online submissions for Longest Palindromic Substring.
     * Memory Usage: 35.9 MB, less than 100.00% of Java online submissions for Longest Palindromic Substring.
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int left=0, right=0;
        int len1, len2, len;
        for (int i = 0; i < s.length(); i++) {
            len1 = expandAroundCenter(s, i, i);
            len2 = expandAroundCenter(s, i, i+1);
            len = Math.max(len1,len2);
            if (len > right - left) {
                left = i - (len-1) / 2;
                right = i + len / 2;
            }
        }
        return s.substring(left, right +1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left -2 +1;
    }

    public static void main(String[] args) {
        System.out.println(new _5_最长回文子串().longestPalindrome2("cbbd"));
    }
}
