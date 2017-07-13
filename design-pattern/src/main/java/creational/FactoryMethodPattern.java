package creational;

/**
 * Created by 李恒名 on 2017/6/14.
 * <p>
 * 工厂方法模式
 */
public class FactoryMethodPattern {

    public static void main(String[] args) {
        CarFactory factory = new BenzFactory();
        //CarFactory factory = new BMWFactory();
        Car car = factory.getCar();
        car.run();
    }

    static abstract class Car {
        abstract void run();
    }

    static class Benz extends Car {

        @Override
        void run() {
            System.out.println("奔驰车在跑");
        }
    }

    static class BMW extends Car {

        @Override
        void run() {
            System.out.println("宝马车在跑");
        }
    }

    static abstract class CarFactory {
        abstract Car getCar();
    }

    static class BenzFactory extends CarFactory {
        @Override
        Car getCar() {
            return new Benz();
        }
    }

    static class BMWFactory extends CarFactory {
        @Override
        Car getCar() {
            return new BMW();
        }
    }
}

