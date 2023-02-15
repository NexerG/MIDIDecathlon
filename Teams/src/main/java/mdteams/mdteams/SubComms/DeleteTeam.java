package mdteams.mdteams.SubComms;

import mdteams.mdteams.Komanda;
import mdteams.mdteams.MasterTeamClass;
import org.bukkit.entity.Player;

public class DeleteTeam extends SubCommandMaster {
    @Override
    public String GetName() {
        return "delete";
    }

    @Override
    public String GetDescpription() {
        return "deletes your team";
    }

    @Override
    public String GetSyntax() {
        return "/komanda delete";
    }

    @Override
    public void Perform(Player player, String[] args, MasterTeamClass Komanda) {
        for(int i=0;i<Komanda.GetKomandos().size();i++)
        {
            if(Komanda.GetKomandos().get(i).TCreator==player.getName())
            {
                Komanda.DeleteTeam(Komanda.GetKomandos().get(i));
            }
        }
    }
}
