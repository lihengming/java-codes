package cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 李恒名 on 2017/6/20.
 */
public class AtomicSequence {
    private AtomicInteger value = new AtomicInteger();

    public int next() {
        while (true) {
            int expect = value.get();
            int next = expect + 1;
            if (value.compareAndSet(expect, next)) {
                return next;
            }
        }

    }

    public int next2() {
        return value.incrementAndGet();
    }
}
