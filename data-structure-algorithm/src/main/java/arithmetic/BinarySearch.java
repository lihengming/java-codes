package arithmetic;

import org.junit.Assert;

import java.util.Arrays;

/**
 * Created by 李恒名 on 2017/6/23.
 */
public class BinarySearch {

    public static int indexOf(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;//取中间索引
            long midVal = arr[mid];

            if (midVal < key)//如果中间值小于key值则key在高位
                low = mid + 1;
            else if (midVal > key)//如果中间值大于key值则key在低位
                high = mid - 1;
            else
                return mid; // 找到，返回索引位置

        }
        return -1;  // 没有找到，返回 -1
    }

    public static void main(String[] args) {
        int[] arr = {9, 6, 7, 4, 5, 3, 2, 1};
        //需先将待搜索的数组进行排序
        Arrays.sort(arr);//[1, 2, 3, 4, 5, 6, 7, 9]
        Assert.assertEquals(indexOf(arr, 6), 5);

        Assert.assertEquals(indexOf(arr, 8), -1);
    }
}
