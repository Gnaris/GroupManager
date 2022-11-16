package sperias.group.Entity;

import SPGroupManager.SPGroupManager;
import sperias.group.Entity.Group.Rank;
import sperias.group.Entity.Group.Grade;
import sperias.group.Model.Thread.UpdatePlayerGradeThread;
import sperias.group.Model.Thread.UpdatePlayerRankThread;
import org.bukkit.entity.Player;

public class PlayerGroup {

    private Player player;
    private Grade grade;
    private Rank rank;

    private final SPGroupManager plugin;

    public PlayerGroup(Player player, Grade grade, Rank rank, SPGroupManager plugin) {
        this.player = player;
        this.grade = grade;
        this.rank = rank;
        this.plugin = plugin;
    }

    public void initializePlayerPermission(boolean value)
    {
        grade.getPermissionList().forEach(permission -> player.addAttachment(plugin).setPermission(permission, value));
        rank.getPermissionList().forEach(permission -> player.addAttachment(plugin).setPermission(permission, value));
    }

    public Grade getGrade() {
        return grade;
    }

    public Rank getRank() {
        return rank;
    }

    public void setGrade(Grade grade) {
        this.initializePlayerPermission(false);
        this.grade = grade;
        this.initializePlayerPermission(true);
        new Thread(new UpdatePlayerGradeThread(player, plugin)).start();
    }

    public void setRank(Rank Rank) {
        this.initializePlayerPermission(false);
        this.rank = Rank;
        this.initializePlayerPermission(true);
        new Thread(new UpdatePlayerRankThread(player, plugin)).start();
    }
}
