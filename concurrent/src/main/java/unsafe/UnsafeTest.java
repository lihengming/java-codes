package unsafe;

import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by 李恒名 on 2017/6/20.
 */
public class UnsafeTest {
    private Unsafe unsafe;

    @Before
    public void before() throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        unsafe = (Unsafe) f.get(null);
    }

    /**
     * 通过反射Constructor获得Unsafe的实例对象
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Unsafe unsafe = constructor.newInstance();
        System.out.println(unsafe);
    }

    /**
     * 通过反射Field获得Unsafe的实例对象
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        System.out.println(unsafe);
    }

    /**
     * 通过Unsafe的defineClass方法在内存中创建一个Class对象
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        //读取Class文件
        File file = new File("E:\\classes\\Test.class");
        FileInputStream input = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        input.read(data);
        input.close();
        //创建Class对象
        //Class clazz = unsafe.defineClass("Test", data, 0, data.length);//这个方法在Java 8 没了。
        Class clazz = unsafe.defineClass("Test", data, 0, data.length, null, null);
        //通过反射创建示例调用方法
        Object instance = clazz.newInstance();
        Method method = clazz.getMethod("say", null);
        method.invoke(instance);//Hello
    }

    /**
     * 通过Unsafe的allocateInstance方法创建一个类的实例
     *
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        //通过Unsafe创建实例
        TestClass instanceByUnsafe = (TestClass) unsafe.allocateInstance(TestClass.class);
        instanceByUnsafe.printValue();//0

        //通过反射创建实例
        //TestClass instanceByReflect = TestClass.class.newInstance();//该方法只能实例化拥有公开无参构造器的类
        Constructor<TestClass> constructor = TestClass.class.getDeclaredConstructor();
        constructor.setAccessible(true);//访问私有构造
        TestClass instanceByReflect = constructor.newInstance();//创建实例
        instanceByReflect.printValue();//1
    }

    @Test
    public void test5() throws Exception {
        unsafe.allocateMemory(Long.MAX_VALUE);//java.lang.OutOfMemoryError
    }

    @Test
    public void test6() throws Exception {
        Object lock = new Object();
        //注掉下面这行代码则抛出java.lang.IllegalMonitorStateException
        unsafe.monitorEnter(lock);
        lock.wait(1000);
        System.out.println("Hello World");
        unsafe.monitorExit(lock);
    }
}
