//---------------justify---------------//
/*
  16
  -----
  4 (3) 4 (3) 2 | (16 - 4 - 4 - 2)/3 = 2
  7 (2) 2 (1) 4 | (16 - 7 - 2 - 4)/3 = 1 (16 - 7 - 2 - 4)%3 = 1  
  5 (1) 2 (8)   |
  "What   must   be"
  "example  of text"
  "shall be        "
*/
class Solution {
    public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<>();
        int i = 0; //word index
        int len = words.length;
        while (i < len) {
            int count = words[i].length();
            int j = i + 1; //one row's last word index
            while (j < len) {
                if (count + words[j].length() + 1 > L) break;
                count += words[j].length() + 1;
                j++;//will jump to the new begginning line head
            }
            StringBuilder sb = new StringBuilder();
            sb.append(words[i]);
            int diff = j - i - 1; //4 - 0 - 1 = 3 or 1 - 0 - 1 = 0 
            //last line case
            if (diff == 0 || j == len) {
                for (int k = i + 1; k < j; k++) {
                    sb.append(" " + words[k]);
                }
                for (int k = sb.length(); k < L; k++) {
                    sb.append(" ");
                }
            } else {
                int space = (L - count) / diff;
                int r = (L - count) % diff;
                for (int k = i + 1; k < j; k++) {
                    for (int l = space; l > 0; l--) {
                        sb.append(" ");
                    }
                    if (r > 0) {
                        sb.append(" ");
                        r--;
                    }
                    sb.append(" " + words[k]);
                }
            }
            res.add(sb.toString());
            i = j;
        }        
        return res;
    }
}
//---------------valid---------------//
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            HashSet<Character> rows = new HashSet<>();
            HashSet<Character> cols = new HashSet<>();
            HashSet<Character> cell = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !rows.add(board[i][j])) return false;
                if (board[j][i] != '.' && !cols.add(board[j][i])) return false;
                if (board[3*(i/3) + j/3][3*(i%3) + j%3] != '.' 
                    && !cell.add(board[3*(i/3) + j/3][3*(i%3) + j%3])) 
                    return false;                
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                if (!isValid(board, i, j)) return false;
            }
        }
        return true;
    }
    
    private static boolean isValid(char[][] board, int i, int j) {
        for (int row = 0; row < 9; row++) {
            if (row == i) continue;
            if (board[row][j] == board[i][j]) return false;
        }
        for (int col = 0; col < 9; col++) {
            if (col == j) continue;
            if (board[i][col] == board[i][j]) return false;
        }
        for (int row = (i/3)*3; row < (i/3)*3 + 3; row++) {
            for (int col = (j/3)*3; col < (j/3)*3 + 3; col++) {
                if (row == i && col == j) continue;
                if (board[i][j] == board[row][col]) return false;
            }
        }
        return true;
    }  
    
}
//---------------solver---------------//
class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0) return;
        solve(board);
    }
    
    private static boolean solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solve(board)) return true;
                            else board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] != '.' && board[i][col] == c) return false;
            if (board[row][i] != '.' && board[row][i] == c) return false;
            if (board[3*(row/3) + i / 3][3*(col/3) + i % 3] != '.' 
                && board[3*(row/3) + i / 3][3*(col/3) + i % 3]== c) return false; 
        }
        return true;
    }
}

//---------------Number---------------//
class Solution {
    public boolean isNumber(String s) {
        int nPiont = 0, nDigit = 0;
        s = s.trim() + " ";
        char[] c = s.toCharArray();
        int i = 0;
        int len = c.length - 1;
        if (c[i] == '+' || c[i] == '-') {
            i++;
        }
        while (Character.isDigit(c[i]) || c[i] == '.') {
            if (Character.isDigit(c[i])) {
                nDigit++;
            }
            if (c[i] == '.') {
                nPiont++;
            }
            i++;
        }
        if (nPiont > 1) {
            return false;
        }
        if (nDigit < 1) {
            return false;
        }
        if (c[i] == 'e') {
            i++;
            if (c[i] == '+' || c[i] == '-') {
                i++;
            }
            if (i == len) {
                return false;
            }
            for (; i < len; i++) {
                if (!Character.isDigit(c[i])) {
                    return false;
                }
            }
        }
        return i == len;
    }
}
//--------------evalRPN----------------//
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (s.equals("+")) {
               stack.push(stack.pop() + stack.pop()); 
            } else if(s.equals("-")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            } else if(s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if(s.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);                
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();     
    }
}
//--------------calculator 1----------------//
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int res = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';  
                    i++;
                }
                res += num * sign;
            } else if (s.charAt(i) == '+') {
                sign = 1;            
            } else if (s.charAt(i) == '-') {
                sign= -1;
            } else if (s.charAt(i) == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (s.charAt(i) == ')') {
                res = res * stack.pop() + stack.pop();
            }
        }
        return res;
    }
}
//--------------calculator 2----------------//
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        char sign = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
            }
            if (!Character.isDigit(s.charAt(i)) 
                && s.charAt(i) != ' ' || i + 1 == s.length()) {
                if (sign == '+') stack.push(num);
                if (sign == '-') stack.push(-num);
                if (sign == '*') stack.push(stack.pop() * num);
                if (sign == '/') stack.push(stack.pop() / num);
                sign = s.charAt(i);
                num = 0;
            }
        }
        for (int i : stack) {
            res += i;
        }
        return res;
    }
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        s = s.trim().replaceAll(" +", "");
        int res = 0;
        int preval = 0;
        int i = 0;
        char sign = '+';
        while (i < s.length()) {
            int curval = 0;
            while (i < s.length() && Character.isDigit(s.charAt(i))) {
                     curval = curval * 10 + s.charAt(i) - '0';
                     i++;
            }
            if (sign == '+') {
                res += preval;
                preval = curval;
            } else if (sign == '-') {
                res += preval;
                preval = -curval;
            } else if (sign == '*') {
                preval = preval * curval;
            } else if (sign == '/') {
                preval = preval / curval;
            }
            if (i < s.length()) {
                sign = s.charAt(i);
                i++;
            }
        }
        res += preval;
        return res;
    }
}
//--------------calculator 3----------------//
class Solution {
    public int calculate(String s) {
        int l1 = 0, o1 = 1;
        int l2 = 1, o2 = 1;

        Deque<Integer> stack = new ArrayDeque<>(); // stack to simulate recursion

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int num = c - '0';

                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(++i) - '0');
                }

                l2 = (o2 == 1 ? l2 * num : l2 / num);

            } else if (c == '(') {
                // First preserve current calculation status
                stack.offerFirst(l1); stack.offerFirst(o1);
                stack.offerFirst(l2); stack.offerFirst(o2);

                // Then reset it for next calculation
                l1 = 0; o1 = 1;
                l2 = 1; o2 = 1;

            } else if (c == ')') {
                // First preserve the result of current calculation
                int num = l1 + o1 * l2;

                // Then restore previous calculation status
                o2 = stack.poll(); l2 = stack.poll();
                o1 = stack.poll(); l1 = stack.poll();

                // Previous calculation status is now in effect
                l2 = (o2 == 1 ? l2 * num : l2 / num);

            } else if (c == '*' || c == '/') {
                o2 = (c == '*' ? 1 : -1);

            } else if (c == '+' || c == '-') {
                l1 = l1 + o1 * l2;
                o1 = (c == '+' ? 1 : -1);

                l2 = 1; o2 = 1;
            }
        }

        return (l1 + o1 * l2);
    }    
    
}
