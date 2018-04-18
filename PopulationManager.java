public class PopulationManager {

	Country country;

	// Maintained vars
	int totalPop;    // total population
	int lf;          // labor force number
	int employed;    // number employed

	// Calculated vars
	int unemployed;  // number unemployed


	// Constructors
	public PopulationManager(Country newCountry, int newPop) {
		country = newCountry;
		totalPop = newPop;
		lf = totalPop;
		unemployed = totalPop;
	}

	public void tick(long tick) {
		recalculateVars();
	}

	public void recalculateVars() {
		unemployed = lf - employed;
	}

	public void fire(int numToFire) {
	    employed -= numToFire;
    }

	public void hire(int numToHire) {
		employed += numToHire;
	}
}
