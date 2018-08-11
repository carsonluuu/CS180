package GS;

import java.util.HashMap;
import java.util.Map;


public class MusicPlaylistFullMinuteDilemma {
    private static final int M = 60;
    public static int playlist(int[] nums) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            num %= 60;
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            num %= 60;
            int diff = M - num;
            if (map.containsKey(diff) && map.get(diff) > 0) {
                if (diff == 30) {
                    res += map.get(num) * (map.get(num) - 1) / 2;
                } else {
                    res += map.get(diff);
                }
                map.put(num, 0);
            } else if (num == 0 && map.get(num) > 0) {
                res += map.get(num) * (map.get(num) - 1) / 2;
                map.put(num, 0);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(playlist(new int[]{119, 121, 352, 288}));
        System.out.println(playlist(new int[]{20, 40, 100, 110, 120}));

    }
}
