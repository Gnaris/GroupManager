package sperias.group.Model;

import org.bukkit.Bukkit;
import sperias.gnaris.SPDatabase.SPDatabase;
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

    private SPGroupManager plugin;
    private final SPDatabase database = (SPDatabase) Bukkit.getServer().getPluginManager().getPlugin("SP_Database");

    public GroupModel(SPGroupManager plugin) {
        this.plugin = plugin;
    }

    public PlayerGroup getPlayerGroup(Player player) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT gradeID, rankID FROM player");
        ResultSet result = stmt.executeQuery();
        PlayerGroup PlayerGroup = null;
        while(result.next())
        {
            PlayerGroup = new PlayerGroup(
                    player,
                    plugin.getGradeList().get(result.getInt("gradeID")),
                    plugin.getRankList().get(result.getInt("rankID")),
                    plugin
            );
        }
        return PlayerGroup;
    }

    public List<Permission> getAllGradePermission() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT gradeID, pg.name FROM permission_grade pgr JOIN permission_group pg ON pg.id = pgr.permissionID");
        ResultSet result = stmt.executeQuery();
        List<Permission> gradePermissionList = new ArrayList<>();
        while(result.next())
        {
            gradePermissionList.add(new Permission(result.getInt("gradeID"), result.getString("name")));
        }
        return gradePermissionList;
    }

    public Map<Integer, Grade> getAllGrade() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT * FROM grade");
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

    public List<Permission> getAllRankPermission() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT rankID, pg.name FROM permission_rank pr JOIN permission_group pg ON pg.id = pr.permissionID");
        ResultSet result = stmt.executeQuery();
        List<Permission> rankPermissionList = new ArrayList<>();
        while(result.next())
        {
            rankPermissionList.add(new Permission(result.getInt("rankID"), result.getString("name")));
        }
        return rankPermissionList;
    }

    public Map<Integer, Rank> getAllRank() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT * FROM rank");
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
        PreparedStatement stmt = database.getConnection().prepareStatement("UPDATE player SET gradeID = ? where uuid = ?");
            stmt.setInt(1, plugin.getPlayerGroupList().get(player.getUniqueId()).getGrade().getId());
            stmt.setString(2, player.getUniqueId().toString());
            stmt.executeUpdate();
    }

    public void updateRank(Player player) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("UPDATE player SET rankID = ? where uuid = ?");
        stmt.setInt(1, plugin.getPlayerGroupList().get(player.getUniqueId()).getRank().getId());
        stmt.setString(2, player.getUniqueId().toString());
        stmt.executeUpdate();
    }
}
