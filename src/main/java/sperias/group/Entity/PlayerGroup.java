package sperias.group.Entity;

import SPGroupManager.SPGroupManager;
import sperias.group.Entity.Group.Rank;
import sperias.group.Entity.Group.Grade;
import sperias.group.Model.Thread.UpdatePlayerGradeThread;
import sperias.group.Model.Thread.UpdatePlayerRankThread;
import org.bukkit.entity.Player;

public class PlayerGroup {

    private Player player;
    private int playerID;
    private Grade grade;
    private Rank rank;

    public PlayerGroup(Player player, int PlayerID, Grade grade, Rank aRank) {
        this.player = player;
        this.playerID = PlayerID;
        this.grade = grade;
        this.rank = aRank;
    }

    public void initializePlayerPermission(boolean value)
    {
        SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(player.getUniqueId()).getRank().getPermissionList()
                .forEach(permission -> player.addAttachment(SPGroupManager.getInstance()).setPermission(permission, value));
        SPGroupManager.getInstance().getGroupStore().getPlayerGroupList().get(player.getUniqueId()).getGrade().getPermissionList()
                .forEach(permission -> player.addAttachment(SPGroupManager.getInstance()).setPermission(permission, value));
    }

    public Player getPlayer() {
        return player;
    }

    public int getPlayerID() {
        return playerID;
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
        Thread updatePlayerGradeThread = new Thread(new UpdatePlayerGradeThread(this.player));
        updatePlayerGradeThread.start();
    }

    public void setRank(Rank Rank) {
        this.initializePlayerPermission(false);
        this.rank = Rank;
        this.initializePlayerPermission(true);
        Thread updatePlayerRankThread = new Thread(new UpdatePlayerRankThread(this.player));
        updatePlayerRankThread.start();
    }
}
