package classloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

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
        if (fileName.indexOf('.') > 0) {
            fileName.replaceAll(".", "\\");
        }
        fileName = fileName + ".class";

        try {
            try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(classesDir + fileName))) {
                try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while ((len = bin.read(buffer)) != -1) {
                        out.write(buffer,0,len);
                    }
                    byte[] data = out.toByteArray();
                    return defineClass(name, data, 0, data.length);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }
}