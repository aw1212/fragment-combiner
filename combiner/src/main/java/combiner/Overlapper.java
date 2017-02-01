package combiner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Overlapper {

    public static void main(String[] args) {
        Overlapper overlapper = new Overlapper();
        overlapper.run("m quaerat voluptatem.;pora incidunt ut labore et d;, consectetur, adipisci" +
                "velit;olore magnam aliqua;idunt ut labore et dolore magn;uptatem.;i dolorem" +
                "ipsum qu;iquam quaerat vol;psum quia dolor sit amet, consectetur, a;ia" +
                "dolor sit amet, conse;squam est, qui do;Neque porro quisquam est, qu;aerat" +
                "voluptatem.;m eius modi tem;Neque porro qui;, sed quia non numquam ei;lorem" +
                "ipsum quia dolor sit amet;ctetur, adipisci velit, sed quia non numq;unt ut" +
                "labore et dolore magnam aliquam qu;dipisci velit, sed quia non numqua;us" +
                "modi tempora incid;Neque porro quisquam est, qui dolorem i;uam eius modi" +
                "tem;pora inc;am al");
    }

    private void run(String file) {
        List<String> list = Arrays.asList(file.split(";"));
        List<String> fragments = new ArrayList<>(list);
        List<String> copy = new ArrayList<>(list);
        String bla = "";
        String overlappingString = null;
        // Start with longest string that starts with a capital letter
        while (fragments.size() > 1) {
            String current = fragments.get(0);
            //copy.remove(current);
            fragments.remove(current);
            overlappingString = getMaximalOverlap(current, fragments);
            String sle = "";
            if (overlappingString.length() < current.length()) {
                sle = removeCommonSubstring(current, overlappingString);
            } else {
                sle = removeCommonSubstring(overlappingString, current);
            }
            //copy.remove(overlappingString);
            fragments.remove(overlappingString);
            String newFragment = "";
            if (overlappingString.startsWith(sle)) {
                newFragment = current + overlappingString.replace(sle, "");
            } else {
                newFragment = current.replace(sle, "") + overlappingString;
            }
            bla = newFragment;
            fragments.add(0, newFragment);

        }
        /*for (String fragment : fragments) {
            copy.remove(fragment);
            overlappingString = getMaximalOverlap(fragment, copy);
            String sle = "";
            if (overlappingString.length() < fragment.length()) {
                sle = removeCommonSubstring(fragment, overlappingString);
            } else {
                sle = removeCommonSubstring(overlappingString, fragment);
            }
            copy.remove(overlappingString);
            bla += fragment + overlappingString.replace(sle, "");
        }*/
        System.out.println(bla);
    }

    private String getMaximalOverlap(String fragment, List<String> copy) {
        int difference = 0;
        String closest = null;
        for (String c : copy) {
            int similarity = longestSubstr(c, fragment);
            if (similarity > difference) {
                difference = similarity;
                closest = c;
            }
        }
        if (closest == null) {
            return fragment;
        }
        //System.out.println(closest);
        return closest;
    }

    public static int longestSubstr(String first, String second) {
        int maxLen = 0;
        int fl = first.length();
        int sl = second.length();
        int[][] table = new int[fl+1][sl+1];

        for (int i = 1; i <= fl; i++) {
            for (int j = 1; j <= sl; j++) {
                if (first.charAt(i-1) == second.charAt(j-1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                    if (table[i][j] > maxLen)
                        maxLen = table[i][j];
                }
            }
        }
        return maxLen;
    }

    private String removeCommonSubstring(String s1, String s2) {
        int Start = 0;
        int Max = 0;
        for (int i = 0; i < s1.length(); i++)
        {
            for (int j = 0; j < s2.length(); j++)
            {
                int x = 0;
                while (s1.charAt(i + x) == s2.charAt(j + x))
                {
                    x++;
                    if (((i + x) >= s1.length()) || ((j + x) >= s2.length())) break;
                }
                if (x > Max)
                {
                    Max = x;
                    Start = i;
                }
            }
        }
        return s1.substring(Start, (Start + Max));
    }

}
