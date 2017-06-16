package arithmetic;

import java.util.Arrays;

/**
 * Created by 李恒名 on 2017/6/15.
 * <p>
 * 冒泡排序
 */
public class BubbleSort {

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "轮排序结果：" + Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 6, 7, 4, 5, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
