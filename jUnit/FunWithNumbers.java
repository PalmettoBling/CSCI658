public class FunWithNumbers {
    // int return
    // sums arrays of integers
    public static int sum(int[] a) {
        int total = 0;
        for (int i = 0; i < a.length; i++) {
            total += a[i];
        }
        return total;
    }

    // bool return 
    // compare for sum of two elements equal to n
    public static boolean sumOfN(int[] a, int n) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] + a[j] == n) {
                    return true;
                }
            }
        }
        return false;
    }
}
