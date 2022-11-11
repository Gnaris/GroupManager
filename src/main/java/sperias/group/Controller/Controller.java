package sperias.group.Controller;

import SPGroupManager.SPGroupManager;
import org.bukkit.entity.Player;
import sperias.group.Entity.Group.Grade;
import sperias.group.Entity.Group.Rank;
import sperias.group.Entity.PlayerGroup;
import sperias.group.Model.GroupModel;

import java.util.Map;
import java.util.UUID;

public class Controller extends GroupModel {

    protected Map<UUID, PlayerGroup> playerGroupList;
    protected Map<Integer, Grade> gradeList;
    protected Map<Integer, Rank> rankList;

    public Controller(Player player) {
        super(player);
        this.playerGroupList = SPGroupManager.getInstance().getGroupStore().getPlayerGroupList();
        this.gradeList = SPGroupManager.getInstance().getGroupStore().getGradeList();
        this.rankList = SPGroupManager.getInstance().getGroupStore().getRankList();
    }


}
