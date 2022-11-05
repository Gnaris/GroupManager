package sperias.group.Controller;

import sperias.group.Model.M_Group;
import sperias.group.Entity.PlayerGroup;
import SPGroupManager.SPGroupManager;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class GroupController extends M_Group {

    protected PlayerGroup PlayerGroup;

    public GroupController(Player player) {
        super(player);
        this.PlayerGroup = SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(player.getUniqueId());
    }

    public void insertPlayerGroupList() throws SQLException, ClassNotFoundException {
        SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().put(this.player.getUniqueId(), this.getPlayerGroup(this.player));
    }

    public boolean hasPermission(String PermissionName)
    {
        PlayerGroup PlayerGroup = SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(this.player.getUniqueId());
        if(PlayerGroup.getGrade().getPermissionList().stream().anyMatch(permission -> permission.getName().equalsIgnoreCase(PermissionName))) return true;
        if(PlayerGroup.getRank().getPermissionList().stream().anyMatch(permission -> permission.getName().equalsIgnoreCase(PermissionName))) return true;
        return false;
    }

    public boolean isGradeEqual(String GradeName)
    {
        return SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(this.player.getUniqueId()).getGrade().getName().equalsIgnoreCase(GradeName);
    }
    public boolean isRankEqual(String RankName)
    {
        return SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(this.player.getUniqueId()).getRank().getName().equalsIgnoreCase(RankName);
    }
}
