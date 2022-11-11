package sperias.group.Command;

import SPGroupManager.SPGroupManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.group.Command.Controller.GroupController;
import sperias.group.GroupStore.GroupStore;

public class Cmd_Group implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(!(sender instanceof Player)) return false;
            Player player = (Player) sender;
            GroupStore groupStore = SPGroupManager.getInstance().getGroupStore();
            GroupController groupController = new GroupController(player);
            if(!groupController.canUseThisStaffCommand()) return false;


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
                    groupStore.getPlayerGroupList().get(target.getUniqueId()).setGrade(SPGroupManager.getInstance().getGroupStore().getGradeByName(args[2]));
                    return true;
                }

                if(args[0].equalsIgnoreCase("setrank"))
                {
                    if(!groupController.canSetRank(args[2], target)) return false;
                    groupStore.getPlayerGroupList().get(target.getUniqueId()).setRank(SPGroupManager.getInstance().getGroupStore().getRankByName(args[2]));
                    return true;
                }
            }

        return true;
    }
}
