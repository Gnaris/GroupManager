package sperias.group.Model.Thread;

import SPGroupManager.SPGroupManager;
import org.bukkit.entity.Player;
import sperias.group.Model.GroupModel;

import java.sql.SQLException;

public class UpdatePlayerGradeThread extends GroupModel implements Runnable{

    private Player player;

    public UpdatePlayerGradeThread(Player player, SPGroupManager plugin) {
        super(plugin);
        this.player = player;
    }

    @Override
    public void run() {
        try {
            this.updateGrade(player);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
