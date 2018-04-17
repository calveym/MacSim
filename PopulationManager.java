public class PopulationManager {

	// Maintained vars
	int totalPop;    // total population
	int lf;          // labor force number
	int employed;    // number employed

	// Calculated vars
	int unemployed;  // number unemployed

	public void tick(long tick) {
		recalculateVars();
	}

	public void recalculateVars() {
		unemployed = lf - employed;
	}
}
