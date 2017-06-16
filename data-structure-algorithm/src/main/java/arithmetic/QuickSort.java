package arithmetic;

import java.util.Arrays;

/**
 * Created by 李恒名 on 2017/6/15.
 */
public class QuickSort {

    public static void sort(int[] arr, int start, int end) {
        if (start < end) {
            int base = arr[start]; // 选定的基准值（第一个数值作为基准值）
            int temp; // 记录临时中间值   
            int i = start, j = end;
            do {
                while ((arr[i] < base) && (i < end))
                    i++;
                while ((arr[j] > base) && (j > start))
                    j--;
                if (i <= j) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            } while (i <= j);
            if (start < j)
                sort(arr, start, j);
            if (end > i)
                sort(arr, i, end);
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 6, 7, 4, 5, 3, 2, 1};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
