package sperias.group.Command.Controller;

import SPGroupManager.SPGroupManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import sperias.group.Controller.Controller;
import sperias.group.Entity.Group.Grade;
import sperias.group.Entity.Group.Rank;

public class GroupController extends Controller {


    public GroupController(Player player, SPGroupManager plugin) {
        super(player, plugin);
    }

    private boolean targetExist(Player target)
    {
        if(target == null)
        {
            player.sendMessage("§cCe joueur n'existe pas");
            return false;
        }

        return true;
    }

    public boolean existingGrade(String gradeName)
    {
        for(Grade grade : plugin.getGradeList().values())
        {
            if(grade.getName().equalsIgnoreCase(gradeName)) return true;
        }
        return false;
    }

    public boolean existingRank(String rankName)
    {
        for(Rank rank : plugin.getRankList().values())
        {
            if(rank.getName().equalsIgnoreCase(rankName)) return true;
        }
        return false;
    }

    public boolean canSetGrade(String gradeName, Player target)
    {
        if(!this.targetExist(target)) return false;
        if(!this.existingGrade(gradeName))
        {
            this.player.sendMessage("§cCe grade n'existe pas");
            return false;
        }

        Grade nextGrade = plugin.getGradeByName(gradeName);
        player.sendMessage("§a" + target.getName() + " est maintenant " + ChatColor.of(nextGrade.getColor()) + nextGrade.getName());
        target.sendMessage("§aFélicitation, vous êtes maintenant " + ChatColor.of(nextGrade.getColor()) + nextGrade.getName());
        return true;
    }

    public boolean canSetRank(String rankName, Player target)
    {
        if(!this.targetExist(target)) return false;
        if(!this.existingRank(rankName))
        {
            this.player.sendMessage("§cCe grade n'existe pas");
            return false;
        }

        Rank nextRank = plugin.getRankByName(rankName);
        player.sendMessage("§a" + target.getName() + " est maintenant " + ChatColor.of(nextRank.getColor()) + nextRank.getName());
        target.sendMessage("§aFélicitation, vous êtes maintenant " + ChatColor.of(nextRank.getColor()) + nextRank.getName());
        return true;
    }

    public boolean canShowGradeList()
    {
        StringBuilder GradeList = new StringBuilder();
        GradeList.append("§aVoici la liste des grades : ");
        for(Grade grade : plugin.getGradeList().values())
        {
            GradeList.append(ChatColor.of(grade.getColor())).append(grade.getName()).append(" ");
        }
        player.sendMessage(GradeList.toString());
        return true;
    }

    public boolean canShowRankList()
    {
        StringBuilder RankList = new StringBuilder();
        RankList.append("§aVoici la liste des ranks : ");
        for(Rank rank : plugin.getRankList().values())
        {
            RankList.append(ChatColor.of(rank.getColor())).append(rank.getName()).append(" ");
        }
        player.sendMessage(RankList.toString());
        return true;
    }
}
