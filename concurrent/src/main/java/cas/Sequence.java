package cas;

/**
 * Created by 李恒名 on 2017/6/20.
 */
public class Sequence {
    private int value;
    public synchronized int next() {
        return value++;
    }
}
