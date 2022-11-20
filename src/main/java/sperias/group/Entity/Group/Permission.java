package sperias.group.Entity.Group;

public class Permission {

    private final String groupName;
    private final String permissionName;

    public Permission(String groupName, String permissionName) {
        this.groupName = groupName;
        this.permissionName = permissionName;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getPermissionName() {
        return permissionName;
    }
}
