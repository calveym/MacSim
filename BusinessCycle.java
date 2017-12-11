import java.util.*;

public class BusinessCycle extends Cycle {

    Random rand;

    int tick; // current tick, determines economic multiplier provided
              // by cycle
    int switchToRecession; // int at which economy enters recession
    int length; // trough to trough tick length

    double currentExtremity; // value between 0-1 on progress to next stage of cycle
    double crashExtremity; // number between 12-24
    double growthExtremity; // number between -1 and 8

    boolean inRecession = false;
    double growthFromStart;

    public BusinessCycle(int length, int extremity) {
        rand = new Random();
        this.length = length;
        tick = 0;
        switchToRecession = (int)(0.8 * length);
        // log("Length of cycle: " + length);
        // log("Switch to recession: " + switchToRecession);
        crashExtremity = 12;
        // crashExtremity = rand.nextDouble() * (extremity + 1) * 4;
        growthExtremity = rand.nextDouble() * (extremity + 1) * 1.5;
    }

    public void tick() {
        tick++;
        if(tick == switchToRecession && !inRecession) {
            inRecession = true;
            currentExtremity = 0;
        }

        if(!inRecession) {
            currentExtremity += 1/(double)switchToRecession;
        } else if(inRecession){
            currentExtremity += 1/(double)(length - switchToRecession);
        }
    }

    public boolean complete() {
        return (length == tick);
    }

    public double multiplier() {
        if(!inRecession) {
            // growth multiplier
            // log("Growth multiplier: " + currentExtremity * growthExtremity);
            return currentExtremity * growthExtremity;
        } else {
            // recession multiplier
            // log("Recession multiplier: " + currentExtremity * growthExtremity);
            return (1-currentExtremity) * crashExtremity * -1;
        }
    }

    public void log(String s) {
        System.out.println(s);
    }
}
