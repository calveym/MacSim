import java.util.*;

public class Economy {

	// Pointers
	Country country;

	// Economic Variables
	double spirits; // confidence -100 < x < 100
	double dSpirits; // confidence delta

	// Timing
	int quarter;
	int year;

	// Business Cycle
	BusinessCycle cycle;
	
	// Companies
	public ArrayList<Company> companies = new ArrayList<Company>();
	int coId = 0;

	// Constructors
	public Economy (Country newCountry, int numCo, double taxRate, int suppressionLevel) {
		MacSim.log(4, "Creating Economy... ");
		country = country;
		spirits = 0; 							// neutral spirits
	}


	// Update
	public void tick(long tick) {
		updateCycle(tick);
		updateSentiment(tick);

		if(tick % 25 == 0) {
			updateQuarter();
		}

		if(tick % 100 == 0) {
			updateYear();
		}
	}

 	void updateCycle(long tick) {
		if(cycle == null || cycle.complete())
			cycle = new BusinessCycle((MacSim.rand.nextInt(2) + 1) * 150, 2);
		cycle.tick();
	}

	void updateSentiment(long tick) {

	}

	void updateYear() {

	}

	void updateQuarter() {

	}


	// Generation
	void generateCompanies(int num) {
		int each = (int)(country.pop.unemployed / num);
		int remainder = country.pop.unemployed % num;
		MacSim.log(3, "Generating " + num + " companies");

		for(int i = 0; i < num; i++) {
			if(i < remainder) {
				generateCompany(each + remainder);
			} else {
				generateCompany(each);
			}
		}
	}

	void generateCompany(int hire) {
		Company newCo = new Company(this, hire, coId);
		companies.add(newCo);
		coId++;
	}

}
