package srp2;

public class EmployeeGateway {
	private Employee employee;
	private Database database;
	
	public Employee loadEmployee() {
		employee = new Employee();
		//load from database to generate a new employee
		return employee;
	}
	
	public void save(Employee e) {
		//save to database
	}
}
