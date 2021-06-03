import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static BigInteger getMaxPairwiseProductNaive(int[] numbers) {
        BigInteger max_product = new BigInteger("0");
        int n = numbers.length;

        for (int first = 0; first < n; ++first) {
            for (int second = first + 1; second < n; ++second) {
                max_product = BigInteger.valueOf(Math.max(max_product.intValue(),
                        numbers[first] * numbers[second]));
            }
        }

        return max_product;
    }

    static BigInteger getMaxPairwiseProduct(int[] numbers) {
        BigInteger max_product = new BigInteger("0");
        int n = numbers.length;

        int firstMaxIndex = -1;
        for (int i = 0; i < n; i++) {
            if (firstMaxIndex == -1 || numbers[i] > numbers[firstMaxIndex]) {
                firstMaxIndex = i;
            }
        }

        int secondMaxIndex = -1;
        for (int j = 0; j < n; j++) {
            if (secondMaxIndex == -1 && (j != firstMaxIndex) || ((j != firstMaxIndex) && (numbers[j] > numbers[secondMaxIndex]))) {
                secondMaxIndex = j;
            }
        }
        max_product = BigInteger.valueOf(numbers[firstMaxIndex]).multiply(BigInteger.valueOf(numbers[secondMaxIndex]));
        return max_product;
    }


    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                    InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
