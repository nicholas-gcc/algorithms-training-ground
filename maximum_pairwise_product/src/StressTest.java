import java.math.BigInteger;
import java.util.Random;

public class StressTest {
    public static void main(String[] args) {
        Random random = new Random();
        while (true) {
            int argsLength = random.nextInt(1000) % 10 + 2;
            int[] numberArgs = new int[argsLength];
            for (int i = 0; i < argsLength; i++) {
                numberArgs[i] = random.nextInt(100000);
                System.out.print(numberArgs[i] + " ");
            }
            BigInteger naiveAnswer = MaxPairwiseProduct.getMaxPairwiseProductNaive(numberArgs);
            BigInteger fastAnswer = MaxPairwiseProduct.getMaxPairwiseProduct(numberArgs);
            if (!naiveAnswer.equals(fastAnswer)) {
                System.out.println(String.valueOf(naiveAnswer) + " " + String.valueOf(fastAnswer)
                        + '\n' + "WRONG");
                break;
            }
            else {
                System.out.println(String.valueOf(naiveAnswer) + " " + String.valueOf(fastAnswer)
                        + '\n' + "OK");
            }
        }
    }
}
