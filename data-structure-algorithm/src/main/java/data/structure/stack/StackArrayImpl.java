package data.structure.stack;

/**
 * Created by 李恒名 on 2017/6/8.
 */
public class StackArrayImpl implements Stack {

    private Object[] array;
    private int index = 0;

    public StackArrayImpl(int size) {
        this.array = new Object[size];
    }

    @Override
    public void push(Object element) {
        if(index < array.length){
            array[index++] = element;
        }else {
            throw new RuntimeException("Stack overflow");
        }
    }

    @Override
    public Object pop() {
        if(index < 1){
            throw new RuntimeException("Stack is empty");
        }
        return array[--index];
    }
}
