package de.kiyan.skyprison.commands;

import de.kiyan.skyprison.Addon.Rewarder;
import de.kiyan.skyprison.Config;
import de.kiyan.skyprison.Main;
import de.kiyan.skyprison.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CMDSponge implements CommandExecutor
{
    public static boolean bAlreadySpawned;
    public int TaskId;

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args )
    {
        if( !( sender instanceof Player ) )
        {
            sender.sendMessage( "§cYou must be a player" );

            return false;
        }

        if( !label.equalsIgnoreCase( "sponge" ) )
        {
            return false;
        }

        Player player = ( Player ) sender;

        if( args.length == 0 )
        {
            player.sendMessage( Config.SpongePrefix + " §eThis is a minigame where you need to find a sponge somewhere on the map in prison." );
            player.sendMessage( "§eEvery §c15 minutes§e a sponge is hidden at a random location on the map, as soon as someone finds it it will be hidden again. If" );
            player.sendMessage( "§enobody finds it another sponge will be added to the hidings. For every sponge you find you get §c" + Config.SpongeReward + ". §e" );
            player.sendMessage( "§eSponges are only hidden in the prison world (this is not tutorial island). ALL sponges are hidden in places that everyone can access," );
            player.sendMessage( "§eyou don't have to go through portals or teleport signs to reach the sponge." );
            player.sendMessage( "§eYou have already found §c" + Rewarder.loadPoints( player ) + " §esponges." );

            if( player.hasPermission( "subzero.spongesearch" ) )
                player.sendMessage( Config.SpongePrefix + " §7There are §a" + ( listSponges( ) == null ? "0" : listSponges( ).size( ) ) + " §7from §a" + Config.MaxSponges + "§7 Sponge location has been set, so far." );

            return true;
        }

        if( player.hasPermission( "subzero.spongesearch" ) )
        {

            if( args[ 0 ].equalsIgnoreCase( "add" ) )
            {
                if( args.length == 1 )
                {
                    player.sendMessage( Config.SpongePrefix + " §ePlease provide only number §61-" + Config.MaxSponges + "§e to being excluded." );

                    return true;
                }

                if( Utils.isInt( args[ 1 ] ) )
                {
                    int i = Integer.parseInt( args[ 1 ] );
                    if( i <= Config.MaxSponges )
                    {
                        writeSpongeLoc( player, i );
                        player.sendMessage( Config.SpongePrefix + " §a" + i + "th§7 sponge point created." );

                        return true;
                    }
                } else
                {
                    player.sendMessage( Config.SpongePrefix + " §cOnly numbers can be used and maximal: §7" + Config.MaxSponges + "§csponges can be set." );

                    return false;
                }
            }
            if( args[ 0 ].equalsIgnoreCase( "start" ) )
            {
                for( Player all : Bukkit.getOnlinePlayers( ) )
                    all.sendMessage( Config.SpongePrefix + " §eSponge has been spawned." );

                Bukkit.getScheduler( ).cancelTask( TaskId );
                spawnSponge( );
            }
            if( args[ 0 ].equalsIgnoreCase( "top10" ) )
            {
                Rewarder.getTop10( player );
            }
        }

        return false;
    }

    public void spawnSponge( )
    {
        if( TaskId > 0 )
            Bukkit.getScheduler( ).cancelTask( TaskId );

        TaskId = Bukkit.getScheduler( ).scheduleSyncRepeatingTask( Main.instance, new Runnable( )
        {
            @Override
            public void run( )
            {
                if( !( listSponges( ) == null ) && !bAlreadySpawned )
                {
                    Location loc = listSponges( ).get( listSponges( ).size( ) == 1 ? 0 : randomWithRange( 0, listSponges( ).size( ) - 1 ) );
                    loc.getBlock( ).setType( Material.SPONGE );
                    CMDSponge.bAlreadySpawned = true;
                }
            }
        }, 20 * 60, ( 20 * 60 ) * Config.delaySponge );
    }

    public static List< Location > listSponges( )
    {
        File file = new File( Main.instance.getDataFolder( ) + "/spongeLocation.yml" );
        if( !file.exists( ) )
        {
            try
            {
                file.createNewFile( );
            } catch( IOException e )
            {
                e.printStackTrace( );
            }
        }
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration( file );
        List< Location > listSponges = new ArrayList<>( );

        if( yaml.getString( "Sponge.1.World" ) == null )
        {
            return null;
        }

        for( int i = 1; i <= Config.MaxSponges; i++ )
        {
            if( yaml.getString( "Sponge." + i + ".World" ) == null )
                break;

            World world = Bukkit.getWorld( yaml.getString( "Sponge." + i + ".World" ) );
            int x = yaml.getInt( "Sponge." + i + ".X" );
            int y = yaml.getInt( "Sponge." + i + ".Y" );
            int z = yaml.getInt( "Sponge." + i + ".Z" );

            listSponges.add( new Location( world, x, y, z ) );
        }
        return listSponges;
    }

    private void writeSpongeLoc( Player player, int iNumber )
    {
        File file = new File( Main.instance.getDataFolder( ) + "/spongeLocation.yml" );
        if( !file.exists( ) )
        {
            try
            {
                file.createNewFile( );
            } catch( IOException e )
            {
                e.printStackTrace( );
            }
        }
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration( file );

        double x = ( int ) player.getLocation( ).getBlock( ).getX( );
        double y = ( int ) player.getLocation( ).getBlock( ).getY( );
        double z = ( int ) player.getLocation( ).getBlock( ).getZ( );
        World world = player.getLocation( ).getWorld( );

        yaml.set( "Sponge." + iNumber + ".X", x );
        yaml.set( "Sponge." + iNumber + ".Y", y );
        yaml.set( "Sponge." + iNumber + ".Z", z );
        yaml.set( "Sponge." + iNumber + ".World", world.getName( ) );

        saveLoc( yaml );
    }

    private void saveLoc( YamlConfiguration yaml )
    {
        try
        {
            yaml.save( Main.instance.getDataFolder( ) + "/spongeLocation.yml" );
        } catch( IOException ex )
        {
            ex.printStackTrace( );
        }
    }

    public int randomWithRange( int min, int max )
    {
        int range = ( max - min );
        return ( int ) ( Math.random( ) * range ) + min;
    }
}
