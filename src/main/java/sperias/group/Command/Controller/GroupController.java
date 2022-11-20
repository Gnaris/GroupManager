package sperias.group.Command.Controller;

import org.bukkit.entity.Player;
import sperias.group.Controller.Controller;
import sperias.group.Entity.Group.Rank;
import sperias.group.GroupManager.GroupManager;

public class GroupController extends Controller {


    public GroupController(Player player, GroupManager plugin) {
        super(player, plugin);
    }

    public boolean canSetGrade(Player target, String gradeName)
    {
        if(!this.targetExist(target)) return false;
        if(!plugin.getGrades().containsKey(gradeName))
        {
            player.sendMessage("§cCe rang n'existe pas");
            return false;
        }

        groupModel.updateGrade(target.getUniqueId(), plugin.getGrades().get(gradeName));
        return true;
    }

    public boolean canSetRank(Player target, String rankName)
    {
        if(!this.targetExist(target)) return false;
        if(!plugin.getRanks().containsKey(rankName))
        {
            player.sendMessage("§cCe rang n'existe pas");
            return false;
        }

        groupModel.updateRank(target.getUniqueId(), plugin.getRanks().get(rankName));
        return true;
    }
}
