package sperias.group.Entity.Group;

import java.util.ArrayList;
import java.util.List;

public abstract class Group {

    protected int id;
    protected String name;
    protected String prefix;
    protected String color;
    protected List<Permission> PermissionList = new ArrayList<>();

    public Group(int id, String name, String prefix, String color) {
        this.id = id;
        this.name = name;
        this.prefix = prefix;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public List<Permission> getPermissionList() {
        return PermissionList;
    }

    public String getColor() {
        return color;
    }
}
