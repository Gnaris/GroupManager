package SPGroupManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sperias.group.Command.Cmd_Group;
import sperias.group.Entity.PlayerGroup;
import sperias.group.Event.E_GroupManager;
import sperias.group.Model.GroupModel;
import sperias.group.GroupStore.GroupStore;
import org.bukkit.plugin.java.JavaPlugin;
import sperias.gnaris.SPDatabase.SPDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;


public final class SPGroupManager extends JavaPlugin {

    private final SPDatabase SPDatabase = (SPDatabase) getServer().getPluginManager().getPlugin("SP_Database");

    private GroupStore groupStore;

    private static SPGroupManager INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        Objects.requireNonNull(getCommand("spgroup")).setExecutor(new Cmd_Group());

        getServer().getPluginManager().registerEvents(new E_GroupManager(), this);

        try {
            this.groupStore = new GroupStore(GroupModel.GET_ALL_GRADE(), GroupModel.GET_ALL_RANK());
            GroupModel.GET_ALL_GRADE_PERMISSION().forEach(permission -> groupStore.getGradeList().get(permission.getGroupID()).getPermissionList().add(permission.getName()));
            GroupModel.GET_ALL_RANK_PERMISSION().forEach(permission -> groupStore.getRankList().get(permission.getGroupID()).getPermissionList().add(permission.getName()));
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        GroupModel groupModel;
        for(Player player : Bukkit.getOnlinePlayers())
        {
            groupModel = new GroupModel(player);
            try {
                groupStore.getPlayerGroupList().put(player.getUniqueId(), groupModel.getPlayerGroup());
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        if(groupStore.getPlayerGroupList().size() > 0)
        {
            for(PlayerGroup playerGroup : groupStore.getPlayerGroupList().values())
            {
                playerGroup.initializePlayerPermission(true);
            }
        }
    }

    public static SPGroupManager getInstance()
    {
        return SPGroupManager.INSTANCE;
    }
    public GroupStore getGroupStore() {
        return groupStore;
    }
    public static Connection getDatabase() throws SQLException, ClassNotFoundException {
        return SPGroupManager.getInstance().SPDatabase.getSPDatabase().getDatabase();
    }
}
