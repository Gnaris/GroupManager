package sperias.group.Entity;

import org.bukkit.entity.Player;
import sperias.group.Entity.Group.Rank;
import sperias.group.Entity.Group.Grade;
import sperias.group.GroupManager.GroupManager;

public class PlayerGroup {

    private Player player;
    private char sexe;
    private Grade grade;
    private Rank rank;
    private GroupManager plugin;

    public PlayerGroup(Player player, char sexe, Grade grade, Rank rank, GroupManager plugin) {
        this.player = player;
        this.sexe = sexe;
        this.grade = grade;
        this.rank = rank;
        this.plugin = plugin;
    }

    public void setPermission(boolean value)
    {
        grade.getPermissionList().forEach(p -> player.addAttachment(plugin).setPermission(p , value));
        rank.getPermissionList().forEach(p -> player.addAttachment(plugin).setPermission(p, value));
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        if(!grade.getName().equalsIgnoreCase(this.grade.getName()))
        {
            this.setPermission(false);
            this.grade = grade;
            this.setPermission(true);
        }
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank)
    {
        if(!rank.getName().equalsIgnoreCase(this.rank.getName()))
        {
            this.setPermission(false);
            this.rank = rank;
            this.setPermission(true);
        }
    }
}
