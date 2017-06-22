package unsafe;

/**
 * Created by 李恒名 on 2017/6/15.
 */
public class PrivateConstructTestClass {

    private int value;// 没有初始化

    public void printValue() {
        System.out.println(this.value);
    }

    private PrivateConstructTestClass() {
        this.value = 1;// 初始化
        new Error("don't support reflect.");
    }

}


