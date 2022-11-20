package sperias.group.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import sperias.group.GroupManager.GroupManager;
import sperias.group.Model.GroupModel;

import java.sql.SQLException;

public class E_GroupManager implements Listener {

    private final GroupManager plugin;

    public E_GroupManager(GroupManager plugin) {
        this.plugin = plugin;
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent e) throws SQLException, ClassNotFoundException {
        plugin.getPlayerGroups().put(e.getPlayer().getUniqueId(), new GroupModel(plugin).getPlayerGroup(e.getPlayer()));
        plugin.getPlayerGroups().get(e.getPlayer().getUniqueId()).setPermission(true);
    }
}
