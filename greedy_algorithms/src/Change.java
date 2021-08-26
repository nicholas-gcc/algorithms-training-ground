import java.util.Scanner;
import java.util.*;

public class Change {
    private static int getChange(int m) {
        ArrayList<Integer> denominations;
        if (m >= 10) {
            denominations = new ArrayList<>(Arrays.asList(10, 5, 1));
        } else if (m >= 5) {
            denominations = new ArrayList<>(Arrays.asList(5, 1));
        } else {
            denominations = new ArrayList<>(Arrays.asList(1));
        }
        int amountLeft = m;
        int coinCount = 0;
        int biggestCurrDenomination;
        while (m > 0 && denominations.size() > 0) {
            biggestCurrDenomination = denominations.get(0);
            int coinIncrement = amountLeft / biggestCurrDenomination;
            coinCount += coinIncrement;
            amountLeft -= biggestCurrDenomination * coinIncrement;
            if (amountLeft < biggestCurrDenomination) {
                denominations.remove(0);
            }
        }
        return coinCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

