package creational;

/**
 * Created by Potato on 2017/7/16.
 * <p>
 * 创建者模式，当创造一个对象需要很多步骤时适合使用建造者模式。而当只需调用一个方法
 * 就可以简单地创建整个对象时适合使用工厂模式。
 * @see StringBuilder
 * @see StringBuffer
 * @see java.sql.PreparedStatement
 * @see javax.swing.GroupLayout.Group
 */
public class BuilderPattern {

    /**
     * 被创建的对象，以手机为例。
     */
    static class Phone {
        private String displayScreen;//显示器
        private String battery;//电池
        private String camera;//摄像头
        private String operatingSystem;//操作系统

        public String getDisplayScreen() {
            return displayScreen;
        }

        public void setDisplayScreen(String displayScreen) {
            this.displayScreen = displayScreen;
        }

        public String getBattery() {
            return battery;
        }

        public void setBattery(String battery) {
            this.battery = battery;
        }

        public String getCamera() {
            return camera;
        }

        public void setCamera(String camera) {
            this.camera = camera;
        }

        public String getOperatingSystem() {
            return operatingSystem;
        }

        public void setOperatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
        }
    }

    static class PhoneBuilder {
        public Phone build(){
            Phone phone = new Phone();
            phone.setBattery("三星电池");
            phone.setCamera("索尼摄像头");
            phone.setDisplayScreen("三星显示器");
            phone.setOperatingSystem("IOS 10 操作系统");
            System.out.println(String.format("手机创建完成，使用%s、%s、%s、%s。",
                    phone.getBattery(),
                    phone.getCamera(),
                    phone.getDisplayScreen(),
                    phone.getOperatingSystem()));
            return phone;
        }
    }

    public static void main(String[] args) {
        PhoneBuilder builder = new PhoneBuilder();
        Phone phone = builder.build();
        //手机创建完成，使用三星电池、索尼摄像头、三星显示器、IOS 10 操作系统。
    }

    /**
     * 总结：
     * 建造者模式将复杂产品的构建过程封装分解在不同的方法中，使得创建过程非常清晰，能够
     * 让我们更加精确的控制复杂产品对象的创建过程，同时它隔离了复杂产品对象的创建和使用
     * ，使得相同的创建过程能够创建不同的产品。但是如果某个产品的内部结构过于复杂，将会
     * 导致整个系统变得非常庞大，不利于控制，同时若几个产品之间存在较大的差异，则不适用
     * 建造者模式，毕竟这个世界上存在相同点大的两个产品并不是很多，所以它的使用范围有限。
     */
}
