package lod2;

import java.util.ArrayList;
import java.util.List;

public class CompositeDeductionCalculator implements DeductionCalculator {

	List<DeductionCalculator> calculators = new ArrayList<DeductionCalculator>();

	public void applyDeductions(PayStub payStub, Money grossPay,
			Employee employee) {

		for (DeductionCalculator calculator : calculators) {
			calculator.applyDeductions(payStub, grossPay, employee);
		}
	}
}
