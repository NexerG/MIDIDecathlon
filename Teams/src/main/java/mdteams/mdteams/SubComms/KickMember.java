package mdteams.mdteams.SubComms;

import mdteams.mdteams.Komanda;
import mdteams.mdteams.MasterTeamClass;
import org.bukkit.entity.Player;

public class KickMember extends SubCommandMaster
{
    @Override
    public String GetName() {
        return "kick";
    }

    @Override
    public String GetDescpription() {
        return "kicks a member from the team";
    }

    @Override
    public String GetSyntax() {
        return "/komanda kick vardas";
    }

    @Override
    public void Perform(Player player, String[] args, MasterTeamClass Komandos) {
        if(player.getName()!=args[2])
            for(int i=0;i< Komandos.GetKomandos().size();i++)
                {
                    if(Komandos.GetKomandos().get(i).TCreator!=args[2]) {
                        Komandos.KickPlayer(args[2],Komandos.GetKomandos().get(i));
                    }
                }
    }
}
