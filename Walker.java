import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

/**
 * Created by Algathonix on 9/27/2017.
 */
public class Walker extends Task<ClientContext> {

    private TileLocations tl = new TileLocations();

    public Walker(ClientContext ctx){
        super(ctx);
    }

    @Override
    public boolean activate() {
        return  !ctx.players.local().inMotion()
        && !ctx.objects.select().id(67002).isEmpty();

    }

    @Override
    public void execute() {
        int inventory = ctx.backpack.count();
        if (!Place.MiningArea.contains(ctx.players.local()) && inventory < 28) {
            ctx.movement.step((tl.MiningLocation));
            return;
        }
        if (inventory >= 28) {
            GameObject exitCave = ctx.objects.nearest().poll();
            if (exitCave.inViewport()) {
                exitCave.interact("Exit");
            } else {
                ctx.movement.step(tl.CaveExit);
                ctx.camera.turnTo(exitCave);
            }
        }
    }
}
