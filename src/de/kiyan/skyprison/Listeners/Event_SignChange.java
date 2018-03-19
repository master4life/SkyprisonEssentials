package de.kiyan.skyprison.Listeners;

import de.kiyan.skyprison.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class Event_SignChange implements Listener
{
//    @EventHandler
//    public void onSignChange(SignChangeEvent event)
//    {
//        if (event.getBlock().hasMetadata("armorStand"))
//        {
//            Block b = event.getBlock();
//            ArmorStand as = getArmorStand(b);
//            if (as != null)
//            {
//                String input = "";
//                for (String line : event.getLines()) {
//                    if ((line != null) && (line.length() > 0)) {
//                        input = input + ChatColor.translateAlternateColorCodes('&', line);
//                    }
//                }
//                if (b.hasMetadata("setName"))
//                {
//                    if (input.length() > 0)
//                    {
//                        as.setCustomName(input);
//                        as.setCustomNameVisible(true);
//                    }
//                    else
//                    {
//                        as.setCustomName("");
//                        as.setCustomNameVisible(false);
//                        as.setCustomNameVisible(false);
//                    }
//                }
//                else if (b.hasMetadata("setSkull")) {
//                    if (this.MC_USERNAME_PATTERN.matcher(input).matches())
//                    {
//                        b.setMetadata("protected", new FixedMetadataValue(this.plugin, Boolean.valueOf(true)));
//                        event.getPlayer().sendMessage(ChatColor.GOLD + Config.pleaseWait);
//                        String cmd = "minecraft:give " + event.getPlayer().getName() + " minecraft:skull 1 3 {SkullOwner:\"" + input + "\"}";
//                        String current = b.getWorld().getGameRuleValue("sendCommandFeedback");
//                        b.getWorld().setGameRuleValue("sendCommandFeedback", "false");
//                        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd);
//                        b.getWorld().setGameRuleValue("sendCommandFeedback", current);
//                        boolean found = false;
//                        for (Iterator i$ = event.getPlayer().getInventory().all(Material.SKULL_ITEM).keySet().iterator(); i$.hasNext();)
//                        {
//                            int slot = ((Integer)i$.next()).intValue();
//                            ItemStack skull = event.getPlayer().getInventory().getItem(slot);
//                            SkullMeta sm = (SkullMeta)skull.getItemMeta();
//                            if ((sm.hasOwner()) && (input.equalsIgnoreCase(sm.getOwner())))
//                            {
//                                as.setHelmet(skull);
//                                event.getPlayer().sendMessage(ChatColor.GREEN + Config.appliedHead + ChatColor.GOLD + " " + input);
//                                event.getPlayer().getInventory().setItem(slot, null);
//                                found = true;
//                                break;
//                            }
//                        }
//                        if (!found) {
//                            event.getPlayer().sendMessage(ChatColor.GOLD + Config.headFailed);
//                        }
//                    }
//                    else
//                    {
//                        event.getPlayer().sendMessage(ChatColor.RED + input + " " + Config.invalidName);
//                    }
//                }
//            }
//            event.setCancelled(true);
//            b.removeMetadata("armorStand", this.plugin);
//            b.removeMetadata("setName", this.plugin);
//            b.removeMetadata("setSkull", this.plugin);
//            b.setType(Material.AIR);
//
//            b.setData((byte)0);
//        }
//    }


    @EventHandler
    public void SignEvent( SignChangeEvent event )
    {
        Player player = event.getPlayer( );
        for( int j = 0; j < 4; j++ )
        {
            for( int i = 0; i < Config.filterWords.size( ); i++ )
            {
                if( event.getLine( j ).toLowerCase( ).contains( Config.filterWords.get( i ).toString( ) ) )
                {
                    event.setLine( 0, Config.SignFirstLine );
                    event.setLine( 1, Config.SignSecondLine );
                    event.setLine( 2, "" );
                    event.setLine( 3, "" );
                    player.sendMessage( Config.SignPrefix + " " + Config.WarnMessage );
                    break;
                }
            }
        }
    }
}
