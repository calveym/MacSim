import java.util.*;

public class Company {

    Country country; // owner country

    int id; // assigned company id

    // Employee information
    int technology; // 0 - 100 score for innovation
    int education; // 0 - 5 score for education
    int employees; // number of employees
    int multiplier; // multiplier calculated from education and technology
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
            if(id == 0)
                quarterlyReport();
            lqRevenue = 0;
            lqExpenses = 0;
            lqProfit = 0;
        }

        updateMultiplier();
        updateReach();

        revenue = calculateRevenue();
        expenses = calculateExpenses();

        profit = revenue - expenses;
        debtPayment = 0.04 * (debt * interest);
        profit -= debtPayment;
        capital += profit;

        lqRevenue += revenue;
        lqExpenses += expenses;
        lqProfit += profit;
    }

    void quarterlyReport() {
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

    long calculateRevenue() {;
        return (long)((multiplier * reach * 0.005) * country.confidenceEarningsMultiplier);
    }

    long calculateExpenses() {
        return (long)(((employees * salary * multiplier) + chargeForAssets()) * 0.01);
    }

    long chargeForAssets() {
        return (long)(assets * maintenance);
    }

    void updateReach() {
        double inTech = 100 / (technology);
        reach = country.population * (int)((inTech) * employees);
    }

    void updateMultiplier() {
        multiplier = (education + 1) * (technology / 20);
    }

    public static void log(String s) {
        System.out.println(s);
    }

    public static void p(String s) {
        System.out.print(s);
    }
}
