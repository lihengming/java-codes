package data.structure.stack;

import org.junit.Test;

/**
 * Created by 李恒名 on 2017/6/8.
 */
public class Tester {

    //测试栈的数组实现
    @Test
    public void test1(){
        Stack stack = new StackArrayImpl(3);
        stack.push("1");
        stack.push("2");
        stack.push("3");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    //测试栈的链表实现
    @Test
    public void test2(){
        Stack stack = new StackLinkedImpl();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
