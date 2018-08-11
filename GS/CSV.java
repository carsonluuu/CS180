package GS;

import java.util.ArrayList;
import java.util.List;

public class CSV {
    public static String format(List<String> list) {
        StringBuilder res = new StringBuilder();
        int[] space = new int[list.get(0).length()];
        for (String s : list) {
            String[] ss = s.split(",");
            for (int i = 0; i < ss.length; i++) {
                space[i] = Math.max(space[i], ss[i].length());
            }
        }
        for (String s : list) {
            String[] ss = s.split(",");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ss.length; i++) {
                int len = ss[i].length();
                sb.append(ss[i]);
                while (len < space[i]) {
                    sb.append(" ");
                    len++;
                }
                if (i != ss.length - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");

            res.append(sb.toString());
        }


        return res.deleteCharAt(res.length() - 1).toString();
    }

    public static void main(String[] args) {
        List<String> input = new ArrayList<>();
        input.add("aa,bbb,cccc");
        input.add("aaaaaaa,b,c");
        input.add("a,bbbbbb,cccccccccc");
        System.out.println(format(input));
    }
}
