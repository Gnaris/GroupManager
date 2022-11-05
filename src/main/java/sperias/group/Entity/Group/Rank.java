package sperias.group.Entity.Group;

public class Rank extends Group {

    private int price;

    public Rank(int id, String name, String prefix, String color, int price) {
        super(id, name, prefix, color);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
