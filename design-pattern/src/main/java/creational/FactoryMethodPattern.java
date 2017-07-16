package creational;

/**
 * Created by 李恒名 on 2017/6/14.
 * <p>
 * 工厂方法模式
 */
public class FactoryMethodPattern {
    //汽车抽象
    static abstract class Car {
        abstract void run();
    }
    //奔驰汽车
    static class Benz extends Car {

        @Override
        void run() {
            System.out.println("奔驰车在跑");
        }
    }
    //宝马汽车
    static class BMW extends Car {

        @Override
        void run() {
            System.out.println("宝马车在跑");
        }
    }
    //抽象的汽车工厂
    static abstract class CarFactory {
        public abstract Car getCar();
    }
    //奔驰汽车工厂
    static class BenzFactory extends CarFactory {
        @Override
        public Car getCar() {
            return new Benz();
        }
    }
    //宝马汽车工厂
    static class BMWFactory extends CarFactory {
        @Override
        public Car getCar() {
            return new BMW();
        }
    }
    public static void main(String[] args) {
        CarFactory factory = new BenzFactory();
        //CarFactory factory = new BMWFactory();
        Car car = factory.getCar();
        car.run();
    }

    /**
     * 总结：
     * 作为抽象工厂模式的孪生兄弟，工厂方法模式定义了一个创建对象的接口，但由子类决定要
     * 实例化的类是哪一个，也就是说工厂方法模式让实例化推迟到子类。
     *
     * 工厂方法模式非常符合“开闭原则”，当需要增加一个新的产品时，我们只需要增加一个具
     * 体的产品类和与之对应的具体工厂即可，无须修改原有系统。同时在工厂方法模式中用户只
     * 需要知道生产产品的具体工厂即可，无须关系产品的创建过程，甚至连具体的产品类名称都
     * 不需要知道。虽然他很好的符合了“开闭原则”，但是由于每新增一个新产品时就需要增加
     * 两个类，这样势必会导致系统的复杂度增加。
     */
}

