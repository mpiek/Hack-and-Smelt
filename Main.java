import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt6.ClientContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//the rt6 package is for RS3. For OSRS scripts, you would use the rt4 package.

@Script.Manifest(
        name = "Miner",
        description = "Miner tin Ores",
        properties = "client=6; topic=Mining; Author=Algathonix"

)
public class Main extends PollingScript<ClientContext> {

    private List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start(){
        taskList.addAll(Arrays.asList(new Equip(ctx), new Miner(ctx), new Walker(ctx), new Smither(ctx)));
    }
@Override
    public void poll() {
        for(Task task : taskList){
            if (task.activate()){
                task.execute();
            }
        }
    }
}