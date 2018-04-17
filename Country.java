import java.util.*;

public class Country {

	Economy economy;
	Government government;
	PopulationManager pop;

	public Country(int newPop, int numCo, double taxRate, int suppressionLevel) {
		MacSim.log(5, "Creating country... ");

		pop = new PopulationManager(this, newPop);
		government = new Government(this, taxRate);
		economy = new Economy(this, numCo, taxRate, suppressionLevel);
	}

	public void tick(long tick) {
		economy.tick(tick);
		government.tick(tick);
	}

	public void quarterlyReport() {
		
	}
}
