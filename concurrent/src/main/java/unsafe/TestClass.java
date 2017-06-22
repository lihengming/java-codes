package unsafe;

/**
 * Created by 李恒名 on 2017/6/21.
 */
public class TestClass {
    private int value;// 没有初始化

    //私有化构造器
    private TestClass() {
        this.value = 1;// 初始化
        //throw new AssertionError("don't support reflect.");
    }

    public void  printValue() {
        System.out.println(this.value);
    }
}
