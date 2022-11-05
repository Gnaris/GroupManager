package sperias.group.Entity;

import sperias.group.Entity.Group.Rank;
import sperias.group.Entity.Group.Grade;
import sperias.group.Model.Thread.UpdatePlayerGradeThread;
import sperias.group.Model.Thread.UpdatePlayerRankThread;
import org.bukkit.entity.Player;

public class PlayerGroup {

    private Player player;
    private int PlayerID;
    private Grade grade;
    private sperias.group.Entity.Group.Rank Rank;

    public PlayerGroup(Player player, int PlayerID, Grade grade, Rank aRank) {
        this.player = player;
        this.PlayerID = PlayerID;
        this.grade = grade;
        this.Rank = aRank;
    }

    public Player getPlayer() {
        return player;
    }

    public int getPlayerID() {
        return PlayerID;
    }

    public Grade getGrade() {
        return grade;
    }

    public Rank getRank() {
        return Rank;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
        Thread updatePlayerGradeThread = new Thread(new UpdatePlayerGradeThread(this.player));
        updatePlayerGradeThread.start();
    }

    public void setRank(Rank Rank) {
        this.Rank = Rank;
        Thread updatePlayerRankThread = new Thread(new UpdatePlayerRankThread(this.player));
        updatePlayerRankThread.start();
    }
}
