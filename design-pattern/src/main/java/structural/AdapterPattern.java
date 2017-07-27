package structural;

import org.junit.Test;

/**
 * Created by 李恒名 on 2017/7/27.
 * <p>
 * 适配器模式，用于将一个类的接口转换成客户端所期待的另一种接口。
 */
public class AdapterPattern {

    //////////////////////////////////////////////////////////////////////
    //                           类适配器                                //
    //////////////////////////////////////////////////////////////////////

    //被适配的源对象(Adaptee)
    static class Power_220V {
        public void in() {
            System.out.print("220V电流输入");
        }
    }

    //目标接口(Target)
    interface Power_5V {
        void in();
    }

    //适配器(Adapter)，电源适配器为例
    static class PowerAdapter extends Power_220V implements Power_5V {
        @Override
        public void in() {
            System.out.print("5V电流输入");
            System.out.print("(");
            super.in();
            System.out.print(")");
        }
    }

    //客户端，手机为例
    static class Iphone {

        public void charge(Power_5V power) {
            System.out.print("充电中...");
            power.in();
        }
    }

    @Test
    public void testClassAdapter() {
        Iphone iPhone = new Iphone();
        PowerAdapter powerAdapter = new PowerAdapter();
        iPhone.charge(powerAdapter);
        //充电中...5V电流输入(220V电流输入)
    }

    //////////////////////////////////////////////////////////////////////
    //                           对象适配器                               //
    //////////////////////////////////////////////////////////////////////

    //适配器(Adapter)，电源适配器为例
    static class PowerAdapter2 implements Power_5V {

        private Power_220V power_220V;

        public PowerAdapter2(Power_220V power_220V) {
            this.power_220V = power_220V;
        }

        @Override
        public void in() {
            System.out.print("5V电流输入");
            System.out.print("(");
            power_220V.in();
            System.out.print(")");
        }
    }

    @Test
    public void testObjectAdapter() {
        Iphone iPhone = new Iphone();
        PowerAdapter2 powerAdapter = new PowerAdapter2(new Power_220V());
        iPhone.charge(powerAdapter);
        //充电中...5V电流输入(220V电流输入)
    }


    //////////////////////////////////////////////////////////////////////
    //                           接口适配器                               //
    //////////////////////////////////////////////////////////////////////

    /**
     * 有时候，我们需要实现一个接口，但只想实现其中一部分方法，又不
     * 想出现很多空的实现方法（这会让代码不那么优美），这时候就便可
     * 以使用接口适配器，把那些空的实现方法放在适配器内，去重写那些
     * 我们真正要实现的方法即可。
     * <p>
     * 如果你使用过 Spring MVC，你应该对下面这两个接口适配器很熟悉
     * <p>
     * - WebMvcConfigurerAdapter
     * - HandlerInterceptorAdapter
     */

    //源接口
    interface Source {
        void method1();

        void method2();

        void method3();
    }

    //接口适配器
    static abstract class SourceAdapter implements Source {
        @Override
        public void method1() {}
        @Override
        public void method2() { }
        @Override
        public void method3() { }
    }

    //继承适配器，重写 method1
    static class SourceImpl extends SourceAdapter {
        @Override
        public void method1() {
            System.out.println("method1 impl");
        }
    }

    /**
     * 总结：
     * 在我们的应用程序中我们可能需要将两个不同接口的类来进行通信，在不修改这两个的前提
     * 下我们可能会需要某个中间件来完成这个衔接的过程。这个中间件就是适配器。所谓适配器
     * 模式就是将一个类的接口，转换成客户期望的另一个接口。它可以让原本两个不兼容的接口
     * 能够无缝完成对接。
     */

}
