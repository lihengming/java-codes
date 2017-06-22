package unsafe;

import org.junit.Assert;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 李恒名 on 2017/6/20.
 */
public class CustomAtomicInteger {
    private volatile int value;
    private Unsafe unsafe;
    private long offset;

    public CustomAtomicInteger() {
        try {
            //获得Unsafe的构造器
            Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
            //突破私有访问权限
            constructor.setAccessible(true);
            //创建示例
            this.unsafe = constructor.newInstance();
            //获得value变量的内存偏移量即内存地址
            offset = unsafe.objectFieldOffset(CustomAtomicInteger.class.getDeclaredField("value"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int incrementAndGet() {
        while (true) {
            int expected = value;
            int next = expected + 1;
            if (unsafe.compareAndSwapInt(this, offset, expected, next)) {
                return next;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CustomAtomicInteger atomicInteger = new CustomAtomicInteger();
        int maxThread = 100000;
        CountDownLatch latch = new CountDownLatch(maxThread);
        for (int i = 0; i < maxThread; i++) {
            new Thread(() -> {
                System.out.println(atomicInteger.incrementAndGet());
                latch.countDown();
            }).start();
        }
        latch.await();//等待所有线程执行完毕
        Assert.assertEquals(atomicInteger.incrementAndGet(), maxThread + 1);
    }
}
