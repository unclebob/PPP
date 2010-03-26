package srp3;

public class EmployeeFacade {
	private Employee employee;
	private EmployeeGateway employeeGateway;
	private EmployeeReportHelper employeeReportHelper;
	
	public void calculatePay(){
		employee.calculatePay();
	}
	
	public void calculateTaxes(){
		employee.calculateTaxes();
	}
	
	public void writeToDatabase(){
		employeeGateway.writeToDatabase();
	}
	public void loadFromDatabase(){
		employeeGateway.loadFromDatabase();
	}
	
	public void displayOnEmployeeReport(){
		employeeReportHelper.displayOnEmployeeReport(new EmployeeInfo());
	}
	
	public void displayOnParyollReport(){
		employeeReportHelper.displayOnPayrollReport(new EmployeeInfo());
	}
	
	public void displayOnTaxReport(){
		employeeReportHelper.displayOnTaxReport(new EmployeeInfo());
	}
}
