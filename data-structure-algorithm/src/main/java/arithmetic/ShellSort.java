package arithmetic;

import java.util.Arrays;

/**
 * Created by 李恒名 on 2017/6/15.
 */
public class ShellSort {

    public static void sort(int[] arr) {
        int len = arr.length;
        while (len != 0) {
            len = len / 2;
            for (int x = 0; x < len; x++) {//分的组数
                for (int i = x + len; i < arr.length; i += len) {//组中的元素，从第二个数开始
                    int j = i - len;//j为有序序列最后一位的位数
                    int temp = arr[i];//要插入的元素
                    for (; j >= 0 && temp < arr[j]; j -= len) {//从后往前遍历。
                        arr[j + len] = arr[j];//向后移动len位
                    }
                    arr[j + len] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 6, 7, 4, 5, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
