package leetcode.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author zhouxinghang
 * Created on 2020-06-22
 */
public class _20_有效的括号 {
    public static void main(String[] args) {
        System.out.println(new _20_有效的括号().isValid(""));
    }
    // 使用栈
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        for (int i = 0; i < s.length(); i++) {
            Character temp = map.get(s.charAt(i));
            if (temp != null) {
                if (stack.isEmpty() || stack.pop() != map.get(s.charAt(i))) {
                    return false;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }
}
