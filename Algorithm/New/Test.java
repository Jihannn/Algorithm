public class Test {
    public static void main(String[] args) {
    }

    public static void martixTest() {
        int a[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        int b[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        int c[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        int n = 3;
        for (int[] n1 : c) {
            for (int n2 : n1) {
                System.out.print(n2 + " ");
            }
            System.out.println();
        }
        System.out.println();
        martixMulit(a, b, n, c);
        for (int[] n1 : c) {
            for (int n2 : n1) {
                System.out.print(n2 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void martixMulit(int[][] a, int[][] b, int n, int[][] c) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
    }
}