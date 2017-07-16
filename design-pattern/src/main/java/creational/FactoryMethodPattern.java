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
}

