package day04;

import java.util.Arrays;

public class PairFinder {

    public int findPair(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    i = j + 1;
                    j = i;
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        PairFinder pairFinder = new PairFinder();
        System.out.println(pairFinder.findPair(new int[]{1,3,3,5,3,3,5,8,5,8,2,2,2}));
    }
}