import java.util.*;

public class FibonacciSumSquares {
    private static long getFibonacciSumSquaresNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current * current;
        }

        return sum % 10;
    }

    private static long getPisanoPeriod(long m) {
        long pisanoPeriod = 0;
        long curr = 1;
        long prev = 0;
        for (int i = 0; i < m * m; i++) {
            long temp = 0;
            temp = curr;
            curr = (prev + curr) % m;
            prev = temp;

            if (prev == 0 && curr == 1) {
                pisanoPeriod = i + 1;
            }
        }
        return pisanoPeriod;
    }

    private static long getFibonacciPisano(long n, long m) {
        if (n <= 1) {
            return n;
        }

        long pisanoPeriod = getPisanoPeriod(m);
        long remainderN = n % pisanoPeriod;
        if (remainderN <= 1) {
            return remainderN;
        }

        long prev = 0;
        long curr = 1;
        // remainderN < m, don't bother checking beyond Fm, where m > remainderN
        // e.g. F2015 mod 3 = F7 mod 3 = 1. m = 2015 > remainderN = 7
        for (long i = 0; i < remainderN - 1; i++) {
            long temp;
            temp = curr;
            curr = (int)((prev + curr) % m);
            prev = temp;
        }
        return curr;
    }

    private static long getFibonacciPartialSum(long from, long to) {
        long lastDigitFrom = getFibonacciPisano(from + 1, 10);
        long lastDigitTo = getFibonacciPisano(to + 2, 10);
        if (lastDigitTo >= lastDigitFrom) {
            return lastDigitTo - lastDigitFrom;
        }
        else return lastDigitTo + 10 - lastDigitFrom;
    }

    private static long getFibonacciSumSquares(long n) {
        return getFibonacciPisano(n, 10) * getFibonacciPartialSum(n - 1, n) % 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumSquares(n);
        System.out.println(s);
    }
}

