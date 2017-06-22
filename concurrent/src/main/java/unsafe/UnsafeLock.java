package unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;

/**
 * Created by 李恒名 on 2017/6/21.
 */
public class UnsafeLock {
    private Unsafe unsafe;

    public UnsafeLock() {
        try {
            //获得Unsafe的构造器
            Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
            //突破私有访问权限
            constructor.setAccessible(true);
            //创建示例
            this.unsafe = constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lock(Object obj) {
        unsafe.monitorEnter(obj);
    }

    public void unlock(Object obj) {
        unsafe.monitorExit(obj);
    }

}
