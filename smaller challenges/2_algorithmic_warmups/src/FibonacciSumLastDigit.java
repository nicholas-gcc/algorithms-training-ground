import java.util.*;

public class FibonacciSumLastDigit {
    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }

    private static int getFibonacciLastDigit(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current  = 1;

        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
        }

        return current;
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

    // F1 + F2 + ... + Fn = F(n + 2) - 1
    public static long getFibonacciSumPisano(long n) {
        long overflow = getFibonacciPisano(n + 2, 10);
        if (overflow < 1) {
            return 9;
        }
        else return overflow - 1;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumPisano(n);
        System.out.println(s);
    }
}

