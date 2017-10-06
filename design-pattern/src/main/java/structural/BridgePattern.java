package structural;

/**
 * Created by 李恒名 on 2017/10/06.
 *
 * 桥接模式的意图是将抽象类与抽象方法的实现相互分离来实现解耦，以便二者可以相互独立地变化。
 *
 * 比如太阳镜，太阳镜有很多种品牌，不管何种品牌它们都会提供不同的风格供顾客选择，比如复古
 * 风格、飞行员风格等，太阳镜与风格间的关系便可使用该模式来维护。
 */
public class BridgePattern {
    //太阳镜的抽象类
    static abstract class SunGlasses {
        protected Style style;

        protected SunGlasses(Style style) {
            this.style = style;
        }

        //获取品牌名称
        abstract String getBrandName();

        //戴眼镜
        public void putOn() {
            System.out.println("戴上" + getBrandName() + "-" + style.getStyle() + "风格的太阳镜");
        }
    }

    static class BolonSunGlasses extends SunGlasses {
        public BolonSunGlasses(Style style) {
            super(style);
        }

        @Override
        String getBrandName() {
            return "暴龙";
        }
    }

    static class DiorSunGlasses extends SunGlasses {
        public DiorSunGlasses(Style style) {
            super(style);
        }

        @Override
        String getBrandName() {
            return "迪奥";
        }
    }

    //太阳镜风格接口
    interface Style {
        String getStyle();
    }

    //复古风格
    static class RetroStyle implements Style {

        @Override
        public String getStyle() {
            return "复古";
        }
    }

    //飞行员风格
    static class PilotStyle implements Style {

        @Override
        public String getStyle() {
            return "飞行员";
        }
    }

    public static void main(String[] args) {
        RetroStyle retroStyle = new RetroStyle();
        PilotStyle pilotStyle = new PilotStyle();

        SunGlasses glasses = new DiorSunGlasses(retroStyle);
        glasses.putOn();
        //戴上迪奥-复古风格的太阳镜
        glasses = new DiorSunGlasses(pilotStyle);
        glasses.putOn();
        //戴上迪奥-飞行员风格的太阳镜
        glasses = new BolonSunGlasses(pilotStyle);
        glasses.putOn();
        //戴上暴龙-飞行员风格的太阳镜
    }

    /**
     * 总结：
     * 如果说某个系统能够从多个角度来进行分类，且每一种分类都可能会变化，那么我们需要做
     * 的就是讲这多个角度分离出来，使得他们能独立变化，减少他们之间的耦合，这个分离过
     * 程就使用了桥接模式。所谓桥接模式就是讲抽象部分和实现部分隔离开来，使得他们能够
     * 独立变化。桥接模式将继承关系转化成关联关系，封装了变化，完成了解耦，减少了系统
     * 中类的数量，也减少了代码量。
     */
}
