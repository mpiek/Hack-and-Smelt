import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.Item;

/**
 * Created by Algathonix on 9/28/2017.
 */
public class Equip extends Task<ClientContext> {

    public Equip(ClientContext ctx){
        super(ctx);
    }
    @Override
    public boolean activate() {
        return !ctx.backpack.select().id(25897).isEmpty()
                && Place.MiningArea.contains(ctx.players.local());
    }

    @Override
    public void execute() {
        System.out.println("i'm here");
        for(Item i : ctx.backpack.id(25897)){
            i.interact("Wield");
        }
    }
}
