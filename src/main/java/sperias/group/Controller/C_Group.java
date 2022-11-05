package sperias.group.Controller;

import sperias.group.Entity.Group.Rank;
import sperias.group.Entity.Group.Grade;
import SPGroupManager.SPGroupManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;

public class C_Group extends GroupController {

    public C_Group(Player player) {
        super(player);
    }

    public boolean existingTarget(Player target)
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

    public boolean existingRanK(String RankName)
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

    public boolean canSetGrade(String GradeName)
    {
        if(!this.hasPermission("sperias.group.command.setgrade") && !this.player.isOp())
        {
            this.player.sendMessage("§cTu n'as pas accès à cette commande");
            return false;
        }
        if(!this.existingGrade(GradeName))
        {
            this.player.sendMessage("§cCe grade n'existe pas");
            return false;
        }

        Grade NextGrade = SPGroupManager.getInstance().getGroupStore().getGradeByName(GradeName);
        this.player.sendMessage("§aFélicitation, vous êtes maintenant " + ChatColor.of(NextGrade.getColor()) + NextGrade.getName());
        return true;
    }

    public boolean canSetRank(String RankName)
    {
        if(!this.hasPermission("sperias.group.command.setrank") && !this.player.isOp())
        {
            this.player.sendMessage("§cTu n'as pas accès à cette commande");
            return false;
        }
        if(!this.existingRanK(RankName))
        {
            this.player.sendMessage("§cCe grade n'existe pas");
            return false;
        }

        Rank NextRank = SPGroupManager.getInstance().getGroupStore().getRankByName(RankName);
        this.player.sendMessage("§aFélicitation, vous êtes maintenant " + ChatColor.of(NextRank.getColor()) + NextRank.getName());
        return true;
    }

    public boolean canUseThisCommand()
    {
        if(!SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(this.player.getUniqueId()).getGrade().isStaff() && !this.player.isOp())
        {
            player.sendMessage("§cTu n'as pas accès à cette commande");
            return false;
        }
        return true;
    }
}
