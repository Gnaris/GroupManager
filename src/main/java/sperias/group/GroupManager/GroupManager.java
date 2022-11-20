package sperias.group.GroupManager;

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


public final class GroupManager extends JavaPlugin {

    private Map<String, Grade> grades;
    private Map<String, Rank> ranks;
    private final Map<UUID, PlayerGroup> playerGroups = new HashMap<>();

    @Override
    public void onEnable() {

        Objects.requireNonNull(getCommand("group")).setExecutor(new Cmd_Group(this));

        getServer().getPluginManager().registerEvents(new E_GroupManager(this), this);


        GroupModel groupModel = new GroupModel(this);
        try {
            grades = groupModel.getAllGrade();
            ranks = groupModel.getAllRank();
            groupModel.getAllGradePermission().forEach(p -> grades.get(p.getGroupName()).getPermissionList().add(p.getPermissionName()));
            groupModel.getAllRankPermission().forEach(p -> ranks.get(p.getGroupName()).getPermissionList().add(p.getPermissionName()));
            Bukkit.getOnlinePlayers().forEach(player -> {
                try {
                    playerGroups.put(player.getUniqueId(), groupModel.getPlayerGroup(player));
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Grade> getGrades() {
        return grades;
    }
    public Map<String, Rank> getRanks() {
        return ranks;
    }
    public Map<UUID, PlayerGroup> getPlayerGroups() {
        return playerGroups;
    }
}
