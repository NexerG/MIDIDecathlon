package buildoff.buildoff.SubComms;
import buildoff.buildoff.BuildOff;
import org.bukkit.entity.Player;

public abstract class SubCommandMaster
{
    public abstract String GetName();
    public abstract String GetDescpription();
    public abstract String GetSyntax();
    public abstract  void Perform(Player player, String[] args, BuildOff par);
}
