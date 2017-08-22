package structural;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李恒名 on 2017/7/19.
 * <p>
 * 代理模式，为目标对象创建一个代理对象，其他对象可以通过代理对象来访问目标对象的方法，AOP、拦截器均为该模式的实践。
 *
 * @see java.lang.reflect.Proxy JDK动态代理
 */
public class ProxyPattern {
    //唱歌的能力
    interface Singable {
        void sing();
    }

    //歌手
    static class Singer implements Singable {

        @Override
        public void sing() {
            System.out.println("歌手唱歌");
        }
    }

    //歌手的代理人
    static class SingerProxy implements Singable {
        //歌手
        private Singable singer;

        public SingerProxy(Singable singer) {
            this.singer = singer;
        }

        @Override
        public void sing() {
            System.out.println("代理人承接演出");
            System.out.println("代理人布置演出场地");
            singer.sing();
            System.out.println("代理人结算演出费用");
        }
    }

    public static void main(String[] args) {
        Singer singer = new Singer();
        SingerProxy proxy = new SingerProxy(singer);
        proxy.sing();

        /**
         * 代理人承接演出
         * 代理人布置演出场地
         * 歌手唱歌
         * 代理人结算演出费用
         */

        // 如果，代理目标方法较多使用静态代理较为麻烦，可以使用动态代理，下面是使用JDK实
        // 现动态代理的方式，这种方式有一个要求，被代理的对象必须实现接口。

        List list = DynamicProxy.proxy(new ArrayList());
        list.add("data");
        list.size();
        list.remove("data");

        /**
         * 前置代理，调用方法：add()
         * 后置代理，方法返回：true
         * 前置代理，调用方法：size()
         * 后置代理，方法返回：1
         * 前置代理，调用方法：remove()
         * 后置代理，方法返回：true
         */

        // 当然除了JDK 提供的方式外我们也可以使用 CGLIB 来使用动态代理，它不要求代理的对象
        // 必须实现接口，这基于ASM 字节码技术。
    }

    private static class DynamicProxy {

        public static <T> T proxy(T target) {

            return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                    target.getClass().getInterfaces(), (proxy, method, args) -> {
                        System.out.println("前置代理，调用方法：" + method.getName()+"()");
                        Object result = method.invoke(target, args);
                        System.out.println("后置代理，方法返回：" + result);
                        return result;
                    });
        }
    }


    /**
     * 总结：
     * 代理模式就是给一个对象提供一个代理，并由代理对象控制对原对象的引用。它使得客户不
     * 能直接与真正的目标对象通信。代理对象是目标对象的代表，其他需要与这个目标对象打交
     * 道的操作都是和这个代理对象在交涉。代理对象可以在客户端和目标对象之间起到中介的作
     * 用，这样起到了的作用和保护了目标对象的，同时也在一定程度上面减少了系统的耦合度。
     */


}
