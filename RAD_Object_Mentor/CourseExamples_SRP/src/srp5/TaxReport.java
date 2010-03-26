package srp5;

//similar to PayrollReport, except with different items in the report
public class TaxReport extends EmployeeReport implements EmployeeReporter{
	protected void putHeading() {}
	protected void putLineItem() {}
	protected void putSummary() {}

	public void acceptName(String name) {}
	public void acceptPay(Money ytdPay) {}
}
