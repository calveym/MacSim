public class TaxTicker extends Ticker {

    MacSim sim;
    double dCTax;
    double aCTax;


    public TaxTicker(MacSim s, double duringTaxRate, double afterTaxRate, long tick, long len, long sDelay) {
        super(tick, len, sDelay);

        sim = s;
        dCTax = duringTaxRate;
        aCTax = afterTaxRate;
    }

    public void preAction() {

    }

    public void duringAction() {
        sim.country.government.corporateTax = dCTax;
    }

    public void postAction() {
        sim.country.government.corporateTax = aCTax;
    }
}
