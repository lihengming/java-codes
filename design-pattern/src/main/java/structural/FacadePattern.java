package structural;

/**
 * Created by 李恒名 on 2017/7/17.
 * <p>
 * 外观模式，也称门面模式，用于为一个复杂的系统提供一个统一、简单的访问入口
 */
public class FacadePattern {

    //系统门面
    static class SystemFacade {
        void start() {
            new SubSystemA().start();
            new SubSystemB().start();
            new SubSystemC().start();
            new SubSystemD().start();
        }
    }

    static class SubSystemA {
        void start() {
            System.out.println("子系统A启动");
        }
    }

    static class SubSystemB {
        void start() {
            System.out.println("子系统B启动");
        }
    }

    static class SubSystemC {
        void start() {
            System.out.println("子系统C启动");
        }
    }

    static class SubSystemD {
        void start() {
            System.out.println("子系统D启动");
        }
    }

    public static void main(String[] args) {
        SystemFacade facade = new SystemFacade();
        facade.start();
    }

    /**
     * 总结：
     * 我们都知道类与类之间的耦合越低，那么可复用性就越好，如果两个类不必彼此通信，那么
     * 就不要让这两个类发生直接的相互关系，如果需要调用里面的方法，可以通过第三者来转发
     * 调用。外观模式非常好的诠释了这段话。外观模式提供了一个统一的接口，用来访问子系统
     * 中的一群接口。它让一个应用程序中子系统间的相互依赖关系减少到了最少，它给子系统提
     * 供了一个简单、单一的屏障，客户通过这个屏障来与子系统进行通信。
     * 通过使用外观模式，使得客户对子系统的引用变得简单了，实现了客户与子系统之间的松耦
     * 合，但是它违背了“开闭原则”，因为增加新的子系统可能需要修改外观类或客户端的源代码。
     */
}
