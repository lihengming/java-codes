package creational;

/**
 * Created by 李恒名 on 2017/7/16.
 * <p>
 * 抽象工厂模式，与简单工厂、工厂方法模式的区别在于，前者只有一个产品线只为创建一个
 * 产品服务，而后者则拥有多个产品线。
 */
public class AbstractFactoryPattern {
    //汽车抽象
    static abstract class Car {
        abstract void run();
    }

    //SUV产品抽象
    static abstract class Suv extends Car {
    }

    //商务轿车产品抽象
    static abstract class Sedan extends Car {
    }

    //奔驰SUV
    static class BenzSuv extends Suv {
        @Override
        void run() {
            System.out.println("奔驰（SUV）车在跑");
        }
    }

    //奔驰轿车
    static class BenzSedan extends Sedan {
        @Override
        void run() {
            System.out.println("奔驰（轿车）车在跑");
        }
    }

    //宝马SUV
    static class BMWSuv extends Suv {
        @Override
        void run() {
            System.out.println("宝马（SUV）车在跑");
        }
    }

    //宝马轿车
    static class BMWSedan extends Sedan {
        @Override
        void run() {
            System.out.println("宝马（轿车）车在跑");
        }
    }

    //抽象汽车工厂（拥有两个产品线）
    static abstract class CarFactory {
        abstract Suv getSuv();

        abstract Sedan getSedan();
    }

    //奔驰汽车工厂
    static class BenzCarFactory extends CarFactory {

        @Override
        Suv getSuv() {
            return new BenzSuv();
        }

        @Override
        Sedan getSedan() {
            return new BenzSedan();
        }
    }

    //宝马汽车工厂
    static class BMWCarFactory extends CarFactory {

        @Override
        Suv getSuv() {
            return new BMWSuv();
        }

        @Override
        Sedan getSedan() {
            return new BMWSedan();
        }
    }

    public static void main(String[] args) {
        CarFactory carFactory = new BenzCarFactory();
        //CarFactory carFactory = new BMWCarFactory();
        Sedan sedan = carFactory.getSedan();
        sedan.run();
        Suv suv = carFactory.getSuv();
        suv.run();
    }

    /**
     * 总结：
     * 所谓抽象工厂模式就是她提供一个接口，用于创建相关或者依赖对象的家族，而不需要明确
     * 指定具体类。他允许客户端使用抽象的接口来创建一组相关的产品，而不需要关系实际产出
     * 的具体产品是什么。这样一来，客户就可以从具体的产品中被解耦。它的优点是隔离了具体
     * 类的生成，使得客户端不需要知道什么被创建了，而缺点就在于新增新的行为会比较麻烦，
     * 因为当添加一个新的产品对象时，需要更加需要更改接口及其下所有子类。
     */
}
