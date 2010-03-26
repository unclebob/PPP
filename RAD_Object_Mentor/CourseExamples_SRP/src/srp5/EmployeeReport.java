package srp5;

public abstract class EmployeeReport implements EmployeeReporter{
	public void build() {
		EmployeeList employeeList = new EmployeeList();
		putHeading();
		for (int i=0; i<employeeList.size(); i++)
		{
			Employee e = employeeList.get(i);
			e.reportTo(this);
			putLineItem();
		}
		putSummary();
	}
	
	protected abstract void putHeading();
	protected abstract void putLineItem();
	protected abstract void putSummary();
}
