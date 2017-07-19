package structural;

/**
 * Created by 李恒名 on 2017/7/19.
 * <p>
 * 代理模式，为目标对象创建一个代理对象，其他对象可以通过代理对象来访问目标对象的方法，AOP、拦截器均为该模式的实践。
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
    }

    /**
     * 总结：
     * 代理模式就是给一个对象提供一个代理，并由代理对象控制对原对象的引用。它使得客户不
     * 能直接与真正的目标对象通信。代理对象是目标对象的代表，其他需要与这个目标对象打交
     * 道的操作都是和这个代理对象在交涉。代理对象可以在客户端和目标对象之间起到中介的作
     * 用，这样起到了的作用和保护了目标对象的，同时也在一定程度上面减少了系统的耦合度。
     */

}
