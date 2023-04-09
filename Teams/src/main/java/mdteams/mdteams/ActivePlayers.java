package mdteams.mdteams;

import org.bukkit.entity.Player;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ActivePlayers
{
    List<Player> ap=new ArrayList<>();
    List<Komanda> koma=new ArrayList<>();

    public void AddTeam(Komanda k)
    {
        koma.add(k);
    }
    public void Joined(Player p)
    {
        for(int i=0;i<koma.size();i++)
        {
            if(koma.get(i).IsInTeam(p))
            {
                ap.add(p);
                try
                {
                    Write();
                } catch (FileNotFoundException e)
                {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
    public void JoinedTeam(Player p)
    {
        if(!ap.contains(p))
        {
            ap.add(p);
            try
            {
                Write();
            } catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    public void Left(Player p)
    {
        if(ap.contains(p))
        {
            ap.remove(p);
            try
            {
                Write();
            } catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
    public void LeftTeam(Player p)
    {
        ap.remove(p);

        try
        {
            Write();
        } catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void Write() throws FileNotFoundException
    {
        PrintWriter out= new PrintWriter("ActivePlayersNames.txt");
        for(int i=0;i< ap.size();i++)
        {
            out.println(ap.get(i).getName());
        }
    }
}
