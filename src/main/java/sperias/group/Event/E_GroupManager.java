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

    @EventHandler (priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent e) throws SQLException, ClassNotFoundException {
        GroupModel groupModel = new GroupModel(e.getPlayer());
        SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().put(e.getPlayer().getUniqueId(), groupModel.getPlayerGroup());
        SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(e.getPlayer().getUniqueId()).initializePlayerPermission(true);
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent e){
        Thread UpdatePlayerGradeThread = new Thread(new UpdatePlayerGradeThread(e.getPlayer()));
        UpdatePlayerGradeThread.start();
        Thread UpdatePlayerRankThread = new Thread(new UpdatePlayerRankThread(e.getPlayer()));
        UpdatePlayerRankThread.start();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        e.setCancelled(true);
        Grade grade = SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(e.getPlayer().getUniqueId()).getGrade();
        Rank aRank = SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(e.getPlayer().getUniqueId()).getRank();

        if(grade.isStaff)
        {
            Bukkit.broadcastMessage(ChatColor.of(grade.getColor()) + grade.getPrefix() + " " + ChatColor.of(aRank.getColor()) + aRank.getPrefix() + " " + e.getPlayer().getName() + ChatColor.of(grade.getColor()) + " → " + e.getMessage());
        }

        if(!grade.isStaff)
        {
            Bukkit.broadcastMessage(ChatColor.of(grade.getColor()) + grade.getPrefix() + " " + ChatColor.of(aRank.getColor()) + aRank.getPrefix() + " " + e.getPlayer().getName() + ChatColor.of(aRank.getColor()) + " → " + ChatColor.of("#CECECE") + e.getMessage());
        }
    }
}
