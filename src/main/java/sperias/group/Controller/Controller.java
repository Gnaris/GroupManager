package sperias.group.Controller;

import sperias.group.GroupManager.GroupManager;
import org.bukkit.entity.Player;
import sperias.group.Model.GroupModel;

public class Controller{

    protected GroupManager plugin;
    protected Player player;

    protected GroupModel groupModel;

    public Controller(Player player, GroupManager plugin) {
        this.plugin = plugin;
        this.player = player;
        this.groupModel = new GroupModel(plugin);
    }

    protected boolean targetExist(Player target)
    {
        if(target == null)
        {
            player.sendMessage("§cCe joueur n'existe pas");
            return false;
        }
        return true;
    }

    public boolean hasPermission(String permission)
    {
        if(!player.hasPermission(permission) && !player.isOp())
        {
            player.sendMessage("§cVous n'avez pas la permission");
            return false;
        }
        return true;
    }
}
