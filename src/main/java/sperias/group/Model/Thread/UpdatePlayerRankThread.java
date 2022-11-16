package sperias.group.Model.Thread;

import SPGroupManager.SPGroupManager;
import org.bukkit.entity.Player;
import sperias.group.Model.GroupModel;

import java.sql.SQLException;

public class UpdatePlayerRankThread extends GroupModel implements Runnable {

    private Player player;

    public UpdatePlayerRankThread(Player player, SPGroupManager plugin) {
        super(plugin);
        this.player = player;
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
