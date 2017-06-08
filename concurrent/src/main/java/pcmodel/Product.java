package pcmodel;

/**
 * Created by 李恒名 on 2017/6/8.
 *
 *   产品
 */
public class Product {

    private String name;

    public Product(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
