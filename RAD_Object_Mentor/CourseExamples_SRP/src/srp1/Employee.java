package srp1;

public class Employee {
	private Bank bank;
	private EmployeeReport employeeReport;
	private PayrollReport payrollReport;
	private TaxReport taxReport;
	private Database database;
	private Money pay;
	private Money taxes;
	
	public void calculatePay(){
		pay = new Money();
//	do the work
	}
	
	public void calculateTaxes(){
		taxes = new Money();
//	do the work
	}
	
	public void writeToDatabase(){
//	writes the employee to the database
	}
	
	public void loadFromDatabase(){
//	reads the employee from the database
	}
	
	public void displayOnEmployeeReport(){
//	puts the name, salary, etc on the employee report
	}
	
	public void displayOnPayrollReport(){
//	puts the name, pay, etc on the payroll report
	}
	public void displayOnTaxReport(){
//	puts the taxes on the tax report
	}
}
