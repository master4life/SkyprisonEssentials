package de.kiyan.skyprison.Listeners;

import de.kiyan.skyprison.Addon.PotionSpawner;
import de.kiyan.skyprison.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class Event_PickupItem implements Listener
{
    @EventHandler
    public void ItemPickUp( PlayerPickupItemEvent e )
    {

        ItemStack iStack = e.getItem( ).getItemStack( );
        Player p = e.getPlayer( );

        if( !( p instanceof Player ) )
            return;

        if( iStack.hasItemMeta( ) )
        {
            ItemMeta iMeta = iStack.getItemMeta( );

            if( iMeta.hasDisplayName( ) && iMeta.getDisplayName( ).contains( "dn[aOKFN-3JF[OIJ34=-F" ) )
            {
                String[] stringArray = iMeta.getDisplayName().replace( " ", "" ).split( ":" );
                List< String > lore = iMeta.getLore( );
                double x = Double.parseDouble( lore.get( 0 ) );
                double y = Double.parseDouble( lore.get( 1 ) );
                double z = Double.parseDouble( lore.get( 2 ) );
                String w = lore.get( 3 );

                Location DropLoc = new Location( Bukkit.getWorld( w ), x, y, z );

                if( stringArray[ 1 ].equalsIgnoreCase( "STRENGTH" ) && stringArray[ 2 ].equalsIgnoreCase( "1" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.INCREASE_DAMAGE, 20 * Integer.parseInt( stringArray[ 3 ] ), 0, true ) );
                }
                if( stringArray[ 1 ].equalsIgnoreCase( "STRENGTH" ) && stringArray[ 2 ].equalsIgnoreCase( "2" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.INCREASE_DAMAGE, 20 * Integer.parseInt( stringArray[ 3 ] ), 2, true ) );
                }
                if( stringArray[ 1 ].equalsIgnoreCase( "REGEN" ) && stringArray[ 2 ].equalsIgnoreCase( "1" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.REGENERATION, 20 * Integer.parseInt( stringArray[ 3 ] ), 0, true ) );
                }
                if( stringArray[ 1 ].equalsIgnoreCase( "REGEN" ) && stringArray[ 2 ].equalsIgnoreCase( "2" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.REGENERATION, 20 * Integer.parseInt( stringArray[ 3 ] ), 1, true ) );
                }
                if( stringArray[ 1 ].equalsIgnoreCase( "SPEED" ) && stringArray[ 2 ].equalsIgnoreCase( "1" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.SPEED, 20 *Integer.parseInt( stringArray[ 3 ] ), 0, true ) );
                }
                if( stringArray[ 1 ].equalsIgnoreCase( "SPEED" ) && stringArray[ 2 ].equalsIgnoreCase( "2" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.SPEED, 20 * Integer.parseInt( stringArray[ 3 ] ), 1, true ) );
                }
                if( stringArray[ 1 ].equalsIgnoreCase( "NIGHT_VISION" ) && stringArray[ 2 ].equalsIgnoreCase( "1" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.NIGHT_VISION, 20 * Integer.parseInt( stringArray[ 3 ] ), 1, true ) );
                }
                if( stringArray[ 1 ].equalsIgnoreCase( "SLOWNESS" ) && stringArray[ 2 ].equalsIgnoreCase( "1" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.SLOW, 20 * Integer.parseInt( stringArray[ 3 ] ), 1, true ) );
                }
                if( stringArray[ 1 ].equalsIgnoreCase( "WEAKNESS" ) && stringArray[ 2 ].equalsIgnoreCase( "1" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.WEAKNESS, 20 * Integer.parseInt( stringArray[ 3 ] ), 1, true ) );
                }
                if( stringArray[ 1 ].equalsIgnoreCase( "JUMP" ) && stringArray[ 2 ].equalsIgnoreCase( "1" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.LEVITATION, 20 * Integer.parseInt( stringArray[ 3 ] ), 1, true ) );
                }
                if( stringArray[ 1 ].equalsIgnoreCase( "JUMP" ) && stringArray[ 2 ].equalsIgnoreCase( "1" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.LEVITATION, 20 * Integer.parseInt( stringArray[ 3 ] ), 2, true ) );
                }
                if( stringArray[ 1 ].equalsIgnoreCase( "INVISIBILITY" ) && stringArray[ 2 ].equalsIgnoreCase( "1" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.INVISIBILITY, 20 * Integer.parseInt( stringArray[ 3 ] ), 1, true ) );
                }
                if( stringArray[ 1 ].equalsIgnoreCase( "FIRE_RESISTANCE" ) && stringArray[ 2 ].equalsIgnoreCase( "1" ) )
                {
                    p.addPotionEffect( new PotionEffect( PotionEffectType.FIRE_RESISTANCE, 20 * Integer.parseInt( stringArray[ 3 ] ), 1, true ) );
                }

                e.getItem( ).remove( );
                e.setCancelled( true );

                Bukkit.getScheduler( ).scheduleSyncDelayedTask( Main.instance, new Runnable( )
                {
                    @Override
                    public void run( )
                    {
                        PotionSpawner.SpawnPotion( DropLoc );
                    }
                }, 30 );
            }

        }
    }

}
