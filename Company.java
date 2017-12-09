import java.util.*;

public class Company {

    boolean suppress = true;


    Country country; // owner country

    int id; // assigned company id

    // Employee information
    int technology; // 0 - 100 score for innovation
    double techVal; // calculated technology score
    int education; // 0 - 5 score for education
    int employees; // number of employees
    double multiplier; // multiplier calculated from education    1/2 < x < 3
    double cumulativeConfidence = 1;
    int salary;


    // Money
    long assets;
    long capital;
    double debt;
    double debtPayment;
    double interest;
    double maintenance;


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
        interest = 0.01;
        maintenance = 0.1;

        employees = numEmp;
        education = 2;
        technology = 20;
        salary = 15000;
    }

    public void tick(long tick) {
        if(tick%100 == 0) {
            // One year
        } if(tick%25 == 0) {
            // One quarter
            invest();

            if(id == 0)
                quarterlyReport();
            lqRevenue = 0;
            lqExpenses = 0;
            lqProfit = 0;
        }

        revenue = calculateRevenue();
        expenses = calculateExpenses();

        ebitda = revenue - expenses;
        debtPayment = 0.04 * (debt * interest);
        ebitda -= debtPayment;
        capital += ebitda;

        lqRevenue += revenue;
        lqExpenses += expenses;
        lqProfit += ebitda;
    }

    void quarterlyReport() {
        if(suppress) return;
        log(" ");
        log("Quarterly report: " + id);
        log("Assets: " + assets);
        log("Capital: " + capital);
        log("Employees: " + employees);
        log("Profit: " + lqProfit);
        p("   Revenue: " + lqRevenue);
        p("   Expenses: " + lqExpenses);
        log(" ");
    }

    long tax(double amount) {
        long amtTaxable = (long)(amount * ebitda);
        profit = ebitda - amtTaxable;
        return amtTaxable;
    }

    void invest() {

    }

    long calculateRevenue() {
        // multiplier     (education + 1) / 2.0;
        // reach
        reach = updateReach();
        multiplier = updateMultiplier();
        cumulativeConfidence *= (1 + country.confidenceEarningsMultiplier * 0.001);
        return (long)(reach * multiplier * cumulativeConfidence * 0.027 * (country.rand.nextDouble()*1.5) +0.6);
    }

    long calculateExpenses() {
        return (long)(((employees * salary * multiplier) + chargeForAssets()) * 0.01 * (country.rand.nextDouble() * 0.2 + 0.9) * cumulativeConfidence);
    }

    long chargeForAssets() {
        return (long)(assets * maintenance);
    }

    int updateReach() {
        techVal = 1.0 - (technology * 0.002 + 0.01);
        double empTech = (employees) / techVal;
        return (int)(country.population * (int)empTech);
    }

    double updateMultiplier() {
        return (education + 1) / 2.0;
    }

    public static void log(String s) {
        System.out.println(s);
    }

    public static void p(String s) {
        System.out.print(s);
    }
}
