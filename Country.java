import java.util.*;

public class Country {
	
	Economy economy;
	Government government;
	PopulationManager pop;

	public Country(int newPop, int numCo, double taxRate, int suppressionLevel) {
		MacSim.log(5, "Creating country... ");

		pop = new PopulationManager(newPop);
		government = new Government();
		economy = new Economy(numCo, taxRate, suppressionLevel);
	}

	public void tick(long tick) {
		economy.tick(tick);
		government.tick(tick);
	}

}
