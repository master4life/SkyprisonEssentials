package de.kiyan.skyprison.Addon;

import de.kiyan.skyprison.Config;
import de.kiyan.skyprison.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Rewarder
{
    public static void getTop10( Player p )
    {
        HashMap< String, Double > unsortMap = new HashMap< String, Double >( );
        unsortMap.putAll( getAllplayers( ) );

        int iCounter = 0;
        for( Entry< String, Double > entry : sortHashMap( unsortMap ).entrySet( ) )
        {
            iCounter++;
            if( iCounter <= 10 )
            {
                p.sendMessage( Config.SpongePrefix + " §7Top " + iCounter + " §b   " + entry.getKey( ) + "    §7Points : §a" + entry.getValue( ) );
            }

        }
    }

    public static HashMap< String, Double > getAllplayers( )
    {
        HashMap< String, Double > topPlayers = new HashMap<>( );
        try
        {
            File file = new File( Main.instance.getDataFolder( ) + "/sponge.db" );
            BufferedReader in = new BufferedReader( new FileReader( file ) );
            String line = null;
            while( ( line = in.readLine( ) ) != null )
            {
                if( line.length( ) > 16 )
                {
                    String[] stringArray = line.replace( " ", "" ).split( ":" );

                    topPlayers.put( getNamebyUUID( stringArray[ 0 ] ), Double.parseDouble( stringArray[ 1 ] ) );

                }
            }
            in.close( );
        } catch( FileNotFoundException e )
        {
            e.printStackTrace( );

            return null;
        } catch( IOException e )
        {
            e.printStackTrace( );

            return null;
        }

        return topPlayers;
    }

    private static String getNamebyUUID( String uuid )
    {
        UUID id = UUID.fromString( uuid );
        return Bukkit.getOfflinePlayer( id ).getName( );
    }

    public static HashMap< String, Double > sortHashMap( HashMap< String, Double > unsortMap )
    {
        List< Entry< String, Double > > list = new LinkedList< Entry< String, Double > >( unsortMap.entrySet( ) );

        Collections.sort( list, new Comparator< Entry< String, Double > >( )
        {
            public int compare( Entry< String, Double > o1, Entry< String, Double > o2 )
            {
                return o2.getValue( ).compareTo( o1.getValue( ) );
            }
        } );

        HashMap< String, Double > sortedMap = new LinkedHashMap< String, Double >( );
        for( Entry< String, Double > entry : list )
        {
            sortedMap.put( entry.getKey( ), entry.getValue( ) );
        }

        return sortedMap;
    }

    /*
     * Set the Points on 'points.db'
     *
     * @param: Player p - load the information from <target>.
     *
     * @return: <Integer> iAmount;
     */
    public static Integer loadPoints( Player p )
    {

        File asdf = new File( Main.instance.getDataFolder( ) + "/sponge.db" );
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration( asdf );

        if( !asdf.exists( ) )
        {
            try
            {
                yaml.set( "Player.1a992bbd-7f7b-4658-92f6-c4644e9b0f58", 0 );
                yaml.save( asdf );
            } catch( IOException ex )
            {
                ex.printStackTrace( );
            }
        }

        return ( Integer ) yaml.getInt( "Player." + p.getUniqueId( ) );
    }

    /*
     * Set the Points on 'points.db'
     *
     * @param: Player p - modificated target.
     *
     * @param: int Amount - Amount of Points
     *
     * @param: boolean bRemove - Indicate the remove or adding points
     *
     * @return: none;
     */
    public static void setPoints( Player p )
    {
        File file = new File( Main.instance.getDataFolder( ) + "/sponge.db" );
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration( file );

        if( !file.exists( ) )
        {
            try
            {
                yaml.set( "Player.Kiyan", 0 );
                yaml.save( file );
            } catch( IOException ex )
            {
                ex.printStackTrace( );
            }
        }


        yaml.set( "Player." + p.getUniqueId( ), yaml.getInt( "Player." + p.getUniqueId( ) ) + 1 );
        savePoints( yaml );
    }

    public static void savePoints( YamlConfiguration yaml )
    {
        try
        {
            yaml.save( Main.instance.getDataFolder( ) + "/sponge.db" );
        } catch( IOException ex )
        {
            ex.printStackTrace( );
        }
    }
}
