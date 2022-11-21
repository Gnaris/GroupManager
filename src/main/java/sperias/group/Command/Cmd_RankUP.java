package sperias.group.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.group.Entity.GUI.RankGUI;
import sperias.group.GroupManager.GroupManager;

public class Cmd_RankUP implements CommandExecutor {

    private final GroupManager plugin;

    public Cmd_RankUP(GroupManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        player.openInventory(RankGUI.getInventory(player, plugin));
        return true;
    }
}
