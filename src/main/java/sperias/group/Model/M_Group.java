package sperias.group.Model;

import sperias.group.Entity.Group.Rank;
import sperias.group.Entity.Group.Permission;
import sperias.group.Entity.PlayerGroup;
import sperias.group.Entity.Group.Grade;
import SPGroupManager.SPGroupManager;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_Group {

    protected Player player;

    public M_Group(Player player) {
        this.player = player;
    }

    public PlayerGroup getPlayerGroup(Player player) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = SPGroupManager.getSPDatabase().prepareStatement("SELECT * FROM player_group");
        ResultSet result = stmt.executeQuery();
        PlayerGroup PlayerGroup = null;
        while(result.next())
        {
            PlayerGroup = new PlayerGroup(
                    player,
                    result.getInt("playerID"),
                    SPGroupManager.getInstance().getGroupStore().getGradeList().get(result.getInt("grade")),
                    SPGroupManager.getInstance().getGroupStore().getRankList().get(result.getInt("rank"))
            );
        }
        return PlayerGroup;
    }

    private static List<Permission> getGroupePermission(String table, String ConditionOfGroupRowName, int id) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = SPGroupManager.getSPDatabase().prepareStatement("SELECT p.name FROM " + table + " gp JOIN permission_group p ON p.id = gp.permissionID WHERE gp." + ConditionOfGroupRowName +" = ?");
        stmt.setInt(1, id);
        ResultSet permission = stmt.executeQuery();
        List<Permission> perm = new ArrayList<>();
        while(permission.next())
        {
            perm.add(new Permission(permission.getString("name")));
        }
        return perm;
    }

    public static Map<Integer, Grade> getAllGrade() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = SPGroupManager.getSPDatabase().prepareStatement("SELECT * FROM grade");
        ResultSet result = stmt.executeQuery();
        Map<Integer, Grade> GradeList = new HashMap<>();
        Grade grade = null;
        while(result.next())
        {
            grade = new Grade(result.getInt("id"), result.getString("name"),result.getString("prefix"), result.getString("color"), result.getBoolean("isStaff"));
            grade.getPermissionList().addAll(getGroupePermission("permission_grade", "gradeID", result.getInt("id")));
            GradeList.put(result.getInt("id"), grade);
        }
        return GradeList;
    }

    public static Map<Integer, Rank> getAllRank() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = SPGroupManager.getSPDatabase().prepareStatement("SELECT * FROM rank");
        ResultSet result = stmt.executeQuery();
        Map<Integer, Rank> Ranklist = new HashMap<>();
        Rank rank = null;
        while(result.next())
        {
            rank = new Rank(result.getInt("id"), result.getString("name"), result.getString("prefix"), result.getString("color"), result.getInt("price"));
            rank.getPermissionList().addAll(getGroupePermission("permission_rank", "rankID"  ,result.getInt("id")));
            Ranklist.put(result.getInt("id"), rank);
        }
        return Ranklist;
    }

    public void updateGrade(Player player) throws SQLException, ClassNotFoundException {
        PlayerGroup PlayerGroup = SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(player.getUniqueId());
        PreparedStatement stmt = SPGroupManager.getSPDatabase().prepareStatement("UPDATE player_group SET grade = ? where playerID = ?");
            stmt.setInt(1, PlayerGroup.getGrade().getId());
            stmt.setInt(2, PlayerGroup.getPlayerID());
            stmt.executeUpdate();
    }

    public void updateRank(Player player) throws SQLException, ClassNotFoundException {
        PlayerGroup PlayerGroup = SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(player.getUniqueId());
        PreparedStatement stmt = SPGroupManager.getSPDatabase().prepareStatement("UPDATE player_group SET rank = ? where playerID = ?");
        stmt.setInt(1, PlayerGroup.getRank().getId());
        stmt.setInt(2, PlayerGroup.getPlayerID());
        stmt.executeUpdate();
    }
}
