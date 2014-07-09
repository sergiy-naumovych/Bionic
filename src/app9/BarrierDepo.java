package app9;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BarrierDepo extends DepoBase implements InterestSum  {

	public static final int MIDDLE = 50000;
	
	public static final int MAX = 100000;

	public BarrierDepo(double sum, int days, double interestRate, LocalDate startDate) {
		super(sum, days, interestRate, startDate);
	}
	
	public double calculateInterest(){
		double daysInYear;
		double daysInYear1;
		double daysInYear2;
		double period1;
		double period2;
		double intRate = this.interestRate;
		
		if(this.sum > MIDDLE && this.sum <= MAX){
			intRate++;
		} else if(this.sum > MAX){
			intRate += 2;
		}
		
		
		this.maturityDate = startDate.plusDays(days); 
		if(startDate.getYear() == maturityDate.getYear()){
			if(startDate.isLeapYear()){
				daysInYear = 366;
			} else {
				daysInYear = 365;
			}
			
			this.interest = this.sum * (intRate / 100.0) * (this.days / daysInYear);
			//System.out.println(this.sum * (this.interestRate / 100.0));
		} else {
			period1 = ChronoUnit.DAYS.between(this.startDate, LocalDate.of(startDate.getYear(), 12, 31));
			period2 = ChronoUnit.DAYS.between(LocalDate.of(this.maturityDate.getYear(), 1, 1), this.maturityDate) + 1;
			
			daysInYear1 = this.startDate.isLeapYear() ? 366 : 365;
			
			daysInYear2 = this.maturityDate.isLeapYear() ? 366 : 365;
						
			
			this.interest = this.sum * (intRate / 100.0) * (period1 / daysInYear1 + period2 / daysInYear2);
			//System.out.println("period =" + this.interest);
		}
		
		return this.getInterest();
	}



}
