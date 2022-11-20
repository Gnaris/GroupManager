package sperias.group.Entity.Group;

import net.md_5.bungee.api.ChatColor;

import java.util.ArrayList;
import java.util.List;

public abstract class Group {

    protected int id;
    protected String name;
    protected String prefix;
    protected String color;
    protected List<String> PermissionList = new ArrayList<>();

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

    public List<String> getPermissionList() {
        return PermissionList;
    }

    public ChatColor getColor() {
        return ChatColor.of(this.color);
    }
}
