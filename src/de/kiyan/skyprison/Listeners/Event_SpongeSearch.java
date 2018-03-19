package de.kiyan.skyprison.Listeners;

import de.kiyan.skyprison.Addon.Rewarder;
import de.kiyan.skyprison.Config;
import de.kiyan.skyprison.Main;
import de.kiyan.skyprison.commands.CMDSponge;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.MaterialData;

public class Event_SpongeSearch implements Listener
{
    @EventHandler
    public void ClickOnSponge( PlayerInteractEvent event )
    {
        Player player = event.getPlayer( );
        Action act = event.getAction( );

        if( !( act == Action.LEFT_CLICK_BLOCK || act == Action.RIGHT_CLICK_BLOCK )  )
        {
            return;
        }

        if( !CMDSponge.bAlreadySpawned )
        {
            return;
        }

        Block block = event.getClickedBlock( );
        if( event.getClickedBlock( ).getType( ) == Material.SPONGE && CMDSponge.listSponges( ).contains( block.getLocation( ) )  )
        {
            for( Player all : Bukkit.getOnlinePlayers( ) )
                all.sendMessage( Config.SpongePrefix + " §c" + player.getName( ) + " §ejust found the sponge and is rewarded with §c" + Config.SpongeReward + " tokens!§e a new one will be hidden in " + Config.delaySponge + " minutes somewhere in prison" );

            event.getClickedBlock( ).setType( Material.AIR );
            block.getWorld( ).spawnParticle( Particle.BLOCK_CRACK, block.getLocation( ).add( 0D, 0.5D, 0D ), 100, new MaterialData( Material.SPONGE ) );
            Rewarder.setPoints( player );
            player.sendMessage(  Config.SpongePrefix + " §eYou have found §c" + Rewarder.loadPoints( player ) + "§c sponges." );
            Bukkit.dispatchCommand( Bukkit.getConsoleSender(), "runalias /tokenadmin give " + player.getName( ) + " " + Config.SpongeReward );

            CMDSponge.bAlreadySpawned = false;
        }
    }
}
