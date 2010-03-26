package srp5;

public class Employee {
	private Money pay;
	private Money taxes;
	private String name;
	
	public void calculatePay(){
		pay = new Money();
//	do the work
	}
	
	public void calculateTaxes(){
		taxes = new Money();
//	do the work
	}
	
	public void reportTo(EmployeeReporter employeeReporter) {
		employeeReporter.acceptName(name);
		employeeReporter.acceptPay(pay);
	}
}
