package sperias.group.Model.Thread;

import SPGroupManager.SPGroupManager;
import org.bukkit.entity.Player;
import sperias.group.Model.GroupModel;

import java.sql.SQLException;

public class UpdatePlayerRankThread extends GroupModel implements Runnable {

    public UpdatePlayerRankThread(Player player) {
        super(player);
    }

    @Override
    public void run() {
        try {
            this.updateRank(player);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
