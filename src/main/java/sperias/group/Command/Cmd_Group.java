package sperias.group.Command;

import net.md_5.bungee.api.ChatColor;
import sperias.group.Entity.Group.Grade;
import sperias.group.Entity.Group.Rank;
import sperias.group.GroupManager.GroupManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sperias.group.Command.Controller.GroupController;

public class Cmd_Group implements CommandExecutor {

    private GroupManager plugin;

    public Cmd_Group(GroupManager plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


            if(!(sender instanceof Player)) return false;
            Player player = (Player) sender;
            GroupController groupController = new GroupController(player, plugin);
            if(!groupController.hasPermission("sperias.group.command.spgroup")) return false;
            if(args.length == 1)
            {
                if(args[0].equalsIgnoreCase("grade"))
                {
                    StringBuilder GradeList = new StringBuilder();
                    GradeList.append("§aVoici la liste des grades : ");
                    for(Grade grade : plugin.getGrades().values())
                    {
                        GradeList.append(grade.getColor()).append(grade.getName()).append(" ");
                    }
                    player.sendMessage(GradeList.toString());
                    return true;
                }

                if(args[0].equalsIgnoreCase("rank"))
                {
                    StringBuilder RankList = new StringBuilder();
                    RankList.append("§aVoici la liste des ranks : ");
                    for(Rank rank : plugin.getRanks().values())
                    {
                        RankList.append(rank.getColor()).append(rank.getName()).append(" ");
                    }
                    player.sendMessage(RankList.toString());
                    return true;
                }
            }

            if(args.length == 3)
            {
                Player target = Bukkit.getPlayer(args[1]);
                String groupName = args[2];
                if(args[0].equalsIgnoreCase("setgrade"))
                {
                    if(!groupController.canSetGrade(target, groupName)) return false;


                    Grade newGrade = plugin.getGrades().get(groupName);
                    player.sendMessage("§a" + target.getName() + " est maintenant " + newGrade.getColor() + newGrade.getName());
                    target.sendMessage("§aFélicitation, vous êtes maintenant " + newGrade.getColor() + newGrade.getName());

                    plugin.getPlayerGroups().get(target.getUniqueId()).setGrade(newGrade);
                    return true;
                }

                if(args[0].equalsIgnoreCase("setrank"))
                {
                    if(!groupController.canSetRank(target, groupName)) return false;

                    Rank newRank = plugin.getRanks().get(groupName);
                    player.sendMessage("§a" + target.getName() + " est maintenant " + newRank.getColor() + newRank.getName());
                    target.sendMessage("§aFélicitation, vous êtes maintenant " + newRank.getColor() + newRank.getName());

                    plugin.getPlayerGroups().get(target.getUniqueId()).setRank(newRank);
                    return true;
                }
            }

        return false;
    }
}
