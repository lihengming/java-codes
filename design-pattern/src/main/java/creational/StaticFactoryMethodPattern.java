package creational;

/**
 * Created by 李恒名 on 2017/6/14.
 * <p>
 * 静态工厂方法模式，准确来讲这并不是一种设计模式，只是为了简化创建对象的过程。
 * 这种创建对象形式很常见，比如 Guava 中的 Lists 、Maps 等。
 */
public class StaticFactoryMethodPattern {
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

    //汽车工厂
    static final class CarFactory {

        public static Benz getBeznCar() {
            return new Benz();
        }
        public static BMW getBMWCar() {
            return new BMW();
        }
    }

    public static void main(String[] args) {
        Car car = CarFactory.getBeznCar();
        //Car car = CarFactory.getBMWCar;
        car.run();
    }
}

