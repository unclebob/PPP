package srp4;

public class PayrollReport implements EmployeeReporter{
	private EmployeeList employeeList;

	public void acceptName(String name) {}
	public void acceptPay(Money pay) {}
	
	private void build(){
		employeeList = new EmployeeList();
		for(int i=0; i<employeeList.size(); i++) {
			Employee employee = ( (Employee) employeeList.get(i));
			employee.reportTo(this);
		}
	}
}
