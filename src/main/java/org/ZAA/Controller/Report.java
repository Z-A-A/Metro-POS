package org.ZAA.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public class Report
{
    private double sales;
    private double remainingStock;
    private double profit;

    public Report()
    {

    }

    public Report(double sales, double remainingStock, double profit)
    {
        this.sales = sales;
        this.remainingStock = remainingStock;
        this.profit = profit;
    }

    public double getSales()
    {
        return sales;
    }

    public void setSales(double sales)
    {
        this.sales = sales;
    }

    public double getRemainingStock()
    {
        return remainingStock;
    }

    public void setRemainingStock(double remainingStock)
    {
        this.remainingStock = remainingStock;
    }

    public double getProfit()
    {
        return profit;
    }

    public void setProfit(double profit)
    {
        this.profit = profit;
    }


    @GetMapping("/report/today")
    public Report getTodayReport(@RequestParam String branchCode)
    {
        // Implement logic to fetch today's report for the given branch code
        return null;
    }

    @GetMapping("/report/weekly")
    public Report getWeeklyReport(@RequestParam String branchCode)
    {
        // Implement logic to fetch weekly report for the given branch code
        return null;
    }

    @GetMapping("/report/monthly")
    public Report getMonthlyReport(@RequestParam String branchCode)
    {
        // Implement logic to fetch monthly report for the given branch code
        return null;
    }

    @GetMapping("/report/yearly")
    public Report getYearlyReport(@RequestParam String branchCode)
    {

        // Implement logic to fetch yearly report for the given branch code
        return null;
    }

    @GetMapping("/report/range")
    public Report getRangeReport(@RequestParam String branchCode, @RequestParam String startDate, @RequestParam String endDate)
    {
        // Implement logic to fetch report for the specified date range for the given branch code
        return null;
    }


}