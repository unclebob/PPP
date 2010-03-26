package srp5;

import java.util.ArrayList;

public class EmployeeList {
	ArrayList<Employee> list;

	public EmployeeList() {
		list = new ArrayList<Employee>();
	}

	public Employee get(int i) {
		return (Employee) list.get(i);
	}

	public int size() {
		return list.size();
	}
}