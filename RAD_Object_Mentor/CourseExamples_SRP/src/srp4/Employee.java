package srp4;

public class Employee {
	private EmployeeGateway employeeGateway;
	private Bank bank;
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
	
	public void reportTo(EmployeeReporter employeeReporter){
		employeeReporter.acceptName(name);
		employeeReporter.acceptPay(pay);
	}
	public void importFrom(EmployeeGateway employeeGateway){
		//get a record
		//get the name
		//get the salary...
	}
	public void exportTo(EmployeeGateway employeeGateway){
		//get a record
		//set the name
		//set the salary...
	}
}
