package srp5;

public class PayrollReport extends EmployeeReport implements EmployeeReporter{
	private StringBuffer report = new StringBuffer();
	private String name;
	private Money pay;
	private Money totalNetPay;
	private Money totalIrsWitholding;
	
	protected void putHeading() {
		//put the heading into the report
	}
	protected void putLineItem() {
		//put the name, pay, totalNetPay taxes, etc into the report
	}
	protected void putSummary() {
		//put the summary into the report
	}
	
	public void acceptName(String name) {
		this.name = name;
	}
	
	public void acceptPay(Money pay) {
		this.pay = pay;
		this.totalNetPay.add(pay);
		//Also compute totalIrsWitholding
	}
}
