import org.powerbot.script.Condition;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by Algathonix on 9/27/2017.
 */
public class Antiban {
    Random random = new Random();

    public int WaitBetweenMining(int min, int max) {
        return random.nextInt(max + 1 - min) + min;
    }

    /*
Serves two purposes
1. "Realistic" waiting periods between mining an ore - act as if half-afk
2. When doing a 'full-loop-mining-animation' it will prevent it from clicking it twice (unless you have an unlucky
random number; couldn't test this out properly yet.
 */
    public void justWait(int MinAmount, int MaxAmount, int animation) {
        int toWait = WaitBetweenMining(MinAmount, MaxAmount);
        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return animation != -1;
            }
        }, toWait, 5);
    }
}
