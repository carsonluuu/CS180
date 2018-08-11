package GS;

public class ACapLock {
    public static String aCapLock(String s) {
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        char[] c = s.toCharArray();
        int i = 0;
        while (i < c.length) {
            while (c[i] == 'a') {
                flag = !flag;
                i++;
            }
            if (flag) {
                sb.append(Character.toUpperCase(c[i]));
            } else {
                sb.append(c[i]);
            }
            i++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(aCapLock("Baa, Baa! said the sheep"));
        System.out.println(aCapLock("My keyboard is broken!"));

    }
}
