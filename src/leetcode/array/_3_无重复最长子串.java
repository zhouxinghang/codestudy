package leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author zhouxinghang
 * @date 2019-07-16
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class _3_无重复最长子串 {

    /**
     * 采用滑动窗口解决
     * Runtime: 9 ms, faster than 49.54% of Java online submissions for Longest Substring Without Repeating Characters.
     * Memory Usage: 36.5 MB, less than 99.90% of Java online submissions for Longest Substring Without Repeating Characters.
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0;
        int maxLength = 1;
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            // 去除前面添加的字符，直到不重复为止
            while(set.contains(chars[i])) {
                set.remove(chars[left]);
                left++;
            }
            set.add(chars[i]);
            maxLength = Math.max(set.size(), maxLength);
        }
        return maxLength;
    }

    /**
     * 滑动窗口改进，第一个方法缺陷是，如果字符重复，就会一个个的去删除，直到不重复为止。时间复杂度为 O(2n)，可以优化为 O(n)，删除字符的操作实际上可以不用
     * 用 map 来保存元素，key 为元素，value 为元素的位置
     *
     * Runtime: 7 ms, faster than 82.33% of Java online submissions for Longest Substring Without Repeating Characters.
     * Memory Usage: 36.1 MB, less than 99.94% of Java online submissions for Longest Substring Without Repeating Characters.
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int maxLength = 1;
        Map<Character, Integer> map = new HashMap<>();
        for (int left = 0, right = 0; right < chars.length; right ++) {
            if (map.containsKey(chars [right])) {
                // +1 表示重复元素的下一个位置。
                left = Math.max(map.get(chars[right]) + 1, left);
            }
            map.put(chars[right], right);
            maxLength = Math.max(right - left +1, maxLength);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new _3_无重复最长子串().lengthOfLongestSubstring2("pwwkew"));
    }
}
