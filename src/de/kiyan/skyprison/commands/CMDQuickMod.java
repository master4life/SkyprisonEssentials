package de.kiyan.skyprison.commands;

import de.kiyan.skyprison.Config;
import de.kiyan.skyprison.Utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CMDQuickMod implements CommandExecutor
{
    public Inventory modInventory;

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args )
    {
        if( !( sender instanceof Player ) )
        {
            sender.sendMessage( "§cYou must be a player" );

            return false;
        }

        if( !label.equalsIgnoreCase( "quickmod" ) )
        {
            return false;
        }

        Player player = ( Player ) sender;

        if( !player.hasPermission( "subzero.quickmod" ) )
        {
            player.sendMessage( Config.QuickmodPrefix + " §cYou dont have enough permissions!" );

            return false;
        }

        if( args.length == 0 )
        {
            player.sendMessage( Config.QuickmodPrefix + " §ePlease provide a name §6/quickmod <username>§e." );

            return true;
        }

        if( args.length == 1 )
        {
            Player target = Bukkit.getServer( ).getPlayer( args[ 0 ] );

            if( target != null )
            {
                modInventory = Bukkit.createInventory( player, 9, Config.QuickmodDisplay );

                ItemStack i1 = new ItemBuilder( Material.SKULL_ITEM, 1, ( short ) 3 ).setSkullOwner( target.getName() ).setName( "§e§l" + target.getName( ) ).setLore( ).toItemStack( );
                ItemStack i2 = new ItemBuilder( Material.LEATHER_BOOTS ).setName( "§cKick" ).setLore( "§7§m-----------------", "§eUsers gets kicked for §c5§e minutes", "", "§e§o<REQUIRES A REASON>", "", "§bAffected user is:", "§9§l" + target.getName( ) ).toItemStack( );
                ItemStack i3 = new ItemBuilder( Material.SIGN ).setName( "§dWarn" ).setLore( "§7§m-----------------", "", "§e§o<REQUIRES A REASON>", "", "§bAffected user is:", "§9§l" + target.getName( ) ).toItemStack( );
                ItemStack i4 = new ItemBuilder( Material.LAPIS_BLOCK ).setName( "§9Jail this user" ).setLore( "§7§m-----------------", "§bThis person will get jaile", "§bfor §e5§b minutes", "", "§e§o<REQUIRES A REASON>", "", "§bAffected user is:", "§9§l" + target.getName( ) ).toItemStack( );
                ItemStack i5 = new ItemBuilder( Material.BEACON ).setName( "§7Mute this user" ).setLore( "§7§m-----------------", "§eUsers gets muted for §c5§e minutes", "", "§e§o<REQUIRES A REASON>", "", "§bAffected user is:", "§9§l" + target.getName( ) ).toItemStack( );
                ItemStack i6 = new ItemBuilder( Material.BARRIER ).setName( "§6Ad Mute" ).setLore( "§7§m-----------------", "§eUsers gets muted for §c5§e minutes", "", "§bAffected user is:", "§9§l" + target.getName( ) ).toItemStack( );
                ItemStack i7 = new ItemBuilder( Material.REDSTONE ).setName( "§4Temp-Ban §7[§e24-Hours§7]" ).setLore( "§7§m-----------------", "§4User will get banned for §e24 Hours§4.", "", "§e§o<REQUIRES A REASON>", "", "§bAffected user is:", "§9§l" + target.getName( ) ).toItemStack( );

                modInventory.setItem( 0, i1 );
                modInventory.setItem( 2, i2 );
                modInventory.setItem( 3, i3 );
                modInventory.setItem( 4, i4 );
                modInventory.setItem( 5, i5 );
                modInventory.setItem( 6, i6 );
                modInventory.setItem( 7, i7 );

                player.openInventory( modInventory );

                return true;
            } else
            {
                player.sendMessage( Config.QuickmodPrefix + " §cName was invalid, please retype it." );

                return false;
            }
        }
        return false;
    }

}
