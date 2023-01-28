import java.util.Scanner;

class calculations {

    private int basicSalary;
    private double allowance, EPF;

    // percentage used in functions should be converted to decimals [divide by
    // hundred]
    // Eg: 5% -> 0.05
    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getEPF() {
        return EPF;
    }

    public void setEPF(double ePF) {
        EPF = ePF;
    }

    public void calBasicSal(int perDayPayment, int numberOfDays) {
        basicSalary = perDayPayment * numberOfDays;
    }

    public void calAllowances(int basicSalary, double percentage) {
        allowance = basicSalary * percentage;
    }

    public void calEPF(double totalMonthlyIncome, double EPF_Percentage, double employerContributionPercentage) {
        double Emp_EPF, employerContribution, total;

        Emp_EPF = totalMonthlyIncome * EPF_Percentage;
        employerContribution = totalMonthlyIncome * employerContributionPercentage;
        total = Emp_EPF + employerContribution;
        EPF = total;
    }
}

public class EmployeeSalary {

    synchronized public static void main(String[] args) throws Exception {
        int perDayPmnt, days, basicSalary;
        double allowPercent, EPF_Percent, employer_Contr_Percent, salaryWithAllowance, grossSalary;
        
        Scanner readInput = new Scanner(System.in);
        calculations cal = new calculations();

        allowPercent = 0.05;
        EPF_Percent = 0.08;
        employer_Contr_Percent = 0.12;


        System.out.println("Enter Per Day Payment rate:  ");
        perDayPmnt = readInput.nextInt();

        System.out.println("Enter Number of days: ");
        days = readInput.nextInt();

        cal.calBasicSal(perDayPmnt, days);
        basicSalary = cal.getBasicSalary();
        
        //Thread1 to calculate Employee Allowance
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                double allowance;
                cal.calAllowances(basicSalary, allowPercent);
            }

        });

        salaryWithAllowance = (double) cal.getAllowance() + basicSalary;
        
        //Thread2 to calculate Employee EPF with employer's contribution
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                cal.calEPF(salaryWithAllowance, EPF_Percent, employer_Contr_Percent);
            }

        });

        thread1.run();
        thread2.run();

       grossSalary = salaryWithAllowance+cal.getEPF();
       System.out.println("         [RESULTS]\n");
        System.out.println("      Allowance: " + cal.getAllowance());
        System.out.println("            EPF: " + cal.getEPF());
        System.out.println("   Gross Salary: "+grossSalary);

    }
}