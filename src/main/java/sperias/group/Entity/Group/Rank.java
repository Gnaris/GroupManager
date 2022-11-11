package sperias.group.Entity.Group;

public class Rank extends Group {

    private int price;
    private int nbMaxLand;

    public Rank(int id, String name, String prefix, String color, int price, int nbMaxLand) {
        super(id, name, prefix, color);
        this.price = price;
        this.nbMaxLand = nbMaxLand;
    }

    public int getPrice() {
        return price;
    }
    public int getNbMaxLand() {
        return nbMaxLand;
    }
}
