package lod2;

public class Employee {

  private Money grossPay;
  private PayClassification payClass;
  private PayStub payStub;
  private DeductionCalculator deductionCalculator;
  private PaymentMethod payMethod;
  
  public Employee(String string, DeductionCalculator calculator, PayClassification payClass) {
    deductionCalculator = calculator;
    this.payClass = payClass;
  }

  public PayStub generatePay() {
    
    grossPay = payClass.calcualtePay();
    deductionCalculator.applyDeductions(payStub, grossPay, this);
    payMethod.generatePayment(payStub);
    
    return payStub;
  }
}
