package lod2;

import java.util.ArrayList;
import java.util.List;

public class HourlyPayClassification implements PayClassification {

	List<Timecard> timecards = new ArrayList<Timecard>();
	Money hourlyRate;

	public Money calcualtePay() {
		Money grossPay = new Money();
		for (Timecard timecard : timecards) {
			// do the math on the timecards
		}

		return grossPay;
	}

	public void addTimecard(Timecard timecard) {
		timecards.add(timecard);
	}

	public void setHourlyRate(Money rate) {
		hourlyRate = rate;
	}
}
