package SPGroupManager;

import sperias.group.Command.Cmd_Group;
import sperias.group.Controller.C_Group;
import sperias.group.Event.E_GroupManager;
import sperias.group.Model.M_Group;
import sperias.group.GroupStore.GroupStore;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sperias.gnaris.SPDatabase.SPDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;


public final class SPGroupManager extends JavaPlugin {

    private final SPDatabase SPDatabase = (SPDatabase) getServer().getPluginManager().getPlugin("SP_Database");

    private GroupStore GroupStore;

    private static SPGroupManager INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;

        Objects.requireNonNull(getCommand("spgroup")).setExecutor(new Cmd_Group());

        getServer().getPluginManager().registerEvents(new E_GroupManager(), this);

        try {
            this.GroupStore = new GroupStore(M_Group.getAllGrade(), M_Group.getAllRank());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(Bukkit.getOnlinePlayers().size() > 0)
                {
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        try {
                            C_Group CGroup = new C_Group(player);
                            CGroup.insertPlayerGroupList();
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    } );
                }
    }

    public static SPGroupManager getInstance()
    {
        return SPGroupManager.INSTANCE;
    }
    public GroupStore getGroupStore() {
        return GroupStore;
    }
    public static Connection getSPDatabase() throws SQLException, ClassNotFoundException {
        return SPGroupManager.getInstance().SPDatabase.getSPDatabase().getDatabase();
    }
}
