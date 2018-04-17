import java.util.*;

public class Economy {

	Random rand;


	// Pointers
	Country country;

	// Economic Variables
	double spirits; // confidence -100 < x < 100
	double dSpirits; // confidence delta

	// Timing
	int quarter;
	int year;

	// Business Cycle
	BusinessCycle cycle
	
	// Companies
	public ArrayList<Company> companies = new ArrayList<Company>();


	// Constructors
	public Economy (Country country, int numCo, double taxRate, int suppressionLevel) {
	
		MacSim.log("Creating Economy... ");
		self.country = country;
		spirits = 0; 							// neutral spirits
	}


	// Update
	public void tick(long tick) {
		updateCycle(tick);
		updateSentiment(tick);
	}

 	void updateCycle(long tick) {
		if(cycle == null || cycle.complete())
			cycle = new BusinessCycle((rand.nextint(2) + 1) * 150, 2);
		cycle.tick();
	}

	void updateSentiment(long tick) {

	}


	// Generation
	void generateCompanies(int num) {
		int each = (int)(country.pop.unemployed / num);
	}

}
