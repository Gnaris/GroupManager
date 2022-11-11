package sperias.group.Command.Controller;

import sperias.group.Controller.Controller;
import sperias.group.Entity.Group.Rank;
import sperias.group.Entity.Group.Grade;
import SPGroupManager.SPGroupManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class GroupController extends Controller {

    public GroupController(Player player) {
        super(player);
    }

    private boolean existingTarget(Player target)
    {
        if(target == null)
        {
            this.player.sendMessage("§cCe joueur n'existe pas");
            return false;
        }

        return true;
    }

    public boolean existingGrade(String GradeName)
    {
        int gradeID = 1; boolean finded = false;
        Map<Integer, Grade> GradeList = SPGroupManager.getInstance().getGroupStore().getGradeList();
        while(gradeID <= GradeList.size() && !finded)
        {
            if(GradeList.get(gradeID) != null && GradeList.get(gradeID).getName().equalsIgnoreCase(GradeName)) finded = true;
            gradeID++;
        }
        return finded;
    }

    public boolean existingRank(String RankName)
    {
        int RankgID = 1; boolean finded = false;
        Map<Integer, Rank> RankList = SPGroupManager.getInstance().getGroupStore().getRankList();
        while(RankgID <= RankList.size() && !finded)
        {
            if(RankList.get(RankgID) != null && RankList.get(RankgID).getName().equalsIgnoreCase(RankName)) finded = true;
            RankgID++;
        }
        return finded;
    }

    public boolean canSetGrade(String gradeName, Player target)
    {
        if(!this.existingTarget(target)) return false;
        if(!player.hasPermission("sperias.group.command.setgroup"))
        {
            this.player.sendMessage("§cTu n'as pas accès à cette commande");
            return false;
        }
        if(!this.existingGrade(gradeName))
        {
            this.player.sendMessage("§cCe grade n'existe pas");
            return false;
        }

        Grade NextGrade = SPGroupManager.getInstance().getGroupStore().getGradeByName(gradeName);
        player.sendMessage("§a" + target.getName() + " est maintenant " + ChatColor.of(NextGrade.getColor()) + NextGrade.getName());
        target.sendMessage("§aFélicitation, vous êtes maintenant " + ChatColor.of(NextGrade.getColor()) + NextGrade.getName());
        return true;
    }

    public boolean canSetRank(String rankName, Player target)
    {
        if(!this.existingTarget(target)) return false;
        if(!player.hasPermission("sperias.group.command.setgroup"))
        {
            this.player.sendMessage("§cTu n'as pas accès à cette commande");
            return false;
        }
        if(!this.existingRank(rankName))
        {
            this.player.sendMessage("§cCe grade n'existe pas");
            return false;
        }

        Rank nextRank = SPGroupManager.getInstance().getGroupStore().getRankByName(rankName);
        player.sendMessage("§a" + target.getName() + " est maintenant " + ChatColor.of(nextRank.getColor()) + nextRank.getName());
        target.sendMessage("§aFélicitation, vous êtes maintenant " + ChatColor.of(nextRank.getColor()) + nextRank.getName());
        return true;
    }

    public boolean canUseThisStaffCommand()
    {
        if(!SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(this.player.getUniqueId()).getGrade().isStaff() && !this.player.isOp())
        {
            player.sendMessage("§cTu n'as pas accès à cette commande");
            return false;
        }
        return true;
    }

    public boolean canShowGradeList()
    {
        if(!player.hasPermission("sperias.group.command.showgroup"))
        {
            player.sendMessage("§cVous n'avez pas la permission");
            return false;
        }

        StringBuilder GradeList = new StringBuilder();
        GradeList.append("§aVoici la liste des grades : ");
        for(Grade grade : SPGroupManager.getInstance().getGroupStore().getGradeList().values())
        {
            GradeList.append(ChatColor.of(grade.getColor()) + grade.getName() + " ");
        }
        player.sendMessage(GradeList.toString());
        return true;
    }

    public boolean canShowRankList()
    {
        if(!player.hasPermission("sperias.group.command.showgroup"))
        {
            player.sendMessage("§cVous n'avez pas la permission");
            return false;
        }

        StringBuilder RankList = new StringBuilder();
        RankList.append("§aVoici la liste des ranks : ");
        for(Rank aRank : SPGroupManager.getInstance().getGroupStore().getRankList().values())
        {
            RankList.append(ChatColor.of(aRank.getColor()) + aRank.getName() + " ");
        }
        player.sendMessage(RankList.toString());
        return true;
    }
}
