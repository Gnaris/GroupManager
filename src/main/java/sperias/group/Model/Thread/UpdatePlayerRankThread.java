package sperias.group.Model.Thread;

import org.bukkit.entity.Player;
import sperias.group.Model.M_Group;

import java.sql.SQLException;

public class UpdatePlayerRankThread extends M_Group implements Runnable {

    public UpdatePlayerRankThread(Player player) {
        super(player);
    }

    @Override
    public void run() {
        try {
            this.updateRank(player);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
