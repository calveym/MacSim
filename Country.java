import java.util.*;

public class Country {

    PopulationManager pop;
    Economy economy;
	Government government;

	public Country(int newPop, int numCo, double taxRate, int suppressionLevel) {
		MacSim.log(5, "Creating country... ");

		pop = new PopulationManager(this, newPop);
		government = new Government(this, taxRate);
		economy = new Economy(this, numCo, taxRate);
	}

	public void tick(long tick) {
		economy.tick(tick);
		government.tick(tick);
	}

	public void quarterlyReport() {
		
	}
}
