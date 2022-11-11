package sperias.group.Model.Thread;

import org.bukkit.entity.Player;
import sperias.group.Model.GroupModel;

import java.sql.SQLException;

public class UpdatePlayerGradeThread extends GroupModel implements Runnable{

    public UpdatePlayerGradeThread(Player player) {
        super(player);
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
