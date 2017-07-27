package structural;

/**
 * Created by 李恒名 on 2017/7/27.
 * <p>
 * 装饰者模式
 *
 * @see java.io.InputStream  抽象构件
 * @see java.io.FilterInputStream 抽象装饰类
 * @see java.io.BufferedInputStream 具体的装饰类
 * @see java.io.DataInputStream 具体的装饰类
 */
public class DecoratorPattern {
    //抽象构件(Component)
    static abstract class Man {
        abstract void aboutMe();
    }

    //具体的构件(ConcreteComponent)
    static class Potato extends Man {

        @Override
        public void aboutMe() {
            System.out.print("Hi，我是土豆");
        }
    }

    //抽象装饰类(Decorator)
    static abstract class Decorator extends Man {
        private Man man;

        protected Decorator(Man man) {
            this.man = man;
        }

        @Override
        public void aboutMe() {
            man.aboutMe();
        }
    }

    //具体装饰类（ConcreteDecorator）
    static class ProgrammerDecorator extends Decorator {
        public ProgrammerDecorator(Man man) {
            super(man);
        }

        @Override
        public void aboutMe() {
            super.aboutMe();
            System.out.print("，我的职业是程序员");
        }
    }

    //具体装饰类（ConcreteDecorator）
    static class ReaderDecorator extends Decorator {
        public ReaderDecorator(Man man) {
            super(man);
        }

        @Override
        public void aboutMe() {
            super.aboutMe();
            System.out.print("，我喜欢读书");

        }
    }

    //具体装饰类（ConcreteDecorator）
    static class GitHubDecorator extends Decorator {
        public GitHubDecorator(Man man) {
            super(man);
        }

        @Override
        public void aboutMe() {
            super.aboutMe();
            System.out.print("，闲暇时我喜欢在 GitHub 上溜达");

        }
    }

    //具体装饰类（ConcreteDecorator）
    static class WriterDecorator extends Decorator {
        public WriterDecorator(Man man) {
            super(man);
        }

        @Override
        public void aboutMe() {
            super.aboutMe();
            System.out.print("，也会去写一些技术文章");

        }
    }

    //具体装饰类（ConcreteDecorator）
    public static void main(String[] args) {
        Man potato = new Potato();
        potato.aboutMe();
        //Hi，我是土豆

        System.out.println();

        potato = new WriterDecorator(new GitHubDecorator(new ReaderDecorator(new ProgrammerDecorator(potato))));
        potato.aboutMe();
        //Hi，我是土豆，我的职业是程序员，我喜欢读书，闲暇时我喜欢在 GitHub 上溜达，也会去写一些技术文章
    }

    /**
     * 总结：
     * 装饰者模式，可以动态地将责任附加到对象上，若要扩展功能，装饰者提供了比继承更加有
     * 弹性的替代方案，有效的避免了类爆炸现象的产生。虽然装饰者模式能够动态将责任附加到
     * 对象上，但是他会产生许多的细小对象，增加了系统的复杂度。java.io 包可谓是装饰者
     * 模式的最佳实践。
     */
}
