package classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by 李恒名 on 2017/6/8.
 */
public class CustomClassLoader extends ClassLoader {
    private final String classesDir;

    public CustomClassLoader(String classesDir) {
        this.classesDir = classesDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = name;
        if (fileName.indexOf('.') != -1) {
            fileName = fileName.replaceAll("\\.", "\\\\");
        }
        fileName = fileName + ".class";
        try {
            try (FileInputStream in = new FileInputStream(classesDir + fileName)) {
                try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer,0,len);
                    }
                    byte[] data = out.toByteArray();
                    return defineClass(name, data, 0, data.length);
                }
            }
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    public static void main(String[] args) throws ReflectiveOperationException{
        //1. 将Test.java 编译为Test.class 后复制到 E:\classes 下，当然也可以选择其他目录作为类加载器的classpath。
        //2. 加载
        ClassLoader classLoader = new CustomClassLoader("E:\\classes\\");
        Class<?> clazz = classLoader.loadClass("Test");//如果你的Test在一个包内，需要加上包名，如x.y.z.Test
        //3. 通过反射调用say()方法
        Object instance = clazz.newInstance();
        Method method = clazz.getMethod("say", null);
        method.invoke(instance);//Hello
    }
}