package sperias.group.Command.Controller;

import org.bukkit.entity.Player;
import sperias.group.Controller.Controller;
import sperias.group.GroupManager.GroupManager;

public class RankUpController extends Controller {

    public RankUpController(Player player, GroupManager plugin)
    {
        super(player, plugin);
    }

    public boolean canRankUp()
    {
        return true;
    }
}
