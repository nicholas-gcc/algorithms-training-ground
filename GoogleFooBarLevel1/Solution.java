import java.lang.Character;
import java.util.HashSet;
public class Solution {
    public static int solution(String x) {
        int partitionSize = 1; //number of colours per slice
        HashSet<String> coloursSet = new HashSet<>(); //only contains unique substrings
        char[] chars = x.toCharArray();
        if (x == "") {
            return 0;
        }
        while (partitionSize <= x.length()) {
            if (x.length() % partitionSize == 0) {
                for (int i = 0; i < x.length(); i = i + partitionSize) {
                    String currPartition = "";
                    for (int j = 0; j < partitionSize; j++) {
                        currPartition += Character.toString(chars[i + j]);
                    }
                    coloursSet.add(currPartition);
                }
                if (coloursSet.size() != 1) {
                    coloursSet.clear();
                    partitionSize++;
                }
                else break;
            }
            else {
                partitionSize++;
            }
        }
        return x.length() / partitionSize;
    }

    public static void main(String[] args) {
        System.out.println(solution("abccbaabccba"));
    }
}
