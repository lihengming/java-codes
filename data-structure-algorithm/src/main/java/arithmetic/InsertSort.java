package arithmetic;

import java.util.Arrays;

/**
 * Created by 李恒名 on 2017/6/15.
 */
public class InsertSort {
    public static void sort(int[] arr) {
        int length = arr.length;//数组长度，将这个提取出来是为了提高速度。
        int insertNum;//要插入的数
        for (int i = 1; i < length; i++) {//插入的次数
            insertNum = arr[i];//要插入的数
            int j = i - 1;//已经排序好的序列元素个数
            while (j >= 0 && arr[j] > insertNum) {//序列从后到前循环，将大于insertNum的数向后移动一格
                arr[j + 1] = arr[j];//元素移动一格
                j--;
            }
            arr[j + 1] = insertNum;//将需要插入的数放在要插入的位置。
        }
    }

    public static void main(String[] args) {
        int[] arr = {9, 6, 7, 4, 5, 3, 2, 1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
