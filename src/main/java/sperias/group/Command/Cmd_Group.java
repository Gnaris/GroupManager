package sperias.group.Command;

import SPGroupManager.SPGroupManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.group.Command.Controller.GroupController;

public class Cmd_Group implements CommandExecutor {

    private SPGroupManager plugin;

    public Cmd_Group(SPGroupManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


            if(!(sender instanceof Player)) return false;
            Player player = (Player) sender;
            GroupController groupController = new GroupController(player, plugin);
            if(!groupController.havePermission("sperias.group.command.spgroup")) return false;
            if(args.length == 1)
            {
                if(args[0].equalsIgnoreCase("grade"))
                {
                    return groupController.canShowGradeList();
                }

                if(args[0].equalsIgnoreCase("rank"))
                {
                    return groupController.canShowRankList();
                }
            }

            if(args.length == 3)
            {
                Player target = Bukkit.getPlayer(args[1]);
                if(args[0].equalsIgnoreCase("setgrade"))
                {
                    if(!groupController.canSetGrade(args[2], target)) return false;
                    plugin.getPlayerGroupList().get(target.getUniqueId()).setGrade(plugin.getGradeByName(args[2]));
                    return true;
                }

                if(args[0].equalsIgnoreCase("setrank"))
                {
                    if(!groupController.canSetRank(args[2], target)) return false;
                    plugin.getPlayerGroupList().get(target.getUniqueId()).setRank(plugin.getRankByName(args[2]));
                    return true;
                }
            }

        return true;
    }
}
