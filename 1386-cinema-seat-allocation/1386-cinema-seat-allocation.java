import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Store only rows that have reserved seats.
        Map<Integer, Integer> reserved = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int s = seat[1];

            // Bit s represents seat s.
            reserved.put(row, reserved.getOrDefault(row, 0) | (1 << s));
        }

        // Rows with no reservations can always fit 2 groups:
        // [2,3,4,5] and [6,7,8,9].
        long result = 2L * (n - reserved.size());

        for (int mask : reserved.values()) {
            boolean left = (mask & ((1 << 2) | (1 << 3) |
                                   (1 << 4) | (1 << 5))) == 0;

            boolean middle = (mask & ((1 << 4) | (1 << 5) |
                                      (1 << 6) | (1 << 7))) == 0;

            boolean right = (mask & ((1 << 6) | (1 << 7) |
                                     (1 << 8) | (1 << 9))) == 0;

            if (left && right) {
                // Two non-overlapping groups.
                result += 2;
            } else if (left || middle || right) {
                // At least one valid block.
                result += 1;
            }
        }

        return (int) result;
    }
}
