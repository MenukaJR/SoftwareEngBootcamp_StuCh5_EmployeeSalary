public class EmployeeSalary {

    public static int calBasicSal(int perDayPayment, int numberOfDays ){
     return perDayPayment*numberOfDays;
    }

    public static double calAllowances(int basicSalary,double percentage){
    return basicSalary*percentage;
    }

    public static double calEPF(double totalMonthlyIncome, double EPF_Percentage, double employerContributionPercentage){
        double EPF, employerContribution,total;
        
        EPF = totalMonthlyIncome*EPF_Percentage;
        employerContribution = totalMonthlyIncome*employerContributionPercentage;
        total = EPF+employerContribution;
        return total;
    }
    
    public static void main(String[] args) {
        
    }
}