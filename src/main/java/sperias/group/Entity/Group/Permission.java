package sperias.group.Entity.Group;

public class Permission {

    private int groupID;
    private String name;

    public Permission(int groupID, String name) {
        this.groupID = groupID;
        this.name = name;
    }

    public int getGroupID() {
        return groupID;
    }

    public String getName() {
        return name;
    }
}
