import java.util.*;

public class Company {

    Country country; // owner country

    int id; // assigned company id

    // Employee information
    int technology; // 0 - 100 score for innovation
    int education; // 0 - 5 score for education
    int employees; // number of employees
    int servable; // servable population, aka max capacity

    // Money
    double assets;
    double capital;
    double debt;
    double debtPayment;
    double interest;


    // Money - flow
    double revenue;
    double expenses;
    double profit;

    double lqRevenue;
    double lqExpenses;
    double lqProfit;

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

        employees = numEmp;
        education = 2;
        technology = 20;
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

        updateServable();

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
        log("Capital: " + capital);
        log("Employees: " + employees);
        log("Profit: " + lqProfit);
        p("   Revenue: " + lqRevenue);
        p("   Expenses: " + lqExpenses);
        log(" ");
    }

    double calculateRevenue() {
        int productivity = employees * (education+1);
        return country.population * productivity;
    }

    double calculateExpenses() {
        return (employees * (education+1) * (servable));
    }

    void updateServable() {
        servable = (education + 1) * technology;
    }

    public static void log(String s) {
        System.out.println(s);
    }

    public static void p(String s) {
        System.out.print(s);
    }
}
