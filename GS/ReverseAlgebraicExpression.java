package GS;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ReverseAlgebraicExpression {
    public static String reverseAlgebraicExpression(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        Set<Character> operations = new HashSet<>();
        operations.add('+');operations.add('-');operations.add('*');operations.add('/');
        Stack<Character> ops = new Stack<>();
        Stack<String> nums = new Stack<>();
        char[] c = s.toCharArray();

        int i = 0;
        int len = s.length();
        while (i < len) {
            StringBuilder sb = new StringBuilder();
            if (c[i] == '-') {
                if (i == 0 || operations.contains(c[i - 1])) {
                    sb.append(c[i++]);
                }
            }
            while (i < len && (Character.isDigit(c[i]) || c[i] == '.')) {
                sb.append(c[i++]);
            }
            if (i < len) {
                ops.push(c[i++]);
            }
            nums.push(sb.toString());
        }

        StringBuilder res = new StringBuilder();
        res.append(nums.pop());
        while (!nums.isEmpty()) {
            res.append(ops.pop());
            res.append(nums.pop());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseAlgebraicExpression("-11.3*2.34+-9.4--23"));
        System.out.println(reverseAlgebraicExpression("1*2"));
        System.out.println(reverseAlgebraicExpression("1*2+9-23"));
        System.out.println(reverseAlgebraicExpression("10.4*32.7/12"));
    }
}
