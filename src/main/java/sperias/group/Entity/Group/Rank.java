package sperias.group.Entity.Group;

public class Rank extends Group {

    private int price;

    private int classment;
    private boolean isPrenium;
    private int nbLand;
    private int nbHome;
    private String prefixF;


    public Rank(int id, String name, String prefixM, String prefixF, String color, int price, boolean isPrenium, int nbLand, int nbHome) {
        super(id, name, prefixM, color);
        this.price = price;
        this.isPrenium = isPrenium;
        this.nbLand = nbLand;
        this.nbHome = nbHome;
        this.prefixF = prefixF;
    }

    public int getPrice() {
        return price;
    }

}
