import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

/**
 * Created by Algathonix on 9/28/2017.
 */
public class Smither extends Task<ClientContext> {

    private Antiban antiban = new Antiban();
    private TileLocations tl = new TileLocations();
    private int[] RockId = {67011, 67014};
    private long last = 0;

    private void updateIfIdle() {
        if (ctx.players.local().animation() != -1) {
            last = System.currentTimeMillis();

        }
    }

    public Smither(ClientContext ctx){super(ctx);}
    @Override
    public boolean activate() {
        return Place.SmithingArea.contains(ctx.players.local());
    }

    @Override
    public void execute() {
        GameObject Furnace = ctx.objects.select().within(Place.SmithingArea).id(67467).poll();
        ctx.backpack.select();

            updateIfIdle();
            if (System.currentTimeMillis() - last < 5000) {
                return;
            }
            if (!ctx.backpack.select().id(438).isEmpty() && !ctx.backpack.select().id(436).isEmpty()) {
                if(!Furnace.inViewport() ){
                      ctx.camera.turnTo(Furnace);
                }
                if (!ctx.widgets.component(1371, 0).visible()) {
                    Furnace.interact("Smelt");
                    antiban.justWait(200, 250, ctx.players.local().animation());
                    return;
                }
                ctx.input.send("{VK_SPACE}");
            } else if (!ctx.backpack.select().id(2349).isEmpty()) {
                GameObject Anvil = ctx.objects.select().within(Place.SmithingArea).id(67468).poll();
                if (!ctx.widgets.component(1371, 0).visible()) {
                    Anvil.interact("Smith");
                    antiban.justWait(200, 250, ctx.players.local().animation());
                    return;
                }
                ctx.widgets.component(1371, 44).component(84).
                ctx.widgets.component(1371, 44).component(84).click();
                ctx.input.send("{VK_SPACE}");
            } else {
                if (!ctx.players.local().inMotion()) {
                    GameObject exitCave = ctx.objects.select().id(66876).poll();
                    System.out.println("inviewport");
                    if (exitCave.inViewport()) {
                        exitCave.interact("Enter");
                    } else {
                        ctx.camera.turnTo(exitCave);
                    }
                }
            }
        }
    }


