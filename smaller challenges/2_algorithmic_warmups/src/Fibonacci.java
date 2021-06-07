import java.util.Scanner;

public class Fibonacci {
  // runs n O(1) time
  private static long calc_fib(int n) {
    double sqrtOfFive = Math.sqrt(5);
    double goldenRatio = (1 + sqrtOfFive)/2;
    double goldenRatioConjugate = (1 - sqrtOfFive)/2;
    long output = (long) ((Math.pow(goldenRatio, n) - Math.pow(goldenRatioConjugate, n)) / sqrtOfFive);
    return output;
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
