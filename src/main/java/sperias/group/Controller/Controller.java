package sperias.group.Controller;

import SPGroupManager.SPGroupManager;
import org.bukkit.entity.Player;

public class Controller{

    protected SPGroupManager plugin;
    protected Player player;

    public Controller(Player player, SPGroupManager plugin) {
        this.plugin = plugin;
        this.player = player;
    }

    public boolean havePermission(String permission)
    {
        if(!player.hasPermission(permission) && !player.isOp())
        {
            player.sendMessage("§cVous n'avez pas la permission");
            return false;
        }
        return true;
    }
}
