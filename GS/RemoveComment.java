package GS;

import java.util.ArrayList;
import java.util.List;

public class RemoveComment {
    private boolean incomment;
    private String unfinish;
    private List<String> res;

    public List<String> removeComments(String[] source) {
        incomment = false;
        unfinish = "";
        res = new ArrayList<>();
        for (String line: source) {
            if (incomment) {
                processIncomment(line);
            } else {
                processNotIncomment(line);
            }
        }
        return res;
    }

    private void processIncomment(String line) {
        int index = line.indexOf("*/");
        if (index == -1) {
            return;
        }
        incomment = false;
        processNotIncomment(line.substring(index + 2));
    }

    private void processNotIncomment(String line) {
        int ind = line.indexOf("/*");
        int ind1 = line.indexOf("//");
        if (ind == -1 && ind1 == -1) {
            addLine(unfinish + line);
            return;
        }
        if (ind1 != -1 && (ind == -1 || ind1 < ind)) {
            addLine(unfinish + line.substring(0, ind1));
            return;
        }
        incomment = true;
        unfinish += line.substring(0, ind);
        processIncomment(line.substring(ind + 2));
    }

    private void addLine(String line) {
        unfinish = "";
        if (!line.isEmpty()) {
            res.add(line);
        }
    }

    public static void main(String[] args) {
        RemoveComment removeComment = new RemoveComment();
        System.out.println(removeComment.removeComments(new String[]{"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"}));
    }
}
