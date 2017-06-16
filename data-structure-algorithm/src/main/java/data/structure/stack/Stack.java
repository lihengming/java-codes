package data.structure.stack;

/**
 * Created by 李恒名 on 2017/6/8.
 * <p>
 * 后进先出（LIFO, Last In First Out）
 */
public interface Stack {
    //入栈
    void push(Object element);

    //出栈
    Object pop();
}
