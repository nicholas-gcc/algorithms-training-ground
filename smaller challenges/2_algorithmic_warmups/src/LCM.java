import java.math.BigInteger;
import java.util.*;

public class LCM {
  private static long lcm_naive(int a, int b) {
    for (long l = 1; l <= (long) a * b; ++l)
      if (l % a == 0 && l % b == 0)
        return l;

    return (long) a * b;
  }

  private static int gcd_euclidean(int a, int b) {
    if (a > b) {
      if (b == 0) {
        return a;
      }
      else {
        return gcd_euclidean(b, a % b);
      }
    }
    else {
      if (a == 0) {
        return b;
      }
      else return gcd_euclidean(a, b % a);
    }
  }

  private static BigInteger lcm_efficient(int a, int b) {
    BigInteger firstNum = BigInteger.valueOf(a);
    BigInteger secondNum = BigInteger.valueOf(b);
    BigInteger product = firstNum.multiply(secondNum);
    return product.divide(BigInteger.valueOf(gcd_euclidean(a, b)));
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcm_efficient(a, b));
  }
}
