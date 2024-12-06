package org.ZAA.Controller;

import org.ZAA.backend.Controller.ReportController;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class Report
{
    private double sales;
    private int remainingStock;
    private double profit;
    private String branchCode;

    public Report()
    {

    }

    public Report(double sales, int remainingStock, double profit)
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

    public int getRemainingStock()
    {
        return remainingStock;
    }

    public void setRemainingStock(int remainingStock)
    {
        this.remainingStock = remainingStock;
    }

    public double getProfit()
    {
        return profit;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setProfit(double profit)
    {
        this.profit = profit;
    }

    public void setBranchCode(String branchCode)
    {
        this.branchCode = branchCode;
    }


    @GetMapping("/report/overallBranches")
    public Report getOverallReport()
    {
        // Implement logic to fetch overall's report for the all branches
        // YAHAN PR EUK REPORT KA OBJECT BANAO AUR USKO RETURN KARO
        return null;
    }

    @GetMapping("/report/today")
    public Report getTodayReport(@RequestParam String branchCode)
    {
        // Implement logic to fetch today's report for the given branch code
        ReportController reportController = new ReportController();
        return reportController.getDailyReport(branchCode);
        // YAHAN PR EUK REPORT KA OBJECT BANAO AUR USKO RETURN KARO
    }

    @GetMapping("/report/weekly")
    public Report getWeeklyReport(@RequestParam String branchCode)
    {
        // Implement logic to fetch weekly report for the given branch code
        ReportController reportController = new ReportController();
        return reportController.getWeeklyReport(branchCode);
        // YAHAN PR EUK REPORT KA OBJECT BANAO AUR USKO RETURN KARO
    }

    @GetMapping("/report/monthly")
    public Report getMonthlyReport(@RequestParam String branchCode)
    {
        // Implement logic to fetch monthly report for the given branch code
        ReportController reportController = new ReportController();
        return reportController.getMonthlyReport(branchCode);
        // YAHAN PR EUK REPORT KA OBJECT BANAO AUR USKO RETURN KARO
    }

    @GetMapping("/report/yearly")
    public Report getYearlyReport(@RequestParam String branchCode)
    {

        // Implement logic to fetch yearly report for the given branch code
        ReportController reportController = new ReportController();
        return reportController.getYearlyReport(branchCode);
        // YAHAN PR EUK REPORT KA OBJECT BANAO AUR USKO RETURN KARO
    }

    @GetMapping("/report/range")
    public Report getRangeReport(@RequestParam String branchCode, @RequestParam String startDate, @RequestParam String endDate)
    {
        // Implement logic to fetch report for the specified date range for the given branch code
        ReportController reportController = new ReportController();
        return reportController.getRangeReport(branchCode, startDate, endDate);
        // YAHAN PR EUK REPORT KA OBJECT BANAO AUR USKO RETURN KARO
    }

}