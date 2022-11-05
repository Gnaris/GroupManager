package sperias.group.Command;

import sperias.group.Controller.C_Group;
import sperias.group.Entity.Group.Rank;
import sperias.group.Entity.Group.Grade;
import SPGroupManager.SPGroupManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cmd_Group implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(!(sender instanceof Player)) return false;
            Player player = (Player) sender;
            C_Group CGroup = new C_Group(player);
            if(!CGroup.canUseThisCommand()) return false;
            switch (args.length)
            {
                //spgroup {grade/Rank}
                case 1 :
                {
                    switch (args[0].toLowerCase())
                    {
                        case "grade" :
                        {
                            StringBuilder GradeList = new StringBuilder();
                            GradeList.append("§aVoici la liste des grades : ");
                            for(Grade grade : SPGroupManager.getInstance().getGroupStore().getGradeList().values())
                            {
                                GradeList.append(ChatColor.of(grade.getColor()) + grade.getName() + " ");
                            }
                            player.sendMessage(GradeList.toString());
                            break;
                        }
                        case "rank" :
                        {
                            StringBuilder RankList = new StringBuilder();
                            RankList.append("§aVoici la liste des Ranks : ");
                            for(Rank aRank : SPGroupManager.getInstance().getGroupStore().getRankList().values())
                            {
                                RankList.append(ChatColor.of(aRank.getColor()) + aRank.getName() + " ");
                            }
                            player.sendMessage(RankList.toString());
                            break;
                        }
                        default:
                        {
                            player.sendMessage("§cMauvaise Commande ?");
                            break;
                        }
                    }
                    break;
                }
                //spgroup {setgrade}/{setRank} {name} {group}
                case 3 :
                {
                    Player target = Bukkit.getPlayer(args[1]);
                    if(!(CGroup.existingTarget(target))) return false;
                    switch (args[0].toLowerCase())
                    {
                        case "setgrade" :
                        {
                            if(!CGroup.canSetGrade(args[2])) return false;
                            SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(target.getUniqueId()).setGrade(SPGroupManager.getInstance().getGroupStore().getGradeByName(args[2]));
                            break;
                        }
                        case "setrank" :
                        {
                            if(!CGroup.canSetRank(args[2])) return false;
                            SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(target.getUniqueId()).setRank(SPGroupManager.getInstance().getGroupStore().getRankByName(args[2]));
                            break;
                        }
                        default:
                        {
                            player.sendMessage("§cPour changer le grade faites /spgroup setgrade {pseudo} {nom du grade}");
                            player.sendMessage("§cPour changer le Rank faites /spgroup setrank {pseudo} {nom du Rank}");
                            break;
                        }
                    }
                    break;
                }

                default:
                {
                    player.sendMessage("§cMauvais manipulation de la commande");
                    break;
                }
            }

        return true;
    }
}
