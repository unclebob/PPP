package srp4;

public class EmployeeReport implements EmployeeReporter{
	private EmployeeList employeeList;
	private String name;
	private Money pay;

	public void acceptName(String name) {
		this.name = name;
	}
	public void acceptPay(Money pay) {
		this.pay = pay;
	}
	
	private void build(){
		employeeList = new EmployeeList();
		for(int i=0; i<employeeList.size(); i++) {
			Employee employee = ( (Employee) employeeList.get(i));
			employee.reportTo(this);
		}
	}
}
