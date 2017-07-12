package creational;

/**
 * Created by 李恒名 on 2017/6/14.
 * <p>
 * 简单工厂模式
 */
public class SimpleFactoryPattern {

    public static void main(String[] args) {
        CarFactory factory = new CarFactory();
        Car car = factory.getCar("Benz");
        //Car car = factory.getCar("BMW");
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

    static class CarFactory {
        Car getCar(String name) {
            if (name.equals("Benz")) {
                return new Benz();
            } else if (name.equals("BMW")) {
                return new BMW();
            } else {
                throw new IllegalArgumentException("No support this car");
            }
        }
    }
}

