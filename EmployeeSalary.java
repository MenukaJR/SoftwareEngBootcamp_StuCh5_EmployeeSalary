import java.util.Scanner;

 class calculations{

    private int basicSalary;
    private double allowance, EPF;

    //percentage used in functions should be converted to decimals [divide by hundred]
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

    public void calBasicSal(int perDayPayment, int numberOfDays ){
         basicSalary = perDayPayment*numberOfDays;
       }
   
       public double calAllowances(int basicSalary,double percentage){
        return allowance = basicSalary*percentage;
       }
   
       public void calEPF(double totalMonthlyIncome, double EPF_Percentage, double employerContributionPercentage){
           double Emp_EPF, employerContribution,total;
           
           Emp_EPF = totalMonthlyIncome*EPF_Percentage;
           employerContribution = totalMonthlyIncome*employerContributionPercentage;
           total = Emp_EPF+employerContribution;
           EPF = total;
       }
}

public class EmployeeSalary {

    synchronized public static void main(String[] args) {
        int perDayPmnt, days,basicSalary;
        double allowPercent,allowance;
        Scanner readInput = new Scanner(System.in);
        
        
        calculations cal = new calculations();

        System.out.println("Enter Per Day Payment rate:  ");
        perDayPmnt = readInput.nextInt();

        System.out.println("Enter Number of days: ");
        days = readInput.nextInt();

        cal.calBasicSal(perDayPmnt, days);
        basicSalary = cal.getBasicSalary();

        allowPercent = 0.05;

        Thread thread1 = new Thread(new Runnable(){

            @Override
            public void run() {
                double allowance;

                allowance = cal.calAllowances(basicSalary,allowPercent);
                cal.setAllowance(allowance);
                //System.out.println(cal.getAllowance());
            }

        });

        thread1.run();
        System.out.println(cal.getAllowance());
    

    }
}