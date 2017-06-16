package arithmetic;

import java.util.Arrays;

/**
 * Created by 李恒名 on 2017/6/15.
 */
public class SelectSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {//循环次数
            int key = arr[i];
            int position = i;
            for (int j = i + 1; j < arr.length; j++) {//选出最小的值和位置
                if (arr[j] < key) {
                    key = arr[j];
                    position = j;
                }
            }
            arr[position] = arr[i];//交换位置
            arr[i] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 6, 7, 4, 5, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
