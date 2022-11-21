package sperias.group.Entity.Group;

import org.bukkit.Material;

public class Rank extends Group {


    private String prefixF;
    private Material material;
    private String description;
    private int price;
    private boolean isPrenium;
    private int nbLand;
    private int nbHome;


    public Rank(int id, String name, String prefixM, String prefixF, Material material, String description, String color, int price, boolean isPrenium, int nbLand, int nbHome) {
        super(id, name, prefixM, color);
        this.prefixF = prefixF;
        this.material = material;
        this.description = description;
        this.price = price;
        this.isPrenium = isPrenium;
        this.nbLand = nbLand;
        this.nbHome = nbHome;
    }

    public int getPrice() {
        return price;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDescription() {
        return description;
    }
}
