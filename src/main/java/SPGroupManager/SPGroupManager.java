package SPGroupManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sperias.group.Command.Cmd_Group;
import sperias.group.Entity.Group.Grade;
import sperias.group.Entity.Group.Rank;
import sperias.group.Entity.PlayerGroup;
import sperias.group.Event.E_GroupManager;
import sperias.group.Model.GroupModel;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;


public final class SPGroupManager extends JavaPlugin {

    private Map<Integer, Grade> gradeList;
    private Map<Integer, Rank> rankList;
    private final Map<UUID, PlayerGroup> playerGroupList = new HashMap<>();

    @Override
    public void onEnable() {

        Objects.requireNonNull(getCommand("spgroup")).setExecutor(new Cmd_Group(this));

        getServer().getPluginManager().registerEvents(new E_GroupManager(this), this);


        GroupModel groupModel = new GroupModel(this);
        try {
            gradeList = groupModel.getAllGrade();
            rankList = groupModel.getAllRank();
            groupModel.getAllGradePermission().forEach(permission -> gradeList.get(permission.getGroupID()).getPermissionList().add(permission.getName()));
            groupModel.getAllGradePermission().forEach(permission -> rankList.get(permission.getGroupID()).getPermissionList().add(permission.getName()));
            Bukkit.getOnlinePlayers().forEach(player -> {
                try {
                    playerGroupList.put(player.getUniqueId(), groupModel.getPlayerGroup(player));
                    playerGroupList.get(player.getUniqueId()).initializePlayerPermission(true);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public Map<Integer, Grade> getGradeList() {
        return gradeList;
    }
    public Map<Integer, Rank> getRankList() {
        return rankList;
    }
    public Map<UUID, PlayerGroup> getPlayerGroupList() {
        return playerGroupList;
    }
    public Grade getGradeByName(String GradeName)
    {
        int GradeID = 1;
        Grade NewGrade = null;
        while(GradeID <= this.gradeList.size() && NewGrade == null)
        {
            if(this.gradeList.get(GradeID) != null && this.gradeList.get(GradeID).getName().equalsIgnoreCase(GradeName))
            {
                NewGrade = this.gradeList.get(GradeID);
            }
            GradeID++;
        }
        return NewGrade;
    }

    public Rank getRankByName(String RankName)
    {
        int RankID = 1;
        Rank newRank = null;
        while(RankID <= this.rankList.size() && newRank == null)
        {
            if(this.rankList.get(RankID) != null && this.rankList.get(RankID).getName().equalsIgnoreCase(RankName))
            {
                newRank = this.rankList.get(RankID);
            }
            RankID++;
        }
        return newRank;
    }
}
