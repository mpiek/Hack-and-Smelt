import org.powerbot.script.rt6.ClientContext;
import org.powerbot.script.rt6.GameObject;

/**
 * Created by Algathonix on 9/26/2017.
 */
public class Miner extends Task<ClientContext> {
    private int[] tinId = {67011};
    private int[] copperId = {67014};
    private Antiban antiban = new Antiban();

    public Miner(ClientContext ctx){
        super(ctx);
    }


    private int[] WhatToMine(){
        if(ctx.backpack.count() >= 14){
            return copperId;
        }
        return tinId;
    }

    @Override
    public boolean activate() {
        return ctx.backpack.select().count() < 28
                && !ctx.objects.select().id(WhatToMine()).isEmpty()
                && ctx.players.local().animation() == -1
                && Place.MiningArea.contains(ctx.players.local());
    }


    @Override
    public void execute() {
       GameObject Rock = ctx.objects.nearest().within(Place.MiningArea).poll();
            if(Rock.inViewport()){
                antiban.justWait(400, 1000, ctx.players.local().animation());
                if(ctx.players.local().animation() == -1) {
                    Rock.interact("Mine");
                    antiban.justWait(200, 200, ctx.players.local().animation());
                }
            } else {
                ctx.movement.step(Rock);
                ctx.camera.turnTo(Rock);
            }
    }
}
