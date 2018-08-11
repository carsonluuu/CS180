package GS;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class WorldCup {
    public static String worldCup(int[] nums) {
        int sum = 0;
        int cnt = 0;
        int total = (nums.length) * (nums.length - 1) / 2;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                sum = Math.max(sum, nums[i] + nums[j]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (sum == nums[i] + nums[j]) {
                    cnt++;
                }
            }
        }

        double res = (double)cnt / (double)total;

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);

        return df.format(res);

    }

    public static void main(String[] args) {
        System.out.println(worldCup(new int[]{2, 3, 3, 3, 4, 0}));
    }
}
