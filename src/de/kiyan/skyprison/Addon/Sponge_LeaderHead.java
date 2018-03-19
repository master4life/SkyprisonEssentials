package de.kiyan.skyprison.Addon;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import me.robin.leaderheads.api.LeaderHeadsAPI;
import me.robin.leaderheads.datacollectors.OnlineDataCollector;
import me.robin.leaderheads.objects.BoardType;
import org.bukkit.entity.Player;

public class Sponge_LeaderHead extends OnlineDataCollector
{

    public Sponge_LeaderHead( )
    {
        super( "sponge-top", "SkyprisonEssentials", BoardType.DEFAULT, "Sponge Toplist", "sponge all", Arrays.asList( null, null, "&d{amount} sponges", null ) );
    }

    @Override
    public List< Map.Entry< ?, Double > > requestAll( )
    {
        return LeaderHeadsAPI.sortMap( Rewarder.getAllplayers( ) );
    }

    @Override
    public Double getScore( Player player )
    {
        return ( double ) Rewarder.sortHashMap( Rewarder.getAllplayers( ) ).get( 0 );
    }
}