package srp2;

public class Employee {
	private Bank bank;
	private String name = "";
	private Money taxes;
	private Money pay;
	private Money salary;
	
	public void calculatePay(){
		pay = new Money();
//	do the work
	}
	public void calculateTaxes(){
		taxes = new Money();
//	do the work
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public Money getTaxes(){
		return taxes;
	}
	public Money getGrossPay(){
		return pay;
	}
	public Money getSalary(){
		return salary;
	}
}
