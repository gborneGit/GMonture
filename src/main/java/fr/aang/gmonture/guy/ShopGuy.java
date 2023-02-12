package fr.aang.gmonture.guy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.aang.gmonture.Main;
import fr.aang.gmonture.Monture;
import fr.aang.gmonture.utils.Utils;

public class ShopGuy {

    public static void open(Main main, Player player) {

        Inventory inv = Bukkit.createInventory(null, 9, "§8Elevage");

        for (int i = 0; i < main.getMontures().size(); i++) {

            Monture monture = main.getMontures().get(i);

            List<String> lore = new ArrayList<String>();

            if (player.hasPermission(monture.getPerm())) {

                if (monture.getPlayer(player) != null) {
                    lore.add("§aAcquis");
                }
                else {
                    if (monture.isControl()) {
                        lore.add("§7▪ §fvitesse §e" + monture.getSpeed());
                        if (monture.isFly())
                            lore.add("§7▪ §ffly §eactif");
                        else
                            lore.add("§7▪ §fsault §e" + monture.getJump());
                    }
                    else {
                        lore.add("§7▪ §fvitesse §e1.5");
                        lore.add("§7▪ §fsault §e1.5");
                    }
                    lore.add("§e(●) §aAcheter §6" + monture.getPrice() + "⛃");
                }

            }
            else
                lore.add("§cVérouillé");

            inv.setItem(monture.getSlot(), Utils.getItem(monture.getIcon(), "§6" + monture.getName(), lore));
        }

        inv.setItem(8, Utils.getItem(Material.BARRIER, "§e(●) §cRetour", null));
        player.openInventory(inv);
    }
}
