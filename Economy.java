import java.util.*;

public class Economy {

	// Pointers
	Country country;
	GoodsMarket market;

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
	public Economy (Country newCountry, int numCo, double taxRate) {
		MacSim.log(4, "Creating Economy... ");
		country = newCountry;
		spirits = 0; 							// neutral spirits
		market = new GoodsMarket();
		generateCompanies(numCo);
	}


	// Update
	public void tick(long tick) {
		updateCycle(tick);
		updateSentiment(tick);

		for(Company company : companies) {
		    company.tick(tick);
        }
        market.tick();
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
	    MacSim.log(5, "COuntry pop: " + country);
		int each = (int)(country.pop.unemployed / num);
		int remainder = country.pop.unemployed % num;
		MacSim.log(3, "Generating " + num + " companies");

//		for(int i = 0; i < num - 1; i++) {
////			if(i < remainder) {
////				generateCompany(each + remainder, -1, 0, 1);
////			} else {
////				generateCompany(each, -1, 0, 1);
////			}
////		}
        generateCompany(10, -1, 0, 1);

        generateCompany(10, 0, -1, 0.1);
	}

	void generateCompany(int hire, int inId, int outId, double inResDem) {
		Company newCo = new Company(this, coId, inId, outId, inResDem, hire, 10);
		companies.add(newCo);
		coId++;
	}

}
