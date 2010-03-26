package lod1;

import junit.framework.TestCase;

public class PayEmployeeTransactionTest extends TestCase {

  private HourlyPayClassification payClass;
  private Employee bill;
  private Employer employer;
  ServiceLocator locator = new MockServiceLocator();
  TransferMoneyService transferService = new MockTransferMoneyService();
  private Bank billsBank;
  
  private Database database = new MockDatabase();
  private PreTaxCalculator preTaxCalculator = new MockPreTaxDeductionCalculator();
  private FedTaxCalculator fedTaxCalculator = new MockFedTaxcalculator();
  private StateTaxCalculator stateTaxCalculator = new MockStateTaxDeductionCalculator();
  private LocalTaxCalculator localTaxCalculator = new MockLocalTaxDeductionCalculator();
  private FicaTaxCalculator ficaTaxCalculator = new MockFicaTaxDeductionCalculator();
  private MedTaxCalculator medTaxCalculator = new MocktMedTaxDeuctionCalculator();
  private Bank employerBank;
  private PayEmployeeTransaction t;
  
  public void setUp() {
    locator.setDatabase(database);
    locator.setTransferMoneyService(transferService);
    locator.setPreTaxDeductionCalculator(preTaxCalculator);
    locator.setFedTaxcalculator(fedTaxCalculator);
    locator.setStateTaxDeductionCalculator(stateTaxCalculator);
    locator.setLocalTaxDeductionCalculator(localTaxCalculator);
    locator.setFicaTaxDeductionCalculator(ficaTaxCalculator);
    locator.setMedTaxDeductionCalculator(medTaxCalculator);
        billsBank = new Bank();
    billsBank.setRoutingNumber("111222333444");
    billsBank.setAccount("11111");
    bill = new Employee("Bill");
    bill.setBank(billsBank);
    employer = new Employer();
    employerBank = new Bank();
    employerBank.setRoutingNumber("999888777666");
    employerBank.setAccount("99999");
    employer.setBank(employerBank);
    database.addEmployer("1", employer);
    bill.setEmployerId("1");
    payClass = new HourlyPayClassification();
    bill.setPayClassification(payClass);
    t = new PayEmployeeTransaction();
    t.setEmployee(bill);
  }
  
  public void testFortyHourWeek() {
    
//    for (int i = 0; i < 5; i++) {
//      payClass.add(new Timecard(bill, 8*60));
//    }    
//    payClass.setHourlyRate(new Money(1000));  
//    t.execute();
//    
//    assertEquals(bill.getPayStub().getGross().toString(), "$400");
//    assertEquals(bill.getPayStub().getFed().toString(), "$1");
//    assertEquals(bill.getPayStub().getState().toString(), "$2");
//    assertEquals(bill.getPayStub().getFica().toString(), "$3");
//    assertEquals(bill.getPayStub().getMed().toString(), "$4");
  }
  
}
