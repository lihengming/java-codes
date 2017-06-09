package classloader;

import java.lang.reflect.Method;

/**
 * Created by 李恒名 on 2017/6/8.
 */
public class Tester {

    public static void main(String[] args) throws Exception{
        //1. 将Test.java 编译为Test.class 后复制到 E:\classes 下，当然也可以选择其他目录。
        //2. 加载
        ClassLoader classLoader = new CustomClassLoader("E:\\classes\\");
        Class<?> clazz = classLoader.loadClass("Test");
        //3. 通过反射调用say()方法
        Object instance = clazz.newInstance();
        Method method = clazz.getMethod("say", null);
        method.invoke(instance);//Hello
    }
}
