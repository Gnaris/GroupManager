package sperias.group.Event;

import sperias.group.Entity.Group.Rank;
import sperias.group.Entity.Group.Grade;
import sperias.group.Model.GroupModel;
import sperias.group.Model.Thread.UpdatePlayerGradeThread;
import sperias.group.Model.Thread.UpdatePlayerRankThread;
import SPGroupManager.SPGroupManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class E_GroupManager implements Listener {

    private final SPGroupManager plugin;

    public E_GroupManager(SPGroupManager plugin) {
        this.plugin = plugin;
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent e) throws SQLException, ClassNotFoundException {
        plugin.getPlayerGroupList().put(e.getPlayer().getUniqueId(), new GroupModel(plugin).getPlayerGroup(e.getPlayer()));
        plugin.getPlayerGroupList().get(e.getPlayer().getUniqueId()).initializePlayerPermission(true);
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent e){
        new Thread(new UpdatePlayerGradeThread(e.getPlayer(), plugin)).start();
        new Thread(new UpdatePlayerRankThread(e.getPlayer(), plugin)).start();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        e.setCancelled(true);
        Grade grade = plugin.getPlayerGroupList().get(e.getPlayer().getUniqueId()).getGrade();
        Rank rank = plugin.getPlayerGroupList().get(e.getPlayer().getUniqueId()).getRank();

        if(grade.isStaff)
        {
            Bukkit.broadcastMessage(ChatColor.of(grade.getColor()) + grade.getPrefix() + " " + ChatColor.of(rank.getColor()) + rank.getPrefix() + " " + e.getPlayer().getName() + ChatColor.of(grade.getColor()) + " → " + e.getMessage());
            return;
        }

        Bukkit.broadcastMessage(ChatColor.of(grade.getColor()) + grade.getPrefix() + " " + ChatColor.of(rank.getColor()) + rank.getPrefix() + " " + e.getPlayer().getName() + ChatColor.of(rank.getColor()) + " → " + ChatColor.of("#CECECE") + e.getMessage());
    }
}
