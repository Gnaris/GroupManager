package sperias.group.Entity.GUI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import sperias.group.GroupManager.GroupManager;

public class RankGUI {

    public static Inventory getInventory(Player player, GroupManager plugin)
    {
        Inventory rankGUI = Bukkit.createInventory(null, 54, "Les classes");
        plugin.getRanks().values().forEach(r -> rankGUI.setItem(r.getId() ,new ItemStack(r.getMaterial(), r.getId())));
        return rankGUI;
    }
}
