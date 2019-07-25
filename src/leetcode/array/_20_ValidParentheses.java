package leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author zhouxinghang
 * @date 2019-07-19
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * Input: "()[]{}"
 * Output: true
 *
 * Input: "([)]"
 * Output: false
 *
 */
public class _20_ValidParentheses {

    /**
     * Runtime: 2 ms, faster than 60.88% of Java online submissions for Valid Parentheses.
     * Memory Usage: 34.3 MB, less than 99.98% of Java online submissions for Valid Parentheses.
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        for (Character character : s.toCharArray()) {
            if (map.containsKey(character)) {
                stack.push(character);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!map.get(stack.pop()).equals(character)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }

        System.out.println(new _20_ValidParentheses().isValid("((())"));
    }
}
