package GS;

import java.lang.reflect.Array;
import java.util.*;

public class SchoolAllotment {
    public static List<Integer> allocateSchools(List<Integer> schoolSeatsArray,
                                         List<Integer> studentScoreArray,
                                         List<List<Integer>> studentSchoolPreferenceArray) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int num = 0;

        int sum = 0;
        for (int seat : schoolSeatsArray) {
            sum += seat;
        }

        for (int score : studentScoreArray) {
            map.putIfAbsent(score, new ArrayList<>());
            map.get(score).add(num++);
        }
        Collections.sort(studentScoreArray);
        Collections.reverse((studentScoreArray));

        for (int i = 0; i < studentScoreArray.size(); i++) {
            if (i > 0 && studentScoreArray.get(i) == studentScoreArray.get(i - 1)) {
                continue;
            }
            int score = studentScoreArray.get(i);
            for (int index : map.get(score)) {
                for (int school : studentSchoolPreferenceArray.get(index)) {
                    int count = schoolSeatsArray.get(school);
                    if (count > 0) {
                        schoolSeatsArray.set(school, count - 1);
                        num--;
                        sum--;
                        break;
                    }
                }
            }
        }

        res.add(sum);
        res.add(num);

        return res;
    }

    public static void main(String[] args) {
        List<Integer> schoolSeatsArray = new ArrayList<>(Arrays.asList(1, 3, 1, 2));
        List<Integer> studentScoreArray = new ArrayList<>(Arrays.asList(990, 780, 830, 860, 920));
        List<List<Integer>> studentSchoolPreferenceArray = new ArrayList<>(Arrays.asList(Arrays.asList(3, 2, 1),
                                                                                         Arrays.asList(3, 0, 0), Arrays.asList(2, 0, 1),
                                                                                         Arrays.asList(0, 1, 3), Arrays.asList(0, 2, 3)));
        System.out.println(allocateSchools(schoolSeatsArray, studentScoreArray, studentSchoolPreferenceArray));
    }
}
