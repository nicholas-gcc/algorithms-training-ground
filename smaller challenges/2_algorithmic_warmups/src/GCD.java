import java.util.*;

public class GCD {
  private static int gcd_naive(int a, int b) {
    int current_gcd = 1;
    for(int d = 2; d <= a && d <= b; ++d) {
      if (a % d == 0 && b % d == 0) {
        if (d > current_gcd) {
          current_gcd = d;
        }
      }
    }

    return current_gcd;
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

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(gcd_euclidean(a, b));
  }
}
