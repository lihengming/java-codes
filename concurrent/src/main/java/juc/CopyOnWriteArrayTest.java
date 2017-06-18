package juc;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 李恒名 on 2017/6/18.
 */
public class CopyOnWriteArrayTest {

    public static void main(String[] args) {
        //List<Object> list = new ArrayList<>();//java.lang.ArrayIndexOutOfBoundsException
       final List<Object> list = new CopyOnWriteArrayList<>();
        int maxThreads = 10;

        for (int i = 0; i < maxThreads; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    list.add(new Object());
                }
            }).start();
        }

    }
}
