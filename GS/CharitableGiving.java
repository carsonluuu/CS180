package GS;

import java.util.ArrayList;
import java.util.List;

public class CharitableGiving {
    public static List<String> giving(int[] nums) {
        List<String> res = new ArrayList<>();
        int[] map = new int[3];
        for (int i = 0; i < nums.length; i++) {
            int minC = min(map);
            map[minC] += nums[i];
            res.add(minC == 0 ? "A" : minC == 1 ? "B" : "C");
        }

        return res;
    }

    private static int min(int[] map) {
        int res = 3;
        int value = Integer.MAX_VALUE;
        for (int i = 0; i < map.length; i++) {
            if (map[i] < value) {
                value = map[i];
                res = i;
            }
            if (map[i] == value) {
                res = Math.min(res, i);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println((giving(new int[]{25, 8, 2, 35, 15, 120, 55, 42})));
    }
}
