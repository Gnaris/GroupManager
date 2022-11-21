package sperias.group.Model;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import sperias.gnaris.SPDatabase.SPDatabase;
import sperias.group.Entity.Group.Grade;
import sperias.group.Entity.Group.Permission;
import sperias.group.Entity.Group.Rank;
import sperias.group.Entity.Group.PlayerGroup;
import sperias.group.GroupManager.GroupManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GroupModel {

    private GroupManager plugin;
    private final SPDatabase database = (SPDatabase) Bukkit.getServer().getPluginManager().getPlugin("SP_Database");

    public GroupModel(GroupManager plugin) {
        this.plugin = plugin;
    }

    public PlayerGroup getPlayerGroup(Player player) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT gradeName, rankName FROM player_group_name WHERE uuid = ?");
        stmt.setString(1, player.getUniqueId().toString());
        ResultSet result = stmt.executeQuery();
        PlayerGroup Group = null;
        while(result.next())
        {
            Group = new PlayerGroup(player,
                    result.getString("sexe").charAt(0),
                    plugin.getGrades().get(result.getString("gradeName")),
                    plugin.getRanks().get(result.getString("rankName")),
                    plugin);
        }
        return Group;
    }

    public Map<String, Grade> getAllGrade() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT * FROM grade");
        ResultSet result = stmt.executeQuery();
        Map<String, Grade> GradeList = new HashMap<>();
        Grade grade = null;
        while(result.next())
        {
            grade = new Grade(result.getInt("id"),
                    result.getString("name"),
                    result.getString("prefix"),
                    result.getString("color"),
                    result.getBoolean("isStaff"));
            GradeList.put(result.getString("name"), grade);
        }
        return GradeList;
    }

    public Map<String, Rank> getAllRank() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT * FROM rank");
        ResultSet result = stmt.executeQuery();
        Map<String, Rank> rankList = new HashMap<>();
        Rank rank = null;
        while(result.next())
        {
            rank = new Rank(result.getInt("id"),
                    result.getString("name"),
                    result.getString("prefixM"),
                    result.getString("prefixF"),
                    Material.getMaterial(result.getString("material")) == null ? Material.WHITE_STAINED_GLASS : Material.getMaterial(result.getString("material")),
                    result.getString("description"),
                    result.getString("color"),
                    result.getInt("price"),
                    result.getBoolean("isPrenium"),
                    result.getInt("nbLand"),
                    result.getInt("nbHome"));
            rankList.put(result.getString("name"), rank);
        }
        return rankList;
    }
    public List<Permission> getAllRankPermission() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT * FROM rank_permission");
        ResultSet result = stmt.executeQuery();
        List<Permission> rankPermissionList = new ArrayList<>();
        while(result.next())
        {
            rankPermissionList.add(new Permission(result.getString("rankName"), result.getString("permissionName")));
        }
        return rankPermissionList;
    }

    public List<Permission> getAllGradePermission() throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT * FROM grade_permission");
        ResultSet result = stmt.executeQuery();
        List<Permission> gradePermissionList = new ArrayList<>();
        while(result.next())
        {
            gradePermissionList.add(new Permission(result.getString("gradeName"), result.getString("permissionName")));
        }
        return gradePermissionList;
    }

    public void updateGrade(UUID uuid, Grade grade){
        new Thread(() -> {
            try {
                PreparedStatement stmt = database.getConnection().prepareStatement("UPDATE player SET gradeID = ? where uuid = ?");
                stmt.setInt(1, grade.getId());
                stmt.setString(2, uuid.toString());
                stmt.executeUpdate();
            }catch (SQLException | ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void updateRank(UUID uuid, Rank rank){
        new Thread(() -> {
            try {
                PreparedStatement stmt = database.getConnection().prepareStatement("UPDATE player SET rankID = ? where uuid = ?");
                stmt.setInt(1, rank.getId());
                stmt.setString(2, uuid.toString());
                stmt.executeUpdate();
            }catch (SQLException | ClassNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
