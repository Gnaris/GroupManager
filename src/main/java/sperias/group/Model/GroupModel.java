package sperias.group.Model;

import sperias.group.Entity.Group.Permission;
import sperias.group.Entity.Group.Rank;
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

public class GroupModel {

    protected Player player;

    public GroupModel(Player player) {
        this.player = player;
    }

    public PlayerGroup getPlayerGroup() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = SPGroupManager.getDatabase().prepareStatement("SELECT * FROM player_group");
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

    public static List<Permission> GET_ALL_GRADE_PERMISSION() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = SPGroupManager.getDatabase().prepareStatement("SELECT gradeID, pg.name FROM permission_grade pgr JOIN permission_group pg ON pg.id = pgr.permissionID");
        ResultSet result = stmt.executeQuery();
        List<Permission> gradePermissionList = new ArrayList<>();
        while(result.next())
        {
            gradePermissionList.add(new Permission(result.getInt("gradeID"), result.getString("name")));
        }
        return gradePermissionList;
    }

    public static Map<Integer, Grade> GET_ALL_GRADE() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = SPGroupManager.getDatabase().prepareStatement("SELECT * FROM grade");
        ResultSet result = stmt.executeQuery();
        Map<Integer, Grade> GradeList = new HashMap<>();
        Grade grade = null;
        while(result.next())
        {
            grade = new Grade(result.getInt("id"), result.getString("name"),result.getString("prefix"), result.getString("color"), result.getBoolean("isStaff"));
            GradeList.put(result.getInt("id"), grade);
        }
        return GradeList;
    }

    public static List<Permission> GET_ALL_RANK_PERMISSION() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = SPGroupManager.getDatabase().prepareStatement("SELECT rankID, pg.name FROM permission_rank pr JOIN permission_group pg ON pg.id = pr.permissionID");
        ResultSet result = stmt.executeQuery();
        List<Permission> rankPermissionList = new ArrayList<>();
        while(result.next())
        {
            rankPermissionList.add(new Permission(result.getInt("rankID"), result.getString("name")));
        }
        return rankPermissionList;
    }

    public static Map<Integer, Rank> GET_ALL_RANK() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = SPGroupManager.getDatabase().prepareStatement("SELECT * FROM rank");
        ResultSet result = stmt.executeQuery();
        Map<Integer, Rank> rankList = new HashMap<>();
        Rank rank = null;
        while(result.next())
        {
            rank = new Rank(result.getInt("id"), result.getString("name"), result.getString("prefix"), result.getString("color"), result.getInt("price"), result.getInt("nbMaxLand"));
            rankList.put(result.getInt("id"), rank);
        }
        return rankList;
    }

    public void updateGrade(Player player) throws SQLException, ClassNotFoundException {
        PlayerGroup PlayerGroup = SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(player.getUniqueId());
        PreparedStatement stmt = SPGroupManager.getDatabase().prepareStatement("UPDATE player_group SET grade = ? where playerID = ?");
            stmt.setInt(1, PlayerGroup.getGrade().getId());
            stmt.setInt(2, PlayerGroup.getPlayerID());
            stmt.executeUpdate();
    }

    public void updateRank(Player player) throws SQLException, ClassNotFoundException {
        PlayerGroup PlayerGroup = SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(player.getUniqueId());
        PreparedStatement stmt = SPGroupManager.getDatabase().prepareStatement("UPDATE player_group SET rank = ? where playerID = ?");
        stmt.setInt(1, PlayerGroup.getRank().getId());
        stmt.setInt(2, PlayerGroup.getPlayerID());
        stmt.executeUpdate();
    }
}
