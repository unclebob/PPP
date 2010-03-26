package srp3;

public class EmployeeReportHelper {
	private EmployeeReport employeeReport;
	private ParyollReport payrollReport;
	private TaxReport taxReport;
	
	public void displayOnEmployeeReport(EmployeeInfo info) {
		employeeReport.display(info);
	}
	public void displayOnPayrollReport(EmployeeInfo info) {
		payrollReport.display(info);
	}
	public void displayOnTaxReport(EmployeeInfo info) {
		taxReport.display(info);
	}
}
