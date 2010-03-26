package lod1;

import java.util.List;

public class PayEmployeeTransaction {
  private ServiceLocator serviceLocator = new ServiceLocator();
  private Employee employee;
  private Money grossPay = new Money();
  private Money preTaxDeductions;
  private Money postTaxDeductions;
  private Money fedTaxes;
  private Money stateTaxes;
  private Money localTaxes;
  private Money ficaTaxes;
  private Money medTaxes;
  private int minutesWorked;
  private Money netPay;

  public void execute() {
    Employer employer = serviceLocator.getDatabase().getEmployer(
      employee.getEmployerId()
    );

    calculateGrossPay();
    applyDeductions();
    generatePayment(employer);
  }

  private void calculateGrossPay() {

    if (employee.getPayClassifcation() instanceof HourlyPayClassification) {
      HourlyPayClassification payClass = (HourlyPayClassification) (employee
        .getPayClassifcation());

      List<Timecard> timecards = payClass.getTimeCards();
      minutesWorked = 0;
      for (int i = 0; i < timecards.size(); i++) {
        Timecard timecard = (Timecard) (timecards.get(i));
        minutesWorked += timecard.getMinutesWorked();
      }

      Money hourlyRate = payClass.getHourlyRate();
      grossPay.addTo(new Money(hourlyRate).dividedBy(60).multiplyBy(
        minutesWorked
      )
      );
    } /* else ... */
  }

  private void applyDeductions() {

    final Benefits benefits = employee.getBenefits();
    final Plan401K the401k = benefits.get401k;
    final double pct401k = the401k.getPercentage();
    preTaxDeductions = serviceLocator.getPreTaxDeductionCalculator()
      .calcualte(
        grossPay,
        pct401k,
        employee.getBenefits().getMedicalPlan()
          .getMedicalDeduction()
        /* etc */
      );

    fedTaxes = serviceLocator.getFedTaxcalculator().calcualte(grossPay,
      employee.getTaxInfo().getYtd(),
      employee.getTaxInfo().getW4().getExemptions()
    );

    stateTaxes = serviceLocator.getStateTaxDeductionCalculator(
      employee.getAddress().getState()
    ).calculate(grossPay,
      employee.getTaxInfo().getW4().getExemptions()
    );

    localTaxes = serviceLocator.getLocalTaxDeductionCalculator().calculate(
      grossPay, employee.getAddress().getZip()
    );

    ficaTaxes = serviceLocator.getFicaTaxDeductionCalculator().calculate(
      grossPay, employee.getTaxInfo().getYtd().getFicaPaid()
    );

    medTaxes = serviceLocator.getMedTaxDeductionCalculator().calculate(
      grossPay
    );

    netPay = new Money().minus(preTaxDeductions).minus(fedTaxes).minus(
      stateTaxes
    ).minus(localTaxes).minus(ficaTaxes).minus(medTaxes)
      .minus(postTaxDeductions);
  }

  private void generatePayment(Employer employer) {

    if (employee.getBank() != null) {
      serviceLocator.getTransferMoneyService().transfer(
        employer.getBank().getAccount().getRoutingNumber(),
        employer.getBank().getAccount().getAccountNumber(),
        employee.getBank().getAccount().getRoutingNumber(),
        employee.getBank().getAccount().getAccountNumber(),
        "Paycheck from:" + employer.getName() + "Hours worked:"
          + formatMinutesWorked(minutesWorked), netPay
      );
    } else {
      // deal with other cases: check, cash, gift card
    }
  }

  private String formatMinutesWorked(int minutesWorked) {
    // TODO Auto-generated method stub
    return null;
  }

  public void setEmployee(Employee bill) {
    // TODO Auto-generated method stub

  }

}
