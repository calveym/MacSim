import java.util.*;

public class Company {

    Country country; // owner country

    int id; // assigned company id

    // Employee information
    int technology; // 0 - 100 score for innovation
    double techVal; // calculated technology score
    int education; // 0 - 5 score for education
    int employees; // number of employees
    double multiplier; // multiplier calculated from education    1/2 < x < 3
    double cumulativeConfidence = 1;
    int wage;


    // Money
    long value; // total value of company
    long assets; // illiquid assets of company, earns interest
    long capital; // liquid assets of company
    long debt; // liabilities
    double debtPayment; // interest on liabilities
    double interest; // interest on capital
    double maintenance; // cost of assets


    // Money - flow
    int reach; // people this business reached in last tick
    long revenue;
    long expenses;
    long ebitda;
    long profit;

    long lqRevenue;
    long lqExpenses;
    long lqProfit;

    // Equity
    int stocksOutstanding;
    double stockPrice;

    public Company(Country newCountry, int numEmp, int newId) {
        id = newId;
        country = newCountry;
        stocksOutstanding = 100;
        stockPrice = 1;

        assets = 10000;
        capital = 25000;
        debt = 0;
        interest = 0.02;
        maintenance = 0.1;

        employees = numEmp;
        education = 2;
        technology = 20;
        wage = 15000;
    }

    public void tick(long tick) {
        if(tick%100 == 0) {
            // One year
        } if(tick%25 == 0) {
            // One quarter
            // investCapital();

            if(id == 0 && MacSim.SUPPRESS == 1)
                quarterlyReport();
            lqRevenue = 0;
            lqExpenses = 0;
            lqProfit = 0;
        }

        revenue = calculateRevenue();
        expenses = calculateExpenses();
        // revenue += assetRevenue();

        ebitda = revenue - expenses;
        debtPayment = 0.04 * (debt * interest);
        ebitda -= debtPayment;
        capital += ebitda;

        lqRevenue += revenue;
        lqExpenses += expenses;
        lqProfit += profit;
        value = capital + assets - debt;
    }

    void quarterlyReport() {
        MacSim.log(2, " ");
        MacSim.log(2, "Quarterly report: " + id);
        MacSim.log(2, "Assets: " + assets);
        MacSim.log(2, "Capital: " + capital);
        MacSim.log(2, "Employees: " + employees);
        MacSim.log(2, "Profit: " + lqProfit);
        MacSim.p(2, "   Revenue: " + lqRevenue);
        MacSim.p(2, "   Expenses: " + lqExpenses);
        MacSim.log(2, " ");
    }

    long tax(double amount) {
        long amtTaxable = (long)((amount * 0.01) * ebitda);
        if(amtTaxable < 0) return 0;
        profit = ebitda - amtTaxable;
        // log("Amt taxable: " + amtTaxable);
        capital -= amtTaxable;
        return amtTaxable;
    }

    // inject capital in company
    void invest(long investAmt) {
        capital += investAmt;
    }

    // improves company by spending capital
    void investCapital() {
        if(capital > 10000000)
            majorInvestment();
        if(capital > 250000)
            minorInvestment();
    }

    void majorInvestment() {
        if(!country.availableUnemployed(20)) {
            hire(20);
            convertCapital(5000000);
        } else {
            convertCapital(10000000);
        }
    }

    void minorInvestment() {
        if(country.availableUnemployed(5)) {
            hire(5);
            convertCapital(175000);
        } else {
            convertCapital(250000);
        }
    }

    void convertCapital(long amount) {
        assets += amount;
        capital -= amount;
    }

    long calculateRevenue() {
        // multiplier     (education + 1) / 2.0;
        // reach
        reach = updateReach();
        multiplier = updateMultiplier();
        cumulativeConfidence *= (1 + country.confidenceEarningsMultiplier * 0.001);
        return (long)(reach * multiplier * cumulativeConfidence * 0.027);
    }

    long calculateExpenses() {
        return (long)(((employees * wage * multiplier) + chargeForAssets()) * 0.01 * (country.rand.nextDouble() * 0.2 + 0.9) * cumulativeConfidence);
    }

    long chargeForAssets() {
        return (long)(assets * maintenance);
    }

    long assetRevenue() {
        long assetRevenue = (long)(assets * interest);
        if(id==0)
            MacSim.log(1, "Asset revenue: " + assetRevenue);
        return assetRevenue;
    }

    void hire(int numToHire) {
        country.pop.hire(numToHire);
		employees += numToHire;
    }

    int updateReach() {
        techVal = 1.0 - (technology * 0.002 + 0.01);
        double empTech = (employees) / techVal;
        return (int)(country.population * (int)empTech);
    }

    double updateMultiplier() {
        return (education + 1) / 2.0;
    }
}
